package com.example.foursquare.data.networking.venueitem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttributesGroup {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
