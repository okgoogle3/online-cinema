package com.cinema.repo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "films")
public final class Film {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long videoId;

    @NotNull
    private String title;

    private String producer;
    private String date;
    private long boxOffice;

    public Film(){
    }

    public Film(long videoId, String title, String producer, String date, long boxOffice) {
        this.videoId = videoId;
        this.title = title;
        this.producer = producer;
        this.date = date;
        this.boxOffice = boxOffice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(long boxOffice) {
        this.boxOffice = boxOffice;
    }
}
