package com.somei.apisomei.service;

import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.CategoriaMei;
import com.somei.apisomei.model.representationModel.CategoriaMeiNovaModel;
import com.somei.apisomei.repository.CategoriaMeiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaMeiService {

    @Autowired
    CategoriaMeiRepository categoriaMeiRepository;

    //CREATE
    public CategoriaMei create(CategoriaMei categoriaMei){
        if(categoriaMeiRepository.existsByTitulo(categoriaMei.getTitulo()))
            throw new DomainException("Já existe categoria com esse título");

        return categoriaMeiRepository.save(categoriaMei);
    }

    //CREATE MASSIVE
    public List<CategoriaMei> create(List<CategoriaMeiNovaModel> categoriaMeiNovaModels){
        List<CategoriaMei> categoriasCriadas = new ArrayList<>();
        for(CategoriaMeiNovaModel categoria : categoriaMeiNovaModels){
            if(!categoriaMeiRepository.existsByTitulo(categoria.getTitulo()))
                categoriasCriadas.add(categoriaMeiRepository.save(categoria.byModel()));
        }
        System.out.println("\n\nQuantidade de categorias criadas");
        System.out.println(categoriasCriadas.size());
        System.out.println("\n\n");

        if(categoriasCriadas.size() == 0)
            throw new DomainException("Nenhuma categoria foi criada pois já estão cadastradas");
        else
            return categoriasCriadas;
    }

    //READ ALL
    public List<CategoriaMei> readAll(){
        return categoriaMeiRepository.findAll();
    }

    //READ BY PROFISSIONAL CADASTRADO
    public List<CategoriaMei> readByProfissionalAtivo(){
        List<CategoriaMei> categoriasAll = categoriaMeiRepository.findAll();
        List<CategoriaMei> categoriasAtivas = new ArrayList<>();
        categoriasAll.forEach(c -> {
            if (c.getProfissional().size() != 0)
                categoriasAtivas.add(c);
        });

        if(categoriasAtivas.size() != 0)
            return categoriasAtivas;
        else
            throw new NotFoundException("Não há nenhum profissional cadastro");
    }

    //UPDATE
    public CategoriaMei update(long id, CategoriaMei categoriaMei){
        CategoriaMei categoriaOld = categoriaMeiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não localizada"));
        categoriaMei.setId(id);

        return categoriaMeiRepository.save(categoriaMei);
    }

    //DELETE
    public void delete(long id){
        if(categoriaMeiRepository.existsById(id))
            categoriaMeiRepository.deleteById(id);
        else
            throw new NotFoundException("Categoria não localizada");
    }


    public List<CategoriaMei> readByTitulo(String titulo) {
        List<CategoriaMei> categorias = categoriaMeiRepository.findByTituloContainsIgnoreCase(titulo);
//                .orElseThrow(() -> new NotFoundException("Categoria não localizada"));

        return categorias;
    }
}
