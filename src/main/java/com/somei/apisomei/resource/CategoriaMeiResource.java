package com.somei.apisomei.resource;

import com.somei.apisomei.model.CategoriaMei;
import com.somei.apisomei.model.RespostaOrcamento;
import com.somei.apisomei.model.representationModel.CategoriaMeiNovaModel;
import com.somei.apisomei.service.CategoriaMeiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categoria-mei")
@Api(value = "API REST SOMEI")
@CrossOrigin(origins = "*")
public class CategoriaMeiResource {

    @Autowired
    CategoriaMeiService categoriaMeiService;

    //CRIAR
    @PostMapping
    @ApiOperation("Cria uma categoria")
    public ResponseEntity<CategoriaMei> criar(@Valid @RequestBody CategoriaMei categoriaMei){
        return ResponseEntity.ok(categoriaMeiService.create(categoriaMei));
    }

    //CRIAR EM MASSA
    @PostMapping("/lote")
    @ApiOperation("Cria categorias em lote")
    public ResponseEntity<List<CategoriaMei>> criarEmLote(@RequestBody List<@Valid CategoriaMeiNovaModel> categoriaMeiNovaModels){
        return ResponseEntity.ok(categoriaMeiService.create(categoriaMeiNovaModels));
    }

    //LET TODAS
    @GetMapping
    @ApiOperation("Obtém todas as categorias")
    public ResponseEntity<List<CategoriaMei>> obterTodas(){
        return ResponseEntity.ok(categoriaMeiService.readAll());
    }

    //LER TODAS ATIVOS
    @GetMapping("/ativos")
    @ApiOperation("Obtém todas as categorias que possuem profissionais ativos")
    public ResponseEntity<List<CategoriaMei>> obterTodasAtivas(){
        return ResponseEntity.ok(categoriaMeiService.readByProfissionalAtivo());
    }

    //BUSCAR POR TITULO
    @GetMapping("/busca/{titulo}")
    @ApiOperation("Obtém categorias com busca a partir do titulo")
    public ResponseEntity<List<CategoriaMei>> buscarPorNome(@PathVariable(value = "titulo") String titulo){
        return ResponseEntity.ok(categoriaMeiService.readByTitulo(titulo));
    }

    //OBTER BY PROFISSIONAIS ATIVOS
    @PostMapping("/ativos")
    @ApiOperation("Obtém todas as categorias que possuem pelo menos um profissional cadastrado")
    public ResponseEntity<List<CategoriaMei>> obterAtivos(){
        return ResponseEntity.ok(categoriaMeiService.readByProfissionalAtivo());
    }

    //EDITAR
    @PutMapping("/{id}")
    @ApiOperation("Edita uma categoria")
    public ResponseEntity<CategoriaMei> editar(@PathVariable(value = "id") Long id,
                                               @Valid @RequestBody CategoriaMei categoriaMei){
        return ResponseEntity.ok(categoriaMeiService.update(id, categoriaMei));
    }

    //DELETAR
    @DeleteMapping("/{id}")
    @ApiOperation("Deleta uma categoria")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") Long id){
        categoriaMeiService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
