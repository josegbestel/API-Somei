package com.somei.apisomei.resource;

import com.somei.apisomei.model.Avaliacao;
import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.representationModel.AvaliacaoModel;
import com.somei.apisomei.model.representationModel.CartaoModel;
import com.somei.apisomei.model.representationModel.FinalizacaoServicoModel;
import com.somei.apisomei.model.representationModel.ServicoNovoModel;
import com.somei.apisomei.service.ServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/servico")
@Api(value = "API REST SOMEI")
@CrossOrigin(origins = "*")
public class ServicoResource {

    @Autowired
    ServicoService servicoService;

    //CRIAR
    @PostMapping
    @ApiOperation("Cria um serviço")
    public ResponseEntity<Servico> criar(@Valid @RequestBody ServicoNovoModel orcamento){
        return ResponseEntity.ok(servicoService.create(orcamento));
    }

    //LER POR ID
    @GetMapping("/{id}")
    @ApiOperation("Obtem um serviço pelo id")
    public ResponseEntity<Servico> obterPorId(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(servicoService.read(id));
    }

    //LER TODOS POR SOLICITANTE
    @GetMapping("/solicitante/{id}")
    @ApiOperation("Obtém uma lista de serviços a partir de um solicitante")
    public ResponseEntity<List<Servico>> obterPorSolicitante(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(servicoService.readBySolicitante(id));
    }

    //LER TODOS POR PROFISSIONAL (QUE RESPONDERAM)
    @GetMapping("/profissional/{id}")
    @ApiOperation("Obtém uma lista de serviços a partir de um profissional")
    public ResponseEntity<List<Servico>> obterPorProfissional(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(servicoService.readByProfissional(id));
    }

    //DESATIVAR
    @PutMapping("/{id}/desativar")
    @ApiOperation("Desativa um serviço. [Mesma ideia de excluir]")
    public ResponseEntity<Object> desativar(@PathVariable(value = "id") Long id){
        servicoService.disable(id);
        return ResponseEntity.noContent().build();
    }

    //Escolher Resposta
    @PutMapping("{id}/resposta/{idResposta}/escolher")
    @ApiOperation("Escolhe uma resposta para o serviço ser realizado")
    public ResponseEntity<Object> escolherResposta(@PathVariable(name = "id") long id,
                                                   @PathVariable(name = "idResposta") long idResposta,
                                                   @RequestBody @Valid CartaoModel cartaoModel){
        servicoService.escolherResposta(id, idResposta, cartaoModel);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idServico}/finalizar/profissional/{idProfissional}")
    @ApiOperation("Insere as informações para concluir o serviço (avaliação, custo e o código do serviço de acordo com a cidade")
    public ResponseEntity<Avaliacao> finalizacaoProfissional(@PathVariable(value = "idServico") Long idServico,
                                                                @PathVariable(value = "idProfissional") Long idProfissional,
                                                                @Valid @RequestBody FinalizacaoServicoModel finalizacao){
        return ResponseEntity.ok(servicoService.finalizarServicoProfissional(idServico, idProfissional, finalizacao));
    }

    @PostMapping("/{idServico}/finalizar/solicitante/{idSolicitante}")
    @ApiOperation("Finaliza o serviço por parte do solicitante criando uma avaliação")
    public ResponseEntity<Avaliacao> criarAvaliacaoSolicitante(@PathVariable(value = "idServico") Long idServico,
                                                               @PathVariable(value = "idSolicitante") Long idSolicitante,
                                                               @Valid @RequestBody AvaliacaoModel avaliacaoModel){
        return ResponseEntity.ok((servicoService.finalizarServicoSolicitante(idServico, idSolicitante, avaliacaoModel)));
    }
}
