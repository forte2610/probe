/*
 *  Vendor
 *
 *  Author: 1412093
 *
 *  The entity class for vendors
 */

package com.titan.probe.models;

import javax.persistence.*;

@Entity
@Table(name="VENDOR")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "description")
    private String description;
    @Column(name = "logo")
    private String logo;
    @Column(name="score")
    private float score;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;

    public Vendor() {
    }

    public Vendor(String name, String url, String description, String logo, float score, String phone, String email) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.logo = logo;
        this.score = score;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
