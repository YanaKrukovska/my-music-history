package com.ritacle.mhistory.persistence.model.stats;

import javax.persistence.Column;

public class SongStats {

    @Column(nullable = false)
    private String rank;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String album;

    public SongStats() {
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
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

    @Override
    public String toString() {
        return String.format("Song statistic: rank = %s, title = %s, artist = %s, album = %s ", rank, title, artist, album);
    }
}
