package com.ritacle.mhistory.persistence.model.stats;

import org.hibernate.annotations.Immutable;

@Immutable
public class TopArtist {

    private int listenCount;
    private String name;
    private String userMail;

    public TopArtist() {
    }

    public TopArtist(int listenCount) {
        this.listenCount = listenCount;
    }

    public TopArtist(String artistName, String userMail, int listenCount) {
        this.name = artistName;
        this.userMail = userMail;
        this.listenCount = listenCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "TopArtist {" +
                "name='" + name + ", listenCount=" + listenCount +
                '}';
    }
}
