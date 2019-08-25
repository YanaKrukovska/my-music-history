package com.ritacle.mhistory.persistence.model.stats;

import org.hibernate.annotations.Immutable;

import java.util.StringJoiner;

@Immutable
public abstract class ListenAbs {

    private String title;
    private String album;
    private String artist;
    private String userMail;


    public ListenAbs() {
    }

    public ListenAbs(String title, String album, String artist, String userMail) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.userMail = userMail;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ListenAbs.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("album='" + album + "'")
                .add("artist='" + artist + "'")
                .add("userMail='" + userMail + "'")
                .toString();
    }
}
