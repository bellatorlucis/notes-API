package com.notes.rest.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Note {
    private int noteId;
    private String title;
    private String text;

    @Id
    @Column(name = "note_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @Basic
    @Column(name = "title", length = 30)
    @Length(max = 30, message = "Title must be less than 30 characters")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "text", length = 50)
    @Length(max= 50, message = "Text must be less then 50 characters")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return noteId == note.noteId &&
                Objects.equals(title, note.title) &&
                Objects.equals(text, note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, title, text);
    }
}
