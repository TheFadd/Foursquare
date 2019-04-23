package com.example.foursquare.data.networking.venueitem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupTips {

    @SerializedName("items")
    @Expose
    private List<ItemTips> itemTips = null;

    public List<ItemTips> getItemTips() {
        return itemTips;
    }

    public void setItemTips(List<ItemTips> itemTips) {
        this.itemTips = itemTips;
    }
}
