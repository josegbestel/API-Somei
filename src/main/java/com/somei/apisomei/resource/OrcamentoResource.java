package com.somei.apisomei.resource;

import com.somei.apisomei.model.Avaliacao;
import com.somei.apisomei.model.Orcamento;
import com.somei.apisomei.model.RespostaOrcamento;
import com.somei.apisomei.model.representationModel.AvaliacaoModel;
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

    //LER TODOS POR PROFISSIONAL (QUE RESPONDERAM)


    //DESATIVAR
    @PutMapping("/{id}/desativar")
    @ApiOperation("Desativa um orçamento. [Mesma ideia de excluir]")
    public ResponseEntity<Object> desativar(@PathVariable(value = "id") Long id){
        orcamentoService.disable(id);
        return ResponseEntity.noContent().build();
    }

    //Escolher Resposta
    @PutMapping("{id}/resposta/{idResposta}/escolher")
    @ApiOperation("Escolhe uma resposta para o orçamento ser realizado")
    public ResponseEntity<Object> escolherResposta(@PathVariable(name = "id") long id,
                                                      @PathVariable(name = "idResposta") long idResposta){
        orcamentoService.escolherResposta(id, idResposta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/avaliacao/profissional")
    @ApiOperation("Cria a avaliação do profissional")
    public ResponseEntity<Avaliacao> criarAvaliacaoProfissional(@PathVariable(value = "id") Long id,
                                                                @Valid @RequestBody AvaliacaoModel avaliacaoModel){
        return ResponseEntity.ok(orcamentoService.createAvaliacaoProfissional(id, avaliacaoModel));
    }

    @PostMapping("/{id}/avaliacao/solicitante")
    @ApiOperation("Cria a avaliação do solicitante")
    public ResponseEntity<Avaliacao> criarAvaliacaoSolicitante(@PathVariable(value = "id") Long id,
                                                               @Valid @RequestBody AvaliacaoModel avaliacaoModel){
        return ResponseEntity.ok((orcamentoService.createAvaliacaoSolicitante(id, avaliacaoModel)));
    }
}
