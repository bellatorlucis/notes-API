package com.notes.rest.service.api;

import com.notes.rest.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Optional<Note> findNoteWithId(int id);
    List<Note> findAllNotes();
}
