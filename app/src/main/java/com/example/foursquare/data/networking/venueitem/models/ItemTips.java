package com.example.foursquare.data.networking.venueitem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemTips {

    @SerializedName("createdAt")
    @Expose
    private long createdAt;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("likes")
    @Expose
    private Likes likes;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("photo")
    @Expose
    private BestPhoto bestPhoto;

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public BestPhoto getBestPhoto() {
        return bestPhoto;
    }

    public void setBestPhoto(BestPhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
    }

    public ItemTips(long createdAt, String text, Likes likes, User user, BestPhoto bestPhoto) {
        this.createdAt = createdAt;
        this.text = text;
        this.likes = likes;
        this.user = user;
        this.bestPhoto = bestPhoto;
    }
}
