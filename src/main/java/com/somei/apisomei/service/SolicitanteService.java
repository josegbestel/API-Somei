package com.somei.apisomei.service;

import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.Solicitante;
import com.somei.apisomei.model.enums.AuthType;
import com.somei.apisomei.model.representationModel.PessoaModel;
import com.somei.apisomei.model.representationModel.PessoaLoginModel;
import com.somei.apisomei.repository.SolicitanteRepository;
import com.somei.apisomei.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SolicitanteService {

    @Autowired
    SolicitanteRepository solicitanteRepository;

    //create
    public Solicitante create(Solicitante solicitante){
        //Verifica se ja existe email cadastrado
        System.out.println(solicitante.getEmail());
        Optional<Solicitante> solicitanteExistente = solicitanteRepository.findByEmail(solicitante.getEmail());
        if(solicitanteExistente.isPresent()){
            throw new DomainException("Já existe um cliente com esse email");
        }

        //Verifica se ja existe CPF cadastrado
        solicitanteExistente = solicitanteRepository.findByCpf(solicitante.getCpf());
        if(solicitanteExistente.isPresent()){
            throw new DomainException("Já existe um cliente com esse CPF");
        }

        if (solicitante.getSenha().length() != 8){
            throw new DomainException("Campo senha precisa ter 8 caracteres");
        }

        solicitante.setAuthType(AuthType.USUARIO);
        solicitante.setSenha(PasswordEncoder.encode(solicitante.getSenha()));
        solicitante.setAtivo(true);
        solicitante.setDtAtivo(LocalDateTime.now());
        Solicitante solicitanteResponse = solicitanteRepository.save(solicitante);
//        solicitanteResponse.setSenha(null);
        return solicitanteResponse;
    }


    //TODO: Create cartão


    //read
    public Solicitante read(Long id){
        Solicitante solicitante = solicitanteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe cliente com esse ID"));

//        solicitante.setSenha(null);
        return solicitante;

    }

    //read by cpf
    public Solicitante read(String cpf) {
        Solicitante solicitante = solicitanteRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Não existe cliente com esse CPF"));

//        solicitante.setSenha(null);
        return solicitante;
    }

    //read by email
    public Solicitante readByEmail(String email){
        Solicitante solicitante = solicitanteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Cliente não localizado"));

        return solicitante;
    }


    //update
    public Solicitante update(Long id, PessoaModel pessoa){
        Solicitante solicitante = solicitanteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe solicitante com esse ID"));

        //Altera as informações requiridas
        solicitante.setNome(pessoa.getNome());
        solicitante.setAnoNascimento(pessoa.getAnoNascimento());
        solicitante.setTelefone(pessoa.getTelefone());
        solicitante.setAvatar(pessoa.getAvatar());

        solicitante = solicitanteRepository.save(solicitante);

//        solicitante.setSenha(null);
        return solicitante;
    }

    //update login
    //TODO: Aprimorar método: deixar mais seguro, poder trocar com e sem senha antiga, com codigo por sms ou email
    public PessoaLoginModel updateLogin(Long id, PessoaLoginModel login) {
        Solicitante solicitante = solicitanteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe solicitante com esse ID"));

        Optional<Solicitante> solicitanteEmail = solicitanteRepository.findByEmail(login.getEmail());
        if (solicitanteEmail.isPresent()){
            throw new DomainException("Já existe um cliente com esse email");
        }

        solicitante.setSenha(login.getSenha());
        solicitante.setEmail(login.getEmail());
        solicitanteRepository.save(solicitante);

//        login.setSenha(null);
        return login;
    }

    //delete
    public void delete(Long id){
        if(!solicitanteRepository.existsById(id)){
            new NotFoundException("Não existe cliente com esse ID");
        }

        solicitanteRepository.deleteById(id);
    }
}
