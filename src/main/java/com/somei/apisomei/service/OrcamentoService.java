package com.somei.apisomei.service;

import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.*;
import com.somei.apisomei.model.enums.StatusOrcamento;
import com.somei.apisomei.model.representationModel.OrcamentoNovoModel;
import com.somei.apisomei.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //TODO: Tentar salvar a genda junto com o orçamento
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
        return orcamentoRepository.saveAndFlush(orcamento);
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

    //TODO: enviar orçamento para profissionais
    public Orcamento enviarOrcamento(Long orcamentoId){
        Orcamento orcamento = orcamentoRepository.findById(orcamentoId)
                .orElseThrow(() -> new NotFoundException("Orçamento não existe"));
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

    //Escolher resposta
    public void defineResposta(long idOrcamento, long idResposta){

    }
}
