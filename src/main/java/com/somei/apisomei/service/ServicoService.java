package com.somei.apisomei.service;

import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.*;
import com.somei.apisomei.model.dto.ServiceInvoiceNfeDTO;
import com.somei.apisomei.model.enums.StatusServico;
import com.somei.apisomei.model.representationModel.AvaliacaoModel;
import com.somei.apisomei.model.representationModel.CartaoModel;
import com.somei.apisomei.model.representationModel.FinalizacaoServicoModel;
import com.somei.apisomei.model.representationModel.ServicoNovoModel;
import com.somei.apisomei.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    SolicitanteRepository solicitanteRepository;

    @Autowired
    CategoriaMeiRepository categoriaMeiRepository;

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Autowired
    RespostaOrcamentoRepository respostaOrcamentoRepository;

    @Autowired
    NfeService nfeService;

    @Autowired
    NotaFiscalRepository notaFiscalRepository;

    //create
    public Servico create(ServicoNovoModel orcamentoNovo){

        Solicitante solicitante = solicitanteRepository.findById(orcamentoNovo.getSolicitanteId())
                .orElseThrow(() -> new NotFoundException("Solicitante não localizado"));
        CategoriaMei categoria = categoriaMeiRepository.findByTitulo(orcamentoNovo.getCategoriaMeiTitulo())
                .orElseThrow(() -> new NotFoundException("Serviço não localizado"));

        //Verifica se existem profissionais nessa categoria
        if(!profissionalRepository.existsByCategoriaId(categoria.getId())){
            throw new NotFoundException("Não existem profissionais nesta categoria");
        }

        Servico servico = Servico.byModel(orcamentoNovo, solicitante, categoria);
        servico.setStatus(StatusServico.NOVO);
        Servico servicoCriado = servicoRepository.save(servico);

        servicoCriado.addAgendas(orcamentoNovo.getAgendas());
        return enviarOrcamento(servicoRepository.saveAndFlush(servico));
    }

    //Finalização por parte do profissional
    public Avaliacao finalizarServicoProfissional(long idServico, long idProfissional, FinalizacaoServicoModel finalizacao){
        AvaliacaoModel avaliacaoModel = finalizacao.getAvaliacao();

        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new NotFoundException("Serviço Não localizado"));

//        //Validar se o Serviço está no status PENDENTE
//        this.validServicoStatus(servico);

        //Validar se o criador (profissional) pertence ao orçamento
        if(idProfissional != servico.getProfissional().getId()){
            throw new DomainException("Este profissional não pertence a este orçamento");
        }

        //Validar se já não existe a avaliação
        List<Avaliacao> avaliacoesExistente = servico.getAvaliacoes();
        for(Avaliacao a : avaliacoesExistente){
            if(a.getCriador().getId() == idProfissional){
                throw new DomainException("Este profissional já realizou sua avaliação");
            }
        }

        //Definir destinatário e criador
        Pessoa criador = servico.getProfissional();
        Pessoa destinatario = servico.getSolicitante();

        //Instancia a avaliação
        Avaliacao avaliacao = new Avaliacao(criador, destinatario, servico,
                avaliacaoModel.getNota(), avaliacaoModel.getComentario());
        servico.addAvaliacao(avaliacao);

        //Inserir infos de finalizacao
        if(finalizacao.getCodigoServicoMunicipal() != null)
            servico.setCodigoServicoMunicipal(finalizacao.getCodigoServicoMunicipal());
        else
            servico.setCodigoServicoMunicipal("000");
        servico.setCustoExecucao(finalizacao.getCustoExecucao());


        //Se já houver avaliação do solicitante, finalizar
        if(servico.getAvaliacaoSolicitante() != null){
            servico.setStatus(StatusServico.FINALIZADO);
            servico.setDtConcluido(LocalDateTime.now());
            servico = this.emitirNFSe(servico);
        }

        servico = servicoRepository.save(servico);
        return servico.getAvaliacaoProfissional();
    }

    //Finalizar serviço (lado solicitante)
    public Avaliacao finalizarServicoSolicitante(long idServico, long idSolicitante, AvaliacaoModel avaliacaoModel){
        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new NotFoundException("Orcaçamento Não localizado"));

        //Validar se o Serviço está no status PENDENTE
        this.validServicoStatus(servico);

        //Validar se o criador (solicitante) pertence ao orçamento
        System.out.println("Solicitante avaliacao: " + idSolicitante);
        System.out.println("Solicitante orcamento: " + servico.getSolicitante().getId());
        if(idSolicitante != servico.getSolicitante().getId()){
            throw new DomainException("Este solicitante não pertence a este orçamento");
        }

        //Validar se o solicitante já não criou a avaliação
        List<Avaliacao> avaliacoesExistentes = servico.getAvaliacoes();
        for(Avaliacao a : avaliacoesExistentes){
            if(a.getCriador().getId() == idSolicitante){
                throw new DomainException("Solicitante já fez sua avaliação");
            }
        }


        //Definir destinatário e criador
        Pessoa criador = servico.getSolicitante();
        Pessoa destinatario = servico.getProfissional();

        //Instancia a avaliação
        Avaliacao avaliacao = new Avaliacao(criador, destinatario, servico,
                avaliacaoModel.getNota(), avaliacaoModel.getComentario());
        servico.addAvaliacao(avaliacao);

        //Se o profissional já avaliou, finalizar
        if(servico.getAvaliacaoProfissional() != null){
            servico.setStatus(StatusServico.FINALIZADO);
        }

        servico = servicoRepository.save(servico);
        return servico.getAvaliacaoSolicitante();
    }

    //read
    public Servico read(Long id){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Orcamento não nocalizado"));

        return servico;
    }

    //read solicitante
    public List<Servico> readBySolicitante(Long solicitanteId){
        Solicitante solicitante = solicitanteRepository.findById(solicitanteId)
                .orElseThrow(() -> new NotFoundException("Solicitante não localizado"));
        List<Servico> servicos = servicoRepository.findBySolicitanteId(solicitante.getId())
                .orElseThrow(() -> new NotFoundException("Solicitante não possui orçamentos"));

        //Filtrar repostas apenas respondidas
        for (Servico s : servicos) {
            List<RespostaOrcamento> respondidas = s.getRespostas().stream().filter(r -> r.getDtResposta() != null).collect(Collectors.toList());
            s.setRespostas(respondidas);
        }

        return servicos;
    }

    //read profissional
    public List<Servico> readByProfissional(Long profissionalId){
        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new NotFoundException("Profissional não localizado"));
        List<Servico> servicos = servicoRepository.findByProfissionalId(profissional.getId())
                .orElseThrow(() -> new NotFoundException("Profissional não possui orçamentos"));

        return servicos;
    }

    //disable orcamento
    public Servico disable(Long id){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new DomainException("Orcamento não localizado"));

        servico.inativar();
        return servicoRepository.save(servico);
    }

    //Enviar orçamento para profissionais
    private Servico enviarOrcamento(Servico servico){
        Map<Profissional, Double> mapProfissionais = new HashMap<>();
        List<Profissional> profissionais = profissionalRepository.findByCategoriaId(servico.getCategoria().getId())
                .orElseThrow(() -> new NotFoundException("Nenhum profissional encontrado nessa categoria"));
        profissionais.forEach(p -> mapProfissionais.put(p, Localizacao.distancia(servico.getLocalizacao(), p.getLocalizacao())));

        //Ordena o map de profissionais
        Map<Profissional, Double> mapOrdenada = mapProfissionais.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        mapOrdenada.forEach((p,d) -> System.out.println(p.getNomeFantasia() + " -> " + d));

        //Transforma o map em um arraylist
        List<Profissional> profissionaisEscolhidos = new ArrayList<>();
        mapOrdenada.forEach((p, d) -> profissionaisEscolhidos.add(p));

        //Cria respostas para a quantidade certa de profissionais
        List<RespostaOrcamento> respostas = new ArrayList<>();
        int max = mapOrdenada.size() >= 10 ? 10 : mapOrdenada.size();
        for(int i = 0; i < max; i++){
            RespostaOrcamento resposta = new RespostaOrcamento();
            resposta.setProfissional(profissionaisEscolhidos.get(i));
            servico.addResposta(resposta);
        }

        servico.setStatus(StatusServico.SOLICITADO);
        //Salva as respostas no orçamento
        return servicoRepository.saveAndFlush(servico);
    }

    //Escolher resposta de um profissional
    public void escolherResposta(long idServico, long idResposta, CartaoModel cartaoModel){
        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new NotFoundException("Serviço não localizado"));

        RespostaOrcamento resposta = respostaOrcamentoRepository.findById(idResposta)
                .orElseThrow(() -> new NotFoundException("Resposta não localizada"));

        boolean respostaLocalizada = false;
        for(RespostaOrcamento r : servico.getRespostas()){
            if(r.getId() == idResposta){
                r.setEscolhida(true);
                servico.setStatus(StatusServico.PENDENTE);
                servico.setProfissional(resposta.getProfissional());
                respostaLocalizada = true;


                //TODO: Cobrar valor do Solicitante
                this.cobrarServicoJuno(servico, cartaoModel);


            }else{
                r.setEscolhida(false);
            }
        }
        if(respostaLocalizada)
            servicoRepository.save(servico);
        else
            throw new DomainException("Esta resposta não existe no orçamento");

    }

    // [interna] Emitir Nota Fiscal de Serviço
    private Servico emitirNFSe(Servico servico){
        //Enviar NF
        ServiceInvoiceNfeDTO serviceInvoice = nfeService.criarNFSe(servico);

        //Transformar NF p/ o Domain e definir NF no orçamento
        NotaFiscal nf = new NotaFiscal(serviceInvoice);
        nf.setServico(servico);
        nf = notaFiscalRepository.save(nf);
        servico.setNotaFiscal(nf);

        return servicoRepository.save(servico);
    }

    // [interna] Validar o Status do Serviço
    private void validServicoStatus(Servico servico){
        switch (servico.getStatus()){
            case NOVO:
                throw new DomainException("Este serviço ainda não foi enviado para os profissionais");
            case RESPONDIDO:
                throw new DomainException("Este serviço precisa ter uma resposta escolhida");
            case FINALIZADO:
                throw new DomainException("Este serviço já foi finalizado");
            case CANCELADO:
                throw new DomainException("Este serviço está cancelado");
        }
    }

    // [interna] Gerar cobrança na Juno
    private void cobrarServicoJuno(Servico servico, CartaoModel cartaoModel){
        //TODO: Obter chave de autenticação na Juno

        //TODO: Criar conta digital na Juno se solicitante não tiver

        Cartao cartao = servico.getSolicitante().getCartaoByNumero(cartaoModel.getNumeroCartao());

        //Se o solicitante não tiver esse cartão, cadastrar um novo na Juno
        if(cartao == null){
            //TODO: Cadastrar cartão na Juno

        }

        //TODO: Criar cobrança na Juno

        //TODO: Pagar cobrança

    }


}
