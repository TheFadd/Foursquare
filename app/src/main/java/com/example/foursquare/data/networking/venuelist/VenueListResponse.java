package com.example.foursquare.data.networking.venuelist;

import com.example.foursquare.data.networking.venuelist.models.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VenueListResponse {

    @SerializedName("response")
    @Expose
    private Response response;

    public VenueListResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

}

