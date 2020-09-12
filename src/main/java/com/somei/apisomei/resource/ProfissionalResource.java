package com.somei.apisomei.resource;

import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.representationModel.PessoaModel;
import com.somei.apisomei.model.representationModel.PessoaLoginModel;
import com.somei.apisomei.model.representationModel.ProfissionalModel;
import com.somei.apisomei.model.representationModel.ProfissionalPerfilModel;
import com.somei.apisomei.service.ProfissionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/profissional")
@Api
@CrossOrigin(origins = "*")
public class ProfissionalResource {

    @Autowired
    ProfissionalService profissionalService;

    //login
    @GetMapping("/login")
    @ApiOperation("Fazer login: retorna as informações do solicitante a partir do usuário de autenticação")
    public ResponseEntity<Profissional> login(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(profissionalService.readByEmail(userDetails.getUsername()));
    }


    //createProfissional
    @PostMapping
    @ApiOperation("Cria um profissional")
    public ResponseEntity<Profissional> createProfissional(@Valid @RequestBody ProfissionalModel profissionalModel){
        return ResponseEntity.ok(profissionalService.create(profissionalModel));
    }

    //getById
    @GetMapping("/{id}")
    @ApiOperation("Retorna um profissional a partir do ID")
    public ResponseEntity<Profissional> getById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(profissionalService.read(id));
    }


    //getByCnpj
    @GetMapping("/cnpj/{cnpj}")
    @ApiOperation("Retorna um profissional a partir do CNPJ")
    public ResponseEntity<Profissional> getByCnpj(@PathVariable(value = "cnpj") String cnpj) {
        return ResponseEntity.ok(profissionalService.readByCnpj(cnpj));
    }


    //getByCategoria
    @GetMapping("/categoria/{id}")
    @ApiOperation("Retorna uma lsita de profissionais a partir do ID da categoria")
    public ResponseEntity<List<Profissional>> getByCategoria(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(profissionalService.readByCategoria(id));
    }

    //getProfile
    @GetMapping("/{id}/perfil")
    @ApiOperation("Retorna o perfil do profissional a partir do ID")
    public ResponseEntity<ProfissionalPerfilModel> getPerfil(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(profissionalService.readResumeProfile(id));
    }


    //edit
    @PutMapping("/{id}")
    @ApiOperation("Edita as informações de um Profissional por ID")
    public ResponseEntity<Profissional> edit(@PathVariable(value = "id") Long id,
                                            @Valid @RequestBody PessoaModel pessoa){
        Profissional profissional = profissionalService.update(id, pessoa);
        return ResponseEntity.ok(profissional);
    }


    //editLogin
    @PutMapping("{id}/login")
    @ApiOperation("Altera as informações de login: Email e senha")
    public ResponseEntity<PessoaLoginModel> editLogin(@PathVariable(value = "id") Long id,
                                                      @Valid @RequestBody PessoaLoginModel login){
        PessoaLoginModel loginNovo = profissionalService.updateLogin(id, login);
        return ResponseEntity.ok(loginNovo);
    }


    //remove
    @DeleteMapping("/{id}")
    @ApiOperation("Remove um Profissional")
    public ResponseEntity<Object> remove(@PathVariable(value = "id") Long id){
        profissionalService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
