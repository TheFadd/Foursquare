package com.example.foursquare.data.networking;

import com.example.foursquare.data.networking.venueitem.VenueItemResponse;
import com.example.foursquare.data.networking.venuelist.VenueListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VenueApi {

    @GET("explore")
    Observable<VenueListResponse> getVenueList(
            @Query("client_id") String client_ID,
            @Query("client_secret") String client_Secret,
            @Query("v") String version,
            @Query("ll") String coordinates,
            @Query("query") String query
    );

    @GET("{venue_id}")
    Observable<VenueItemResponse> getVenue(
            @Path("venue_id") String venueId,
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("v") String version
    );
}