package com.notes.rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    private int userId;
    private String username;
    private List<Note> notes;

    public User() {
    }
     public User(int id, String username){
        this.userId = id;
        this.username = username;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    @Length(max = 25, message = "Username must be under 20 characters" )
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(mappedBy = "user",  fetch =  FetchType.LAZY)
    @JsonBackReference
    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getNotes(), user.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getNotes());
    }
}
