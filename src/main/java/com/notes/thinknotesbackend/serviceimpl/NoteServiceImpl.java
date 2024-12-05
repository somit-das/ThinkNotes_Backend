package com.notes.thinknotesbackend.serviceimpl;

import com.notes.thinknotesbackend.entity.Note;
import com.notes.thinknotesbackend.repository.NoteRepository;
import com.notes.thinknotesbackend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setOwnerUsername(username);
        note.setContent(content);

        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String username, String content) {
        Note foundNote = noteRepository.findById(noteId).orElseThrow(()->new RuntimeException("Note not found"));
        foundNote.setContent(content);
        Note updatedNote = noteRepository.save(foundNote);
        return updatedNote;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Note foundNote = noteRepository.findNoteByIdAndOwnerUsername(noteId,username).orElseThrow(()->new RuntimeException("Note not found"));;
        noteRepository.delete(foundNote);

    }

    @Override
    public List<Note> getAllNotesForUser(String username) {
        return noteRepository.findNoteByOwnerUsername(username);
    }
}
