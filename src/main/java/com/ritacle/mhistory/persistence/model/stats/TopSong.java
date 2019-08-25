package com.ritacle.mhistory.persistence.model.stats;

import org.hibernate.annotations.Immutable;

import java.util.StringJoiner;

@Immutable
public class TopSong extends ListenAbs {

    private int listenCount;

    public TopSong() {
    }

    public TopSong(int listenCount) {
        this.listenCount = listenCount;
    }

    public TopSong(String title, String album, String artist, String userMail, int listenCount) {
        super(title, album, artist, userMail);
        this.listenCount = listenCount;
    }


    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TopSong.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("listenCount=" + listenCount)
                .toString();
    }
}
