package com.ritacle.mhistory.persistence.model.stats;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.util.Date;

@Entity
@Subselect("SELECT * FROM song_stats_by_date")
@Immutable
@Table(name = "song_stats_by_date")

public class LastListen {

    @Id
    @GeneratedValue
    private Long id;


    private String title;

    private String artist;

    private String album;


     private String userMail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date listenDate;


    public LastListen() {
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Date getDate() {
        return listenDate;
    }

    public void setDate(Date listenDate) {
        this.listenDate = listenDate;
    }

    @Override
    public String toString() {
        return String.format("Last listen :title = %s, artist = %s, album = %s",  title, artist, album);
    }
}
