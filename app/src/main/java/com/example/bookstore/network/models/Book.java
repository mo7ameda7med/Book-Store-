package com.example.bookstore.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Book implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("discount")
    @Expose
    private int discount;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getAboutAuthor() {
        return aboutAuthor;
    }

    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @SerializedName("bookDescription")
    @Expose
    private String bookDescription;
    @SerializedName("aboutAuthor")
    @Expose
    private String aboutAuthor;
    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("totalRating")
    @Expose
    private int totalRating;
    @SerializedName("isActive")
    @Expose
    private boolean isActive;

    public Book(String id, String name, String image, int discount, double price, String url, String author, String bookDescription, String aboutAuthor, float rating, int totalRating, boolean isActive) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.discount = discount;
        this.price = price;
        this.url = url;
        this.author = author;
        this.bookDescription = bookDescription;
        this.aboutAuthor = aboutAuthor;
        this.rating = rating;
        this.totalRating = totalRating;
        this.isActive = isActive;
    }
}
