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

    private Date dateListen;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public LastListen(Date dateListen) {
        this.dateListen = dateListen;
    }

    public LastListen(String title, String album, String artist, String userMail, Date dateListen) {
        super(title, album, artist, userMail);
        this.dateListen = dateListen;
    }

    public LastListen() {
    }

    public Date getDateListen() {
        return dateListen;
    }

    public void setDateListen(Date dateListen) {
        this.dateListen = dateListen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
