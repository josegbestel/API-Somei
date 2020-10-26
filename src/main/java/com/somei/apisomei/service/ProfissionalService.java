package com.somei.apisomei.service;


import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.CategoriaMei;
import com.somei.apisomei.model.Financeiro;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.dto.CompanyNfeDTO;
import com.somei.apisomei.model.enums.AuthType;
import com.somei.apisomei.model.representationModel.PessoaModel;
import com.somei.apisomei.model.representationModel.PessoaLoginModel;
import com.somei.apisomei.model.representationModel.ProfissionalModel;
import com.somei.apisomei.model.representationModel.ProfissionalPerfilModel;
import com.somei.apisomei.repository.CategoriaMeiRepository;
import com.somei.apisomei.repository.FinanceiroRepository;
import com.somei.apisomei.repository.ProfissionalRepository;
import com.somei.apisomei.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Autowired
    CategoriaMeiRepository categoriaMeiRepository;

    @Autowired
    FinanceiroRepository financeiroRepository;

    @Autowired
    NfeService nfeService;

    //Create
    public Profissional create(ProfissionalModel profissionalModel){
        Optional<Profissional> profExistente = profissionalRepository.findByEmail(profissionalModel.getEmail());
        if(profExistente.isPresent()){
            throw new DomainException("Já existe um profissional com esse email");
        }

        profExistente = profissionalRepository.findByCnpj(profissionalModel.getCnpj());
        if (profExistente.isPresent()){
            throw new DomainException("Já existe um profissional com esse CNPJ");
        }

        CategoriaMei categoria = categoriaMeiRepository.findByTitulo(profissionalModel.getCategoriaTitulo())
                .orElseThrow(() -> new NotFoundException("Categoria não localizada"));

        Profissional profissional = profissionalModel.byModel(categoria);
        profissional.setAuthType(AuthType.USUARIO);
        profissional.setAtivo(true);
        profissional.setSenha(PasswordEncoder.encode(profissional.getSenha()));

        //Salvar empresa no NFe.io
        CompanyNfeDTO companyNfe = nfeService.obterEmpresa(new CompanyNfeDTO(profissional));

        //Referenciar ID NFe.io no Profisisonal
        profissional.setIdNfe(companyNfe.getId());

        //Cria um financeiro
        Financeiro financeiro = new Financeiro();
        financeiro.setProfissional(profissional);
        financeiro.setMetaMensal(profissionalModel.getMetaMensal());

        //Salvar profissional no BD
        profissional = profissionalRepository.save(profissional);

        //Vincular financeiro com o profissional
        financeiro = financeiroRepository.save(financeiro);
        profissional.setFinanceiro(financeiro);
        profissional = profissionalRepository.save(profissional);

        return profissional;
    }

    //Read resumo perfil
    public ProfissionalPerfilModel readResumeProfile(long id){
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profissional não localizado"));
        return ProfissionalPerfilModel.toModel(profissional);
    }

    //TODO: Create despesa

    //TODO: Create deposito

    //TODO: Relatorio Financeiro

    //Read
    public Profissional read(Long id){
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe profissional com esse ID"));
    }

    //Read by cnpj
    public Profissional readByCnpj(String cnpj){
        return profissionalRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new NotFoundException("Não existe profissional com esse ID"));
    }

    //read by email
    public Profissional readByEmail(String email){
        return profissionalRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Cliente não localizado"));
    }

    //Read by categoria
    public List<Profissional> readByCategoria(Long categoriaId){
        return profissionalRepository.findByCategoriaId(categoriaId)
                .orElseThrow(() -> new NotFoundException("Profissionais não localizados nesta categoria"));
    }

    //TODO: Read perfil

    //TODO: Read resumo


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
