package com.example.foursquare.data.networking.venueitem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("currency")
    @Expose
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Price(String currency) {
        this.currency = currency;
    }
}
