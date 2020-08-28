package com.somei.apisomei.resources;

import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.representationModel.PessoaModel;
import com.somei.apisomei.model.representationModel.PessoaLoginModel;
import com.somei.apisomei.service.CrudProfissionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/profissional")
@Api
@CrossOrigin(origins = "*")
public class ProfissionalResource {

    @Autowired
    CrudProfissionalService profissionalService;


    //createProfissional
    @PostMapping
    @ApiOperation("Cria um profissional")
    public ResponseEntity<Profissional> createProfissional(@Valid @RequestBody Profissional profissional){
        return ResponseEntity.ok(profissionalService.create(profissional));
    }

    //getById
    @GetMapping("/id/{id}")
    @ApiOperation("Retorna um profissional a partir do ID")
    public ResponseEntity<Profissional> getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(profissionalService.read(id));
    }


    //getByCnpj
    @GetMapping("/cnpj/{cnpj}")
    @ApiOperation("Retorna um profissional a partir do CNPJ")
    public  ResponseEntity<Profissional> getByCnpj(@PathVariable(value = "cnpj") String cnpj) {
        return ResponseEntity.ok(profissionalService.readByCnpj(cnpj));
    }


    //getByCategoria


    //edit
    @PutMapping("/id/{id}")
    @ApiOperation("Edita as informações de um Profissional por ID")
    public ResponseEntity<Profissional> edit(@PathVariable(value = "id") Long id,
                                            @Valid @RequestBody PessoaModel pessoa){
        Profissional profissional = profissionalService.update(id, pessoa);
        return ResponseEntity.ok(profissional);
    }


    //editLogin
    @PutMapping("login/id/{id}")
    @ApiOperation("Altera as informações de login: Email e senha")
    public ResponseEntity<PessoaLoginModel> editLogin(@PathVariable(value = "id") Long id,
                                                      @Valid @RequestBody PessoaLoginModel login){
        PessoaLoginModel loginNovo = profissionalService.updateLogin(id, login);
        return ResponseEntity.ok(loginNovo);
    }


    //remove
    @DeleteMapping("/id/{id}")
    @ApiOperation("Remove um Profissional")
    public ResponseEntity<Object> remove(@PathVariable(value = "id") Long id){
        profissionalService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
