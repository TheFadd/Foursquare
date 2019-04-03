package com.example.foursquare.data.networking.venuelist;

import com.example.foursquare.data.networking.venuelist.json.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseFromListVenue {

    @SerializedName("response")
    @Expose
    private Response response;

    public ResponseFromListVenue(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }


}

