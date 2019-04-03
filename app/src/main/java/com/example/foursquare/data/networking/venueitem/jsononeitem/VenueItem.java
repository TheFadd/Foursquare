package com.example.foursquare.data.networking.venueitem.jsononeitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueItem {



    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("ratingColor")
    @Expose
    private String ratingColor;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("tips")
    @Expose
    private Tips tips;
    @SerializedName("bestPhoto")
    @Expose
    private BestPhoto bestPhoto;




    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }




    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public Tips getTips() {
        return tips;
    }

    public void setTips(Tips tips) {
        this.tips = tips;
    }

    public BestPhoto getBestPhoto() {
        return bestPhoto;
    }

    public void setBestPhoto(BestPhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
    }



}


