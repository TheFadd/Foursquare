package com.example.foursquare.networking.venueitem;

import com.example.foursquare.networking.venueitem.jsononeitem.ResponseItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageJSON {


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
