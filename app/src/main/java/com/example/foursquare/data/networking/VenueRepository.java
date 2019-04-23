package com.example.foursquare.data.networking;

import com.example.foursquare.data.networking.venueitem.VenueItemResponse;
import com.example.foursquare.data.networking.venuelist.VenueListResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VenueRepository {

//    public static final String CLIENT_ID = "OQ2UKWU5IDQ352QI5DLXLNWOW0TBBQFNFHR4B124I1WJHAFW";
//    public static final String CLIENT_SECRET = "RW4APTVSUJEKTLGXPZZLRC2NQLOTABX4AY0IJKUDFF3EI2RH";

    public static final String CLIENT_ID = "2DP2DH34V3XXS3UL5DQZTTPSKJZJOJ3UUK1NXCANLSZOHYME";
    public static final String CLIENT_SECRET = "DQKTWOWFSHIOQHO3VOJ2VCSABKC3B5FTJGRHLJ4040EKWYX4";

//    public static final String CLIENT_ID = "USMYA2TIZJOSXYNNVLRGTAJGYPLCFSMJREC2XQOYZ41SIVAK";
//    public static final String CLIENT_SECRET = "I0WSUFRQGJII0HG0AXEFJP42ERWW4PVGJEJRPP1G05RIYJ4K";

//    public static final String CLIENT_ID = "AOKTZUX2EJNDTUWN5MYOFCMHIIIFSZQMJ3YQ0NGNAOCRVNPW";
//    public static final String CLIENT_SECRET = "LQMKTCNI2SWMWF5GU2QAI11QV12IGS3R40JRULAUHTSGVFUY";

    public static final String VERSION = "20180323";
    public static final String BASE_URL = "https://api.foursquare.com/v2/venues/";

    private final VenueApi venueApi;

    public VenueRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        venueApi = retrofit.create(VenueApi.class);
    }

    public Observable<VenueListResponse> getVenueList(double latitude, double longitude, String query) {
        String coordinates = latitude + "," + longitude;
        return venueApi.getVenueList(CLIENT_ID, CLIENT_SECRET, VERSION, coordinates, query);
    }

    public Observable<VenueItemResponse> getVenue(String venueId) {
        return venueApi.getVenue(venueId, CLIENT_ID, CLIENT_SECRET, VERSION);
    }
}
