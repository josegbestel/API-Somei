package com.somei.apisomei.service;

import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.*;
import com.somei.apisomei.model.enums.StatusOrcamento;
import com.somei.apisomei.model.representationModel.AvaliacaoModel;
import com.somei.apisomei.model.representationModel.OrcamentoNovoModel;
import com.somei.apisomei.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrcamentoService {

    @Autowired
    OrcamentoRepository orcamentoRepository;

    @Autowired
    SolicitanteRepository solicitanteRepository;

    @Autowired
    CategoriaMeiRepository categoriaMeiRepository;

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Autowired
    RespostaOrcamentoRepository respostaOrcamentoRepository;


    //create
    public Orcamento create(OrcamentoNovoModel orcamentoNovo){

        Solicitante solicitante = solicitanteRepository.findById(orcamentoNovo.getSolicitanteId())
                .orElseThrow(() -> new NotFoundException("Solicitante não localizado"));
        CategoriaMei categoria = categoriaMeiRepository.findById(orcamentoNovo.getCategoriaMeiId())
                .orElseThrow(() -> new NotFoundException("Serviço não localizado"));
        if(!profissionalRepository.existsByCategoriaId(orcamentoNovo.getCategoriaMeiId())){
            throw new NotFoundException("Não existem profissionais nesta categoria");
        }

        Orcamento orcamento = Orcamento.byModel(orcamentoNovo, solicitante, categoria);
        orcamento.setStatus(StatusOrcamento.NOVO);
        Orcamento orcamentoCriado = orcamentoRepository.save(orcamento);

        orcamentoCriado.addAgendas(orcamentoNovo.getAgendas());
        return enviarOrcamento(orcamentoRepository.saveAndFlush(orcamento));
    }

    //Avaliação para o profissional
    public Avaliacao createAvaliacaoProfissional(long idOrcamento, AvaliacaoModel avaliacaoModel){
        Orcamento orcamento = orcamentoRepository.findById(idOrcamento)
                .orElseThrow(() -> new NotFoundException("Orcaçamento Não localizado"));

        Profissional profissional = profissionalRepository.findById(avaliacaoModel.getIdDestinatario())
                .orElseThrow(() -> new NotFoundException("Profissional (destinatário) Não localizado"));

        Solicitante solicitante = solicitanteRepository.findById(avaliacaoModel.getIdCriaddor())
                .orElseThrow(() -> new NotFoundException("Solicitante (criador) Não localizado"));

        //TODO: Habilitar com a parte 2
//        //Validar se o orçamento já está finalizado
//        if(orcamento.getStatus() != StatusOrcamento.FINALIZADO){
//            throw new DomainException("Este oçamento ainda não está finalizado para poder avaliar");
//        }

        //Validar se já não existe a avaliação
        List<Avaliacao> avaliacoesExistente = orcamento.getAvaliacoes();
        for(Avaliacao a : avaliacoesExistente){
            if(a.getDestinatario().getId() == avaliacaoModel.getIdDestinatario()){
                throw new DomainException("Profissional já foi avaliado");
            }
        }

        //Validar se o destinatário/profissional pertencem ao orçamento
        if(avaliacaoModel.getIdDestinatario() != orcamento.getProfissional().getId()){
            throw new DomainException("Este profissional não pertence a este orçamento");
        }

        //validar se o criador/solicitante pertencem ao orçamento
        if(avaliacaoModel.getIdCriaddor() != orcamento.getSolicitante().getId()){
            throw new DomainException("Este solicitante não pertence a este orçamento");
        }

        orcamento.addAvaliacao(avaliacaoModel.byModel(solicitante, profissional, orcamento));
        orcamento = orcamentoRepository.save(orcamento);
        return orcamento.getAvaliacaoProfissional();
    }


    //Create avaliacao solicitante
    public Avaliacao createAvaliacaoSolicitante(long idOrcamento, AvaliacaoModel avaliacaoModel){
        Orcamento orcamento = orcamentoRepository.findById(idOrcamento)
                .orElseThrow(() -> new NotFoundException("Orcaçamento Não localizado"));

        Solicitante solicitante = solicitanteRepository.findById(avaliacaoModel.getIdDestinatario())
                .orElseThrow(() -> new NotFoundException("Solicitante (destinatário) Não localizado"));

        Profissional profissional = profissionalRepository.findById(avaliacaoModel.getIdCriaddor())
                .orElseThrow(() -> new NotFoundException("Profissional (criador) Não localizado"));

        //TODO: Habilitar com a parte 2
//        //Validar se o orçamento já está finalizado
//        if(orcamento.getStatus() != StatusOrcamento.FINALIZADO){
//            throw new DomainException("Este oçamento ainda não está finalizado para poder avaliar");
//        }

        //Validar se já não existe a avaliação
        List<Avaliacao> avaliacoesExistente = orcamento.getAvaliacoes();
        for(Avaliacao a : avaliacoesExistente){
            if(a.getDestinatario().getId() == avaliacaoModel.getIdDestinatario()){
                throw new DomainException("Profissional já foi avaliado");
            }
        }

        //Validar se o criador/profissional pertencem ao orçamento
        if(avaliacaoModel.getIdDestinatario() == orcamento.getProfissional().getId()){
            throw new DomainException("Este profissional não pertence a este orçamento");
        }

        //validar se o solicitante/solicitante pertencem ao orçamento
        if(avaliacaoModel.getIdCriaddor() == orcamento.getSolicitante().getId()){
            throw new DomainException("Este solicitante não pertence a este orçamento");
        }

        orcamento.addAvaliacao(avaliacaoModel.byModel(profissional, solicitante, orcamento));
        orcamento = orcamentoRepository.save(orcamento);
        return orcamento.getAvaliacaoSolicitante();
    }

    //read
    public Orcamento read(Long id){
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Orcamento não nocalizado"));

        return orcamento;
    }

    //read solicitante
    public List<Orcamento> readBySolicitante(Long solicitanteId){
        Solicitante solicitante = solicitanteRepository.findById(solicitanteId)
                .orElseThrow(() -> new NotFoundException("Solicitante não localizado"));
        List<Orcamento> orcamentos = orcamentoRepository.findBySolicitanteId(solicitante.getId())
                .orElseThrow(() -> new NotFoundException("Solicitante não possui orçamentos"));

        return orcamentos;
    }

    //disable orcamento
    public Orcamento disable(Long id){
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new DomainException("Orcamento não localizado"));

        orcamento.inativar();
        return orcamentoRepository.save(orcamento);
    }

    //Enviar orçamento para profissionais
    private Orcamento enviarOrcamento(Orcamento orcamento){
        Map<Profissional, Double> mapProfissionais = new HashMap<>();
        List<Profissional> profissionais = profissionalRepository.findByCategoriaId(orcamento.getCategoria().getId())
                .orElseThrow(() -> new NotFoundException("Nenhum profissional encontrado nessa categoria"));
        profissionais.forEach(p -> mapProfissionais.put(p, Localizacao.distancia(orcamento.getLocalizacao(), p.getLocalizacao())));

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
            orcamento.addResposta(resposta);
        }

        orcamento.setStatus(StatusOrcamento.SOLICITADO);
        //Salva as respostas no orçamento
        return orcamentoRepository.saveAndFlush(orcamento);
    }

    //update escolhida
    public void escolherResposta(long idOrcamento, long idResposta){
        Orcamento orcamento = orcamentoRepository.findById(idOrcamento)
                .orElseThrow(() -> new NotFoundException("Orcaçamento não localizado"));

        RespostaOrcamento resposta = respostaOrcamentoRepository.findById(idResposta)
                .orElseThrow(() -> new NotFoundException("Resposta não localizada"));

        boolean respostaLocalizada = false;
        for(RespostaOrcamento r : orcamento.getRespostas()){
            if(r.getId() == idResposta){
                r.setEscolhida(true);
                orcamento.setStatus(StatusOrcamento.RESPONDIDO);
                orcamento.setProfissional(resposta.getProfissional());
                respostaLocalizada = true;
            }else{
                r.setEscolhida(false);
            }
        }
        if(respostaLocalizada)
            orcamentoRepository.save(orcamento);
        else
            throw new DomainException("Esta resposta não existe no orçamento");

    }
}
