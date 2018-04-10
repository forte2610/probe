package com.titan.probe.models;

public class Product {
    private String name;
    private int price;
    private String vendorName;
    private String vendorURL;
    private String images;
    private String type;
    private String description;

    public Product() {
    }

    public Product(String name, int price, String vendorName, String vendorURL, String images, String type, String description) {
        this.name = name;
        this.price = price;
        this.vendorName = vendorName;
        this.vendorURL = vendorURL;
        this.images = images;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorURL() {
        return vendorURL;
    }

    public void setVendorURL(String vendorURL) {
        this.vendorURL = vendorURL;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


