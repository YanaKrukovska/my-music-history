package com.ritacle.mhistory.persistence.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.StringJoiner;

public class ListenAmount extends ListenAbs {

    private int listenCount;

    public ListenAmount() {
    }

    public ListenAmount(int listenCount) {
        this.listenCount = listenCount;
    }

    public ListenAmount(String title, String album, String artist, String userMail, int listenCount) {
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
        return new StringJoiner(", ", ListenAmount.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("listenCount=" + listenCount)
                .toString();
    }
}
