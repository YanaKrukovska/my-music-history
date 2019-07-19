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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", foreignKey = @ForeignKey(name = "ARTIST_ID_FK"))
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", foreignKey = @ForeignKey(name = "ALBUM_ID_FK"))
    private Album album;

    public Song() {
    }

    public Song(String title) {
        this.title = title;

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


    @Override
    public String toString() {
        return String.format("Song: id = %s, title = %s", id, title ) ;
    }
}
