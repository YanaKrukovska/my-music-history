package com.ritacle.mhistory.persistence.model;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint( name = "song_constraint", columnNames = {"title", "artist", "album"})
)

public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String album;

    public Song() {
    }

    public Song(String title, String artist, String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return String.format("Song: id = %s, title = %s, artist = %s, album = %s", id, title, artist, album ) ;
    }
}
