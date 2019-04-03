package com.example.foursquare.ui.detailadapterview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.foursquare.R;
import com.example.foursquare.data.networking.VenueListAPI;
import com.example.foursquare.data.networking.venueitem.MessageJSON;
import com.example.foursquare.data.networking.venueitem.jsononeitem.AttributesGroup;
import com.example.foursquare.data.networking.venueitem.jsononeitem.ItemTips;
import com.example.foursquare.data.networking.venueitem.jsononeitem.ResponseItem;
import com.example.foursquare.ui.adapterutils.Helper;
import com.example.foursquare.ui.mainactivity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class DetailActivity extends AppCompatActivity {

    private String discription = "";
    private InfoFromItemVenueList infoFromMain;
    private double latitude;
    private double longtitude;
    private String client_id = VenueListAPI.CLIENT_ID;
    private String client_secret = VenueListAPI.CLIENT_SECRET;
    private String version = VenueListAPI.VERSION;
    private String id;
    private VenueListAPI.ApiInterface api;
    private ProgressBar loadIndicator;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initParams();
        makeInternetRequest();
    }

    private void makeInternetRequest() {


        Call<MessageJSON> callMessageJSON = api.getVenue(id, client_id, client_secret, version);
        callMessageJSON.enqueue(new Callback<MessageJSON>() {
            @Override
            public void onResponse(@NonNull Call<MessageJSON> call, @NonNull Response<MessageJSON> response) {
                Log.e("RESPONSE", "РЕСПОНС ОКК");
                List<AttributesGroup> list = response.body().getResponseItem().getVenueItem().getAttributes().getAttributesGroups();
                checkDiscription(list);
                List<ItemTips> tipslist = response.body().getResponseItem().getVenueItem().getTips().getGroups().get(0).getItemTips();
                ResponseItem item = response.body().getResponseItem();
                ResponseItem finalItem = Helper.checkItem(item);
                String photo = convertBestPhotoToString(finalItem);
                DetailInformation detailInformation = DetailInformation.from(infoFromMain, finalItem,
                        discription, tipslist, photo);
                initRecyclerView(detailInformation);
            }

            @Override
            public void onFailure(Call<MessageJSON> call, Throwable t) {
                Log.e("Response", "onFailure");
            }
        });
    }

    private void initRecyclerView(DetailInformation detailInformation) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DetailAdapter(createDummyList(detailInformation), DetailActivity.this, latitude, longtitude));
        recyclerView.setVisibility(VISIBLE);
        loadIndicator.setVisibility(GONE);
    }

    private List<RecycleViewItem> createDummyList(DetailInformation detailInformation) {
        List<RecycleViewItem> recyclerViewItems = new ArrayList<>();
        Header header = new Header(detailInformation);
        recyclerViewItems.add(header);

        for (int i = 0; i < detailInformation.getTips().size(); i++) {
            Tiper foodItem = new Tiper(detailInformation.getTips().get(i));
            recyclerViewItems.add(foodItem);
        }
        return recyclerViewItems;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initParams(){
        loadIndicator = findViewById(R.id.load_indicator);
        recyclerView = findViewById(R.id.recyclerview_comment);
        recyclerView.setVisibility(GONE);
        loadIndicator.setVisibility(VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        id = getIntent().getStringExtra(MainActivity.VENUE_ID);
        String venueName = getIntent().getStringExtra(MainActivity.VENUE_NAME);
        String venueShorName = getIntent().getStringExtra(MainActivity.VENUE_SHORT_NAME);
        String adressWithDistance = getIntent().getStringExtra(MainActivity.VENUE_ADRESS_WITH_DISTANCE);
        String prefix = getIntent().getStringExtra(MainActivity.VENUE_PREFIX);
        String syfix = getIntent().getStringExtra(MainActivity.VENUE_SYFIX);
        String lat = getIntent().getStringExtra(MainActivity.VENUE_LATITUDE);
        String lon = getIntent().getStringExtra(MainActivity.VENUE_LONGTITUDE);

        infoFromMain = new InfoFromItemVenueList(id,venueName,venueShorName,adressWithDistance,prefix,syfix,lat,lon);
        String title = infoFromMain.getName();
        this.setTitle(title);
        latitude = Double.valueOf(infoFromMain.getLatitude());// Double.valueOf(infoFromMain.getLatitude());
        longtitude = Double.valueOf(infoFromMain.getLongtitude());
        api = VenueListAPI.getClientVenueItem(id).create(VenueListAPI.ApiInterface.class);


    }
    private void checkDiscription( List<AttributesGroup> list){
      for (AttributesGroup ag : list) {
          if (discription.equals("")) {
              discription = ag.getName();
          } else
              discription = discription + ", " + ag.getName();
      }
    }

    private static String convertBestPhotoToString(ResponseItem item){
       String bestPhoto =item.getVenueItem().getBestPhoto().getPrefix()+ item.getVenueItem().getBestPhoto().getWidth()+"x"+
                item.getVenueItem().getBestPhoto().getHeight()+item.getVenueItem().getBestPhoto().getSuffix();
       return bestPhoto;
    }
}
