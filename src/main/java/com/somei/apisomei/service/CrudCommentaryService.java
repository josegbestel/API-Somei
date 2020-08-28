package com.somei.apisomei.service;

import com.somei.apisomei.model.Commentary;
import com.somei.apisomei.model.Note;
import com.somei.apisomei.repository.CommentaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudCommentaryService {

    CommentaryRepository commentaryRepository;

    public Commentary create(Commentary commentary){
        return commentaryRepository.save(commentary);
    }

    public List<Commentary> readAll(){
        return commentaryRepository.findAll();
    }

    public List<Commentary> readByNote(Note note){
        Optional<List<Commentary>> commentaries = commentaryRepository.findByNote(note);
        if(commentaries.isPresent()){
            return commentaries.get();
        }
        return null;
    }
}
