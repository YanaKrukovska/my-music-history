package com.ritacle.mhistory.persistence.model.stats;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Subselect("SELECT * FROM song_stats")
@Immutable
@Table(name = "song_stats")
public class SongStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int listenCount;
    private String title;
    private String artist;
    private String listener;

    public SongStatistic() {
    }

    public SongStatistic(int listenCount, String title, String artist, String listener) {
        this.listenCount = listenCount;
        this.title = title;
        this.artist = artist;
        this.listener = listener;
    }

    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
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

    public String getListener() {
        return listener;
    }

    public void setListener(String listener) {
        this.listener = listener;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SongStatistic: " +
                "listenCount = " + listenCount +
                ", title = '" + title + '\'' +
                ", artist = '" + artist + '\'' +
                ", listener = '" + listener + '\'' +
                '}';
    }
}
