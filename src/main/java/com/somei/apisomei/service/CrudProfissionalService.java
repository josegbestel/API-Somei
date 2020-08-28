package com.somei.apisomei.service;


import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.enums.AuthType;
import com.somei.apisomei.model.representationModel.PessoaModel;
import com.somei.apisomei.model.representationModel.PessoaLoginModel;
import com.somei.apisomei.repository.ProfissionalRepository;
import com.somei.apisomei.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudProfissionalService {

    @Autowired
    ProfissionalRepository profissionalRepository;

    //Create
    public Profissional create(Profissional profissional){
        Optional<Profissional> profExistente = profissionalRepository.findByEmail(profissional.getEmail());
        if(profExistente.isPresent()){
            throw new DomainException("Já existe um profissional com esse email");
        }

        profExistente = profissionalRepository.findByCnpj(profissional.getCnpj());
        if (profExistente.isPresent()){
            throw new DomainException("Já existe um profissional com esse CNPJ");
        }

        profissional.setAuthType(AuthType.USUARIO);
        profissional.setAtivo(true);
        profissional.setSenha(PasswordEncoder.encode(profissional.getSenha()));
        Profissional profissionalResponse = profissionalRepository.save(profissional);
//        profissionalResponse.setSenha(null);

        return profissionalResponse;
    }

    //Read
    public Profissional read(Long id){
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe profissional com esse ID"));

//        profissional.setSenha(null);
        return profissional;
    }

    //Read by cnpj
    public Profissional readByCnpj(String cnpj){
        Profissional profissional = profissionalRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new NotFoundException("Não existe profissional com esse ID"));

//        profissional.setSenha(null);
        return profissional;

    }

    //Read by categoria


    //Update by id
    public Profissional update(Long id, PessoaModel pessoa){
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe profissional com esse ID"));

        //Altera as informações requiridas
        profissional.setNome(pessoa.getNome());
        profissional.setAnoNascimento(pessoa.getAnoNascimento());
        profissional.setTelefone(pessoa.getTelefone());
        profissional.setAvatar(pessoa.getAvatar());

        profissional = profissionalRepository.save(profissional);

//        profissional.setSenha(null);
        return profissional;
    }

    //Update Login
    //TODO: Aprimorar método: deixar mais seguro, poder trocar com e sem senha antiga, com codigo por sms ou email
    public PessoaLoginModel updateLogin(Long id, PessoaLoginModel login) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe profissional com esse ID"));

        Optional<Profissional> solicitanteEmail = profissionalRepository.findByEmail(login.getEmail());
        if (solicitanteEmail.isPresent()){
            throw new DomainException("Já existe um profissional com esse email");
        }

        profissional.setSenha(login.getSenha());
        profissional.setEmail(login.getEmail());
        profissionalRepository.save(profissional);
        return login;
    }

    //Delete
    public void delete(Long id){
        if(!profissionalRepository.existsById(id)){
            new NotFoundException("Não existe cliente com esse ID");
        }

        profissionalRepository.deleteById(id);
    }
}
