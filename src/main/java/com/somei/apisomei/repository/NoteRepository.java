package com.somei.apisomei.repository;

import com.somei.apisomei.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findById(long id);
}
