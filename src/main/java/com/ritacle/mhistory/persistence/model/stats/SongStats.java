package com.ritacle.mhistory.persistence.model.stats;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.util.Date;

@Entity
@Subselect("SELECT * FROM song_stats_by_date")
@Immutable
public class SongStats {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int rank;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String album;

    @Column(nullable = false)
    private int listenAmount;

    @Column(nullable = false)
    private String userMail;

    @Temporal(TemporalType.DATE)
    private Date listenDate;


    public SongStats() {
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public int getListenAmount() {
        return listenAmount;
    }

    public void setListenAmount(int listenAmount) {
        this.listenAmount = listenAmount;
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
        return String.format("Song statistic: rank = %s, title = %s, artist = %s, album = %s, amount of listens = %s ", rank, title, artist, album, listenAmount);
    }
}
