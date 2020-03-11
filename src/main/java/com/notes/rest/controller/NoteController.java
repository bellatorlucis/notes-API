package com.notes.rest.controller;

import com.notes.rest.exception.NoteNotFoundException;
import com.notes.rest.model.Note;
import com.notes.rest.model.User;
import com.notes.rest.service.api.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<Note>>> findAllNotes(){
        List<EntityModel<Note>> notes = noteService.findAllNotes()
                                                    .stream()
                                                    .map( note -> new EntityModel<>(
                                                                 note,
                                                                 linkTo(methodOn(NoteController.class).findNoteWithId(note.getNoteId())).withSelfRel(),
                                                                 linkTo(methodOn(NoteController.class).findAllNotes()).withRel("all-notes")))
                                                    .collect(Collectors.toList());

        return ResponseEntity.ok(
                new CollectionModel<>(notes,
                        linkTo(methodOn(NoteController.class).findAllNotes()).withSelfRel()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Note>> findNoteWithId(@PathVariable int id){
        return noteService.findNoteWithId(id)
                          .map( note -> new EntityModel<>(
                                  note,
                                  linkTo(methodOn(NoteController.class).findNoteWithId(id)).withSelfRel(),
                                  linkTo(methodOn(NoteController.class).findAllNotes()).withRel("all-notes")))
                          .map(ResponseEntity::ok)
                          .orElseThrow(() -> new NoteNotFoundException("Note with id : "+id+" does not exist"));
    }


    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }
}
