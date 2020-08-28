package com.somei.apisomei.resources;

import com.somei.apisomei.model.Commentary;
import com.somei.apisomei.model.Note;
import com.somei.apisomei.service.CrudCommentaryService;
import com.somei.apisomei.service.CrudNoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/note")
@Api(value = "API REST SOMEI")
@CrossOrigin(origins = "*")
public class NoteResource {

    @Autowired
    CrudNoteService noteService;

    @Autowired
    CrudCommentaryService commentaryService;


    @GetMapping
    @ApiOperation(value = "Retorna uma lista com todas as notas")
    public List<Note> getallNotes(){
        return noteService.readAll();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna uma nota a partir do id")
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") long id){
        Note note = noteService.read(id);
        if (note != null){
            return ResponseEntity.ok(note);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma nota")
    public Note createNote(@Valid @RequestBody Note note){
        return noteService.create(note);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Edita uma nota")
    public ResponseEntity<Note> editNote(@PathVariable(value = "id") long id, @Valid @RequestBody Note note){
        Note noteEdit = noteService.update(id, note);
        if(noteEdit !=  null){
            return ResponseEntity.ok(noteEdit);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta uma nota")
    public ResponseEntity<Void> deleteNote(@PathVariable(value = "id") long id){
        if(noteService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/commentary")
    @ApiOperation(value = "Adiciona um comentário em uma nota")
    public ResponseEntity<Commentary> addCommentary(@RequestBody Commentary commentary, @RequestParam(value = "note") long id){
        Note note = noteService.read(id);
        if (note != null){
            commentary.setNote(note);
            return ResponseEntity.ok(commentaryService.create(commentary));
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/commentary")
    @ApiOperation(value = "Obtem todos os comentarios de uma nota")
    public ResponseEntity<List<Commentary>> getAllCommenties(@RequestParam(value = "note") long id){
        Note note = noteService.read(id);
        if (note != null){
            List<Commentary> commentaries = commentaryService.readByNote(note);
            if (commentaries != null){
                return ResponseEntity.ok(commentaries);
            }
        }

        return ResponseEntity.notFound().build();
    }

}
