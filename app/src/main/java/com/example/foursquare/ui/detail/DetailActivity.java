package com.example.foursquare.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.foursquare.R;
import com.example.foursquare.data.networking.VenueRepository;
import com.example.foursquare.data.networking.venueitem.VenueItemResponse;
import com.example.foursquare.data.networking.venueitem.models.AttributesGroup;
import com.example.foursquare.data.networking.venueitem.models.BestPhoto;
import com.example.foursquare.data.networking.venueitem.models.ItemTips;
import com.example.foursquare.data.networking.venueitem.models.ResponseItem;
import com.example.foursquare.ui.GeneralUtil;
import com.example.foursquare.ui.detail.adapter.DetailAdapter;
import com.example.foursquare.ui.detail.adapter.HeaderCell;
import com.example.foursquare.ui.detail.adapter.TiperCell;
import com.example.foursquare.ui.detail.adapter.VenueRecyclerCell;
import com.example.foursquare.ui.list.VenueModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class DetailActivity extends AppCompatActivity {

    public final static String VENUE_KEY = "venue_key";
    private DetailAdapter adapter;

    public static void launch(Context context, VenueModel model) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(VENUE_KEY, model);
        context.startActivity(i);
    }

    private String description = "";
    private TotalInformation infoFromMain;
    private double latitude;
    private double longitude;
    private String id;
    private VenueRepository api;
    private ProgressBar loadIndicator;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initParams();
        startRequest();
    }

    private void initParams() {
        loadIndicator = findViewById(R.id.load_indicator);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setVisibility(GONE);
        loadIndicator.setVisibility(VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        VenueModel model = (VenueModel) getIntent().getSerializableExtra(VENUE_KEY);
        id = model.getCode();

        infoFromMain = new TotalInformation(id, model.getName(), model.getShortName(), model.getAddress(), model.getPrefix(), model.getSuffix(), model.getLatitude(), model.getLongitude());
        String title = infoFromMain.getName();
        setTitle(title);
        latitude = infoFromMain.getLatitude();
        longitude = infoFromMain.getLongitude();
        api = new VenueRepository();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailAdapter(this, latitude, longitude);
        recyclerView.setAdapter(adapter);
    }

    private void startRequest() {
        api.getVenue(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processResponse, this::handleError);
    }

    private void handleError(Throwable e) {
        Log.e("Response", "onFailure", e);
    }

    private void processResponse(VenueItemResponse it) {
        Log.e("RESPONSE", "РЕСПОНС ОКК");
        List<AttributesGroup> list = it.getResponseItem().getVenueItem().getAttributes().getAttributesGroups();
        checkDescription(list);
        List<ItemTips> tipsList = it.getResponseItem().getVenueItem().getTips().getGroups().get(0).getItemTips();
        ResponseItem item = it.getResponseItem();
        ResponseItem finalItem = GeneralUtil.checkItem(item);
        String photo = convertBestPhotoToString(finalItem);
        DetailInformation detailInformation = DetailInformation.from(infoFromMain, finalItem, description, tipsList, photo);
        initRecyclerView(detailInformation);
    }

    private void initRecyclerView(DetailInformation item) {
        recyclerView.setVisibility(VISIBLE);
        loadIndicator.setVisibility(GONE);
        adapter.swapData(createDummyList(item));
    }

    private List<VenueRecyclerCell> createDummyList(DetailInformation detailInformation) {
        List<VenueRecyclerCell> items = new ArrayList<>();
        items.add(new HeaderCell(detailInformation));
        for (ItemTips tips : detailInformation.getTips()) {
            items.add(new TiperCell(tips));
        }
        return items;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkDescription(List<AttributesGroup> list) {
        for (AttributesGroup ag : list) {
            if (description.equals("")) {
                description = ag.getName();
            } else {
                description = description + ", " + ag.getName();
            }
        }
    }

    private static String convertBestPhotoToString(ResponseItem item) {
        BestPhoto photo = item.getVenueItem().getBestPhoto();
        return photo.getPrefix() + photo.getWidth() + "x" + photo.getHeight() + photo.getSuffix();
    }
}
