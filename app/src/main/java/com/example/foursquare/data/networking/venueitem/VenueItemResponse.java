package com.example.foursquare.data.networking.venueitem;

import com.example.foursquare.data.networking.venueitem.models.ResponseItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueItemResponse {

    @SerializedName("response")
    @Expose
    private ResponseItem responseItem;

    public ResponseItem getResponseItem() {
        return responseItem;
    }

    public void setResponse(ResponseItem responseItem) {
        this.responseItem = responseItem;
    }

}
