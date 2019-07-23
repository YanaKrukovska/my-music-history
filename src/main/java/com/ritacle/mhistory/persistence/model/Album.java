package com.ritacle.mhistory.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id", foreignKey = @ForeignKey(name = "ARTIST_ID_FK"))
    private Artist artist;

    @Column
    private String genre;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    public Album() {
    }

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return String.format("Album: id = %s, title = %s, genre = %s, release date = %s ", id, title, genre, releaseDate);
    }
}
