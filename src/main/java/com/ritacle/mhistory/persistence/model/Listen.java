package com.ritacle.mhistory.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Listen"
        ,uniqueConstraints = {@UniqueConstraint( name = "listener_id_constraint", columnNames = {"user_id"}),
        @UniqueConstraint( name = "sync_id_constraint", columnNames = {"sync_id"})}
)
public class Listen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "song_id", foreignKey = @ForeignKey(name = "SONG_ID_FK"))
    private Song song;

    @Column(name = "listen_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date listenDate;

    @Column(name = "sync_id", nullable = false)
    private long syncId;

    public Listen() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Date getListenDate() {
        return listenDate;
    }

    public void setListenDate(Date listenDate) {
        this.listenDate = listenDate;
    }

    public long getSyncId() {
        return syncId;
    }

    public void setSyncId(long syncId) {
        this.syncId = syncId;
    }

    @Override
    public String toString() {
        return "Listen: id = " + id + ", user = " + user + ", song = " + song + "listen date = " + listenDate;
    }
}
