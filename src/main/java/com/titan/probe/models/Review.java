/*
 *  Review
 *
 *  Author: 1412093
 *
 *  The entity class for reviews
 */

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
    @Column(name="timestamp")
    private Timestamp timestamp;
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="author")
    private User author;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="vendor")
    private Vendor vendor;

    public Review() {
    }

    public Review(float score, String content, Timestamp timestamp, User author, Vendor vendor) {
        this.score = score;
        this.content = content;
        this.timestamp = timestamp;
        this.author = author;
        this.vendor = vendor;
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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
