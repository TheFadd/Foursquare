package com.example.foursquare.data.networking.venueitem.jsononeitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseItem {

    @SerializedName("venue")
    @Expose
    private VenueItem venueItem;

    public VenueItem getVenueItem() {
        return venueItem;
    }

    public void setVenueItem(VenueItem venueItem) {
        this.venueItem = venueItem;
    }

}
