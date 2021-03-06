package com.ritacle.mhistory.persistence.model.stats;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.util.Date;

@Entity
@Subselect("SELECT * FROM song_stats_by_date")
@Immutable
@Table(name = "song_stats_by_date")
public class LastListen extends ListenAbs {

    private Date listenDate;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public LastListen(Date listenDate) {
        this.listenDate = listenDate;
    }

    public LastListen(String title, String album, String artist, String userMail, Date listenDate) {
        super(title, album, artist, userMail);
        this.listenDate = listenDate;
    }

    public LastListen() {
    }

    public Date getListenDate() {
        return listenDate;
    }

    public void setListenDate(Date listenDate) {
        this.listenDate = listenDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
