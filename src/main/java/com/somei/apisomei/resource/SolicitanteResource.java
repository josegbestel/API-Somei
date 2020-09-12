package com.somei.apisomei.resource;

import com.somei.apisomei.model.Solicitante;
import com.somei.apisomei.model.representationModel.PessoaModel;
import com.somei.apisomei.model.representationModel.PessoaLoginModel;
import com.somei.apisomei.model.representationModel.SolicitantePerfilModel;
import com.somei.apisomei.service.SolicitanteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/solicitante")
@Api
@CrossOrigin(origins = "*")
@EnableWebSecurity
public class SolicitanteResource {

    @Autowired
    SolicitanteService solicitanteService;

    //login
    @GetMapping("/login")
    @ApiOperation("Fazer login: retorna as informações do solicitante a partir do usuário de autenticação")
    public ResponseEntity<Solicitante> login(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(solicitanteService.readByEmail(userDetails.getUsername()));
    }


    //create
    @PostMapping
    @ApiOperation("Cria uma Pessoa do tipo Solicitante")
    public ResponseEntity<Solicitante> create(@Valid @RequestBody Solicitante solicitante){
        return ResponseEntity.ok(solicitanteService.create(solicitante));
    }


    //getById
    //TODO: Como retornar somente os campos que preciso? (Retirar o campo ativo)
    @GetMapping("/{id}")
    @ApiOperation("Obtem um Solicitante a partir do ID")
    public ResponseEntity<Solicitante> getById(@PathVariable(value = "id") Long id){
        Solicitante solicitante = solicitanteService.read(id);
        return ResponseEntity.ok(solicitante);
    }


    //getByCpf
    @GetMapping("/cpf/{cpf}")
    @ApiOperation("Obtém um Solicitante a partir do CPF")
    public ResponseEntity<Solicitante> getByCpf(@PathVariable(value = "cpf") String cpf){
        Solicitante solicitante = solicitanteService.read(cpf);
        return ResponseEntity.ok(solicitante);
    }

    //getProfile
    @GetMapping("/{id}/perfil")
    @ApiOperation("Retorna o perfil do solicitante a partir do ID")
    public ResponseEntity<SolicitantePerfilModel> getPerfil(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(solicitanteService.readResumeProfile(id));
    }


    //edit
    @PutMapping("/{id}")
    @ApiOperation("Edita as informações de um Solicitante por ID")
    public ResponseEntity<Solicitante> edit(@PathVariable(value = "id") Long id,
                                                       @Valid @RequestBody PessoaModel pessoa){
        Solicitante solicitante = solicitanteService.update(id, pessoa);
        return ResponseEntity.ok(solicitante);
    }


    //editLogin
    @PutMapping("/login/{id}")
    @ApiOperation("Altera as informações de login: Email e senha")
    public ResponseEntity<PessoaLoginModel> editLogin(@PathVariable(value = "id") Long id,
                                                      @Valid @RequestBody PessoaLoginModel login){
        PessoaLoginModel loginNovo = solicitanteService.updateLogin(id, login);
        return ResponseEntity.ok(loginNovo);
    }


    //remove
    @DeleteMapping("/{id}")
    @ApiOperation("Remove um Solicitante")
    @PreAuthorize("ADMIN")
    public ResponseEntity<Object> remove(@PathVariable(value = "id") Long id,
                                         @AuthenticationPrincipal UserDetails userDetails){
//        if(!Pessoa.validateUser(userDetails, solicitanteService.read(id))){
//            throw new DomainException("usuário inválido");
//        }
        solicitanteService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
