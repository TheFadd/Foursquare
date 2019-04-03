package com.example.foursquare.data.networking;

import com.example.foursquare.data.networking.venueitem.MessageJSON;
import com.example.foursquare.data.networking.venuelist.ResponseFromListVenue;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class VenueListAPI {
    private static Retrofit retrofit = null;
    public static final String CLIENT_ID = "OQ2UKWU5IDQ352QI5DLXLNWOW0TBBQFNFHR4B124I1WJHAFW";
    public static final String CLIENT_SECRET = "RW4APTVSUJEKTLGXPZZLRC2NQLOTABX4AY0IJKUDFF3EI2RH";

//    public static final String CLIENT_ID = "2DP2DH34V3XXS3UL5DQZTTPSKJZJOJ3UUK1NXCANLSZOHYME";
//    public static final String CLIENT_SECRET = "DQKTWOWFSHIOQHO3VOJ2VCSABKC3B5FTJGRHLJ4040EKWYX4";

//    public static final String CLIENT_ID = "USMYA2TIZJOSXYNNVLRGTAJGYPLCFSMJREC2XQOYZ41SIVAK";
//    public static final String CLIENT_SECRET = "I0WSUFRQGJII0HG0AXEFJP42ERWW4PVGJEJRPP1G05RIYJ4K";

//    public static final String CLIENT_ID = "AOKTZUX2EJNDTUWN5MYOFCMHIIIFSZQMJ3YQ0NGNAOCRVNPW";
//    public static final String CLIENT_SECRET = "LQMKTCNI2SWMWF5GU2QAI11QV12IGS3R40JRULAUHTSGVFUY";

    public static final String VERSION = "20180323";
    public static final String BASE_URL = "https://api.foursquare.com/v2/venues/";

    public interface ApiInterface {

        @GET("explore")
        Call<ResponseFromListVenue> getVenueList(
                @Query("client_id") String client_ID,
                @Query("client_secret") String client_Secret,
                @Query("v") String version,
                @Query("ll") String coordinates,
                @Query("query") String query
        );


        @GET()
        Call<MessageJSON> getVenue(
                @Url String venue_ID,
                @Query("client_id") String client_ID,
                @Query("client_secret") String client_Secret,
                @Query("v") String version
        );
    }


    public static Retrofit getClientVenueList() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientVenueItem(String id) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL + id + "?")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
