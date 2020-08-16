package com.somei.apisomei.resources;

import com.somei.apisomei.model.Commentary;
import com.somei.apisomei.model.Note;
import com.somei.apisomei.repository.CommentaryRepository;
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

    @Autowired
    CommentaryRepository commentaryRepository;

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

    @PostMapping("/note/commentary")
    public Commentary addCommentary(@RequestBody Commentary commentary, @RequestParam(value = "note") long id){
        Note note = noteRepository.findById(id);
        commentary.setNote(note);
        return commentaryRepository.save(commentary);
    }

    @GetMapping("/note/commentaries")
    public List<Commentary> getAllCommenties(@RequestParam(value = "note") long id){
        Note note = noteRepository.findById(id);
        return commentaryRepository.findByNote(note);
    }

}
