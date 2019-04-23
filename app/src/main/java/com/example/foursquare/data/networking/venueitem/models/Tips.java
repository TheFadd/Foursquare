package com.example.foursquare.data.networking.venueitem.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tips {

    @SerializedName("groups")
    @Expose
    private List<GroupTips> grouptips = null;

    public List<GroupTips> getGroups() {
        return grouptips;
    }

    public void setGroupTips(List<GroupTips> groupTips) {
        this.grouptips = groupTips;
    }

}
