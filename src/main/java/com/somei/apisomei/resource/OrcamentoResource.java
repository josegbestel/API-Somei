package com.somei.apisomei.resource;

import com.somei.apisomei.model.Orcamento;
import com.somei.apisomei.model.representationModel.OrcamentoNovoModel;
import com.somei.apisomei.service.OrcamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orcamento")
@Api(value = "API REST SOMEI")
@CrossOrigin(origins = "*")
public class OrcamentoResource {

    @Autowired
    OrcamentoService orcamentoService;

    //CRIAR
    @PostMapping
    @ApiOperation("Cria um orçamento")
    public ResponseEntity<Orcamento> criar(@Valid @RequestBody OrcamentoNovoModel orcamento){
        return ResponseEntity.ok(orcamentoService.create(orcamento));
    }

    //LER POR ID
    @GetMapping("/{id}")
    @ApiOperation("Obtem um orçamento pelo id")
    public ResponseEntity<Orcamento> obterPorId(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(orcamentoService.read(id));
    }

    //LER TODOS POR SOLICITANTE
    @GetMapping("/solicitante/{id}")
    @ApiOperation("Obtém uma lista de orçamentos a partir de um solicitante")
    public ResponseEntity<List<Orcamento>> obterPorSolicitante(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(orcamentoService.readBySolicitante(id));
    }

    //LER TODOS POR PROFISSIONAL (QUE RESPONDEU)


    //DESATIVAR
    @PutMapping("/{id}/desativar")
    @ApiOperation("Desativa um orçamento. [Mesma ideia de excluir]")
    public ResponseEntity<Object> desativar(@PathVariable(value = "id") Long id){
        orcamentoService.disable(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/enviar")
    public ResponseEntity<Orcamento> enviar(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(orcamentoService.enviarOrcamento(id));
    }
}
