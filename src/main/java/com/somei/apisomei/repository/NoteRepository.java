package com.somei.apisomei.repository;

import com.somei.apisomei.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findById(long id);
    Note findByTitle(String title);
//    Note findByTitleContaining(String title);
}
