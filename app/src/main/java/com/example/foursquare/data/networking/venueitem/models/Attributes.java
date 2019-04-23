package com.example.foursquare.data.networking.venueitem.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attributes {

    @SerializedName("groups")
    @Expose
    private List<AttributesGroup> attributesGroups;

    public List<AttributesGroup> getAttributesGroups() {
        return attributesGroups;
    }

    public void setAttributesGroups(List<AttributesGroup> attributesGroups) {
        this.attributesGroups = attributesGroups;
    }
}