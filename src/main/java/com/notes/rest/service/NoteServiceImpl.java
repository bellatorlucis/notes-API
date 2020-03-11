package com.notes.rest.service;

import com.notes.rest.model.Note;
import com.notes.rest.repository.NoteJpaRepository;
import com.notes.rest.service.api.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    private NoteJpaRepository noteJpaRepository;

    public Optional<Note> findNoteWithId(int id){
        return noteJpaRepository.findById(id);
    }

    public List<Note> findAllNotes(){
        return noteJpaRepository.findAll();
    }

    @Autowired
    public void setNoteJpaRepository(NoteJpaRepository noteJpaRepository) {
        this.noteJpaRepository = noteJpaRepository;
    }
}
