package com.somei.apisomei.resource;

import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.RespostaOrcamento;
import com.somei.apisomei.model.representationModel.RespostaOrcamentoModel;
import com.somei.apisomei.model.representationModel.RespostaOrcamentoNovoModel;
import com.somei.apisomei.service.RespostaOrcamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/resposta-orcamento")
@Api
@CrossOrigin(origins = "*")
public class RespostaOrcamentoResource {

    @Autowired
    RespostaOrcamentoService respostaOrcamentoService;

    @GetMapping("/{id}")
    @ApiOperation("Obtém a resposta por id")
    public ResponseEntity<RespostaOrcamentoModel> obterPorId(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(respostaOrcamentoService.read(id));
    }

    //Obter por profissional
    @GetMapping("/profissional/{id}")
    @ApiOperation("Obtem todas as solicitações não respondidas designadas a um determinado profissional")
    public ResponseEntity<List<RespostaOrcamentoModel>> obterPorProfissional(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(respostaOrcamentoService.readByProfissional(id));
    }

    //Responder
    @PutMapping("/{id}/responder")
    @ApiOperation("Responder um orçamento com valor, preço e observação")
    public ResponseEntity<RespostaOrcamento> responder(@PathVariable(value = "id") long id,
                                                        @Valid @RequestBody RespostaOrcamentoNovoModel respostaModel){
        return ResponseEntity.ok(respostaOrcamentoService.updateResposta(id, respostaModel));
    }

    @GetMapping("/{id}/orcamento")
    @ApiOperation("Obtem um orçamento a partir de uma resposta")
    public ResponseEntity<Servico> obterOrcamento(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(respostaOrcamentoService.obterOrcamento(id));
    }
}
