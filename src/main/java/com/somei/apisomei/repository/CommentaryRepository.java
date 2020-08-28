package com.somei.apisomei.repository;

import com.somei.apisomei.model.Commentary;
import com.somei.apisomei.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    Optional<List<Commentary>> findByNote(Note note);

}
