package com.somei.apisomei.resources;

import com.somei.apisomei.models.Note;
import com.somei.apisomei.repository.NoteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST SOMEI")
@CrossOrigin(origins = "*")
public class NoteResource {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    @ApiOperation(value = "Retorna uma lista com todas as notas")
    public List<Note> getallNotes(){
        return noteRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    @ApiOperation(value = "Retorna uma nota a partir do id")
    public Note getNoteById(@PathVariable(value = "id") long id){
        return noteRepository.findById(id);
    }

    @PostMapping("/note")
    @ApiOperation(value = "Cria uma nota")
    public Note createNote(@RequestBody Note note){
        return noteRepository.save(note);
    }

    @PutMapping("/note")
    @ApiOperation(value = "Edita uma nota")
    public Note editNote(@RequestBody Note note){
        return noteRepository.save(note);
    }

    @DeleteMapping("/note/{id}")
    @ApiOperation(value = "Deleta uma nota")
    public void deleteNote(@PathVariable(value = "id") long id){
        noteRepository.deleteById(id);
    }
}
