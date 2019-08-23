package com.ritacle.mhistory.persistence.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Subselect("SELECT * FROM song_stats_by_date")
@Immutable
@Table(name = "song_stats_by_date")
public class LastListen extends ListenAbs {

    private Date dateListen;

    @Id
    @GeneratedValue
    private Long id;


    public LastListen(String title, String album, String artist, String userMail, Date dateListen) {
        super(title, album, artist, userMail);
        this.dateListen = dateListen;
    }

    public LastListen() {
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateListen() {
        return dateListen;
    }

    public void setDateListen(Date dateListen) {
        this.dateListen = dateListen;
    }
}
