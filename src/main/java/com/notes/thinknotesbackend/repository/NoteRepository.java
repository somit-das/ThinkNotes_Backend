package com.notes.thinknotesbackend.repository;

import com.notes.thinknotesbackend.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findNoteByUsername(String username);

    Optional<Note> findNoteByIdAndUsername(Long id, String username);
}
