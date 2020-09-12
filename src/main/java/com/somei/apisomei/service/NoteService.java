package com.somei.apisomei.service;

import com.somei.apisomei.model.Note;
import com.somei.apisomei.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Classe usada para aplicação de regra de negócio
@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    public Note create(Note note){
        return noteRepository.save(note);
    }

    public List<Note> readAll(){
        return noteRepository.findAll();
    }

    public Note read(Long id){
        Optional<Note> note = noteRepository.findById(id);
        if(note.isPresent()){
            return note.get();
        }
        return null;
    }

    public boolean delete(Long noteId){
        if(noteRepository.existsById(noteId)){
            noteRepository.deleteById(noteId);
            return true;
        }
        return false;
    }

    public Note update(Long id, Note note){
        if(noteRepository.existsById(id)){
            note.setId(id);
            note = noteRepository.save(note);
            return note;
        }
        return null;
    }
}
