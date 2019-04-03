package com.example.foursquare.data.networking.venuelist.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("venue")
    @Expose
    private Venue venue;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venues) {
        this.venue = venue;
    }
}
