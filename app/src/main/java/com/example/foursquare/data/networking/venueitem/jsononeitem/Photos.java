package com.example.foursquare.data.networking.venueitem.jsononeitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {

    @SerializedName("groups")
    @Expose
    private List<GroupItem> groups = null;

    public List<GroupItem> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupItem> groups) {
        this.groups = groups;
    }
}