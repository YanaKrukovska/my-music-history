package com.ritacle.mhistory.persistence.model.stats;

import org.hibernate.annotations.Immutable;

@Immutable
public class TopAlbum {

    private int listenCount;
    private String albumName;
    private String artist;
    private String userMail;

    public TopAlbum() {
    }

    public TopAlbum(int listenCount) {
        this.listenCount = listenCount;
    }

    public TopAlbum(String albumName, String artist, String userMail, int listenCount) {
        this.albumName = albumName;
        this.artist = artist;
        this.userMail = userMail;
        this.listenCount = listenCount;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
    }

    @Override
    public String toString() {
        return "TopAlbum {" +
                "name='" + albumName + ", artist = , " + artist + ", listenCount=" + listenCount +
                '}';
    }
}
