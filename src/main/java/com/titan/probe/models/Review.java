package com.titan.probe.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="REVIEW")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="score")
    private float score;
    @Column(name="content")
    private String content;
    @Column(name="content")
    private Timestamp timestamp;
    @Column(name="author")
    @ManyToOne(cascade = CascadeType.ALL)
    private User author;

    public Review() {
    }

    public Review(float score, String content, Timestamp timestamp, User author) {
        this.score = score;
        this.content = content;
        this.timestamp = timestamp;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
