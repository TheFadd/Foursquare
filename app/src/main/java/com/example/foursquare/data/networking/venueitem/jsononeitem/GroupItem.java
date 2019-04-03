package com.example.foursquare.data.networking.venueitem.jsononeitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupItem {

    @SerializedName("items")
    @Expose
    private List<ItemVenue> items = null;


    public List<ItemVenue> getItems() {
        return items;
    }

    public void setItems(List<ItemVenue> items) {
        this.items = items;
    }

}
