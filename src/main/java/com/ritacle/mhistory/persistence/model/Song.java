package com.ritacle.mhistory.persistence.model;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "song_constraint", columnNames = {"title", "artist_id", "album_id"})
)

public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", foreignKey = @ForeignKey(name = "ARTIST_ID_FK"))
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", foreignKey = @ForeignKey(name = "ALBUM_ID_FK"))
    private Album album;

    public Song() {
    }

    public Song(String title, Artist artist, Album album) {
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


    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return String.format("Song: id = %s, title = %s", id, title);
    }
}
