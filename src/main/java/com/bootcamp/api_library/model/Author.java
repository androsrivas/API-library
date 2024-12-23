package com.bootcamp.api_library.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "date_of_death")
    private Date dateOfDeath;

    @Column(name = "about")
    private String about;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author() {};

    public Author (String name, String surname, Date dateOfBirth, Date dateOfDeath, String about) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.about = about;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public String getAbout() {
        return about;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
