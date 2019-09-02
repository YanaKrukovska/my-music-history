package com.ritacle.mhistory.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "song_constraint", columnNames = {"title", "album_id"})
)

public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", foreignKey = @ForeignKey(name = "ALBUM_ID_FK"))
    private Album album;

    public Song() {
    }

    public Song(String title, Album album) {
        this.title = title;
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
