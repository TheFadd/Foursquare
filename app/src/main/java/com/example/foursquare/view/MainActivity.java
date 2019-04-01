package com.example.foursquare.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.foursquare.R;
import com.example.foursquare.VenueItemToDataBase;
import com.example.foursquare.adapterUtils.Helper;
import com.example.foursquare.networking.InternetConnection;
import com.example.foursquare.networking.venuelist.ResponseFromListVenue;
import com.example.foursquare.networking.VenueListAPI;
import com.example.foursquare.networking.venuelist.json.Item;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

    private VenueListAPI.ApiInterface api;
    private final String TAG = "WEATHER";
    private VenueAdapter adapter;
    private RecyclerView recListVenueView;
    private RecyclerView.LayoutManager ll;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Boolean locationPermissionsGranted = false;
    private String latitute;
    private String longtitude ;
    private LinearLayout emptyView;
    private LinearLayout noConnectionView;
    private LinearLayout badQueryView;
    private ProgressBar progressBar;


    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyView = findViewById(R.id.empty_view);
        noConnectionView = findViewById(R.id.no_connection_view);
        badQueryView = findViewById(R.id.bad_query);
        progressBar = findViewById(R.id.load_indicator);

        recListVenueView = (RecyclerView) findViewById(R.id.recyclerview_forecast);
        onEmptyView();
        recListVenueView.setHasFixedSize(true);
        ll = new LinearLayoutManager(this);
        recListVenueView.setLayoutManager(ll);
        getLocationPermission();

        Context context = MainActivity.this;



//        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
//
//        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
//            buildAlertMessageNoGps();
//        }
    }
//    private void buildAlertMessageNoGps() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
//                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                    }
//                });
//        final AlertDialog alert = builder.create();
//        getDeviceLocation();
//        alert.show();
//    }


    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (locationPermissionsGranted) {
                final Task location = fusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                                latitute = Double.toString(currentLocation.getLatitude());
                                longtitude = Double.toString(currentLocation.getLongitude());

                        } else {
                           // Log.d(TAG, "onComplete: current location is null");
                          //  buildAlertMessageNoGps();
                            Toast.makeText(MainActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationPermissionsGranted = true;
                getDeviceLocation();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);

            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

    }

    public void startApp(String quary) {
        api = VenueListAPI.getClientVenueList().create(VenueListAPI.ApiInterface.class);
        String client_id = VenueListAPI.CLIENT_ID;
        String client_secret = VenueListAPI.CLIENT_SECRET;
        String version = VenueListAPI.VERSION;
        String coordinates = latitute + "," + longtitude;
        String qua = quary;
        onLoading();

        Call<ResponseFromListVenue> callVenueList = api.getVenueList(client_id, client_secret, version, coordinates, quary);

        callVenueList.enqueue(new Callback<ResponseFromListVenue>() {
            @Override
            public void onResponse(@NonNull Call<ResponseFromListVenue> call, @NonNull Response<ResponseFromListVenue> response) {
                Log.e(TAG, "РЕСПОНС ОКК");
                List<VenueItemToDataBase> listOfVenues = new ArrayList<>();
                for (Item item : response.body().getResponse().getGroups().get(0).getItems()) {
                    listOfVenues.add(VenueItemToDataBase.from(item));
                }
                adapter = new VenueAdapter(listOfVenues, MainActivity.this, venueItemToDataBase -> {
                    startDetail(venueItemToDataBase);
                });
                if (!adapter.equals(null)) {
                    recListVenueView.setAdapter(adapter);
                    onView();
                }


                List<VenueItemToDataBase> venueList = new ArrayList<>();
                int size = response.body().getResponse().getGroups().get(0).getItems().size();
                if (size != 0) {
                    for (int i = 0; i < size; i++) {
                        venueList.add(VenueItemToDataBase.from(response.body().getResponse().getGroups().get(0).getItems().get(i)));
                    }
                } else onBadView();
                progressBar.setVisibility(GONE);

//                if(response.body().getResponse().equals(null)){
//                    onBadView();
//                    progressBar.setVisibility(GONE);
//
//                }
//                venueDAO.clearVenues();
//                venueDAO.insert(venueList);
            }

            @Override
            public void onFailure(Call<ResponseFromListVenue> call, Throwable t) {
                Log.e(TAG, "onFailure");

                // InternetConnection.checkConnection(MainActivity.this);
                if (InternetConnection.isConnected(MainActivity.this)) {
                    onBadView();
                }
                onNoConnectionView();

                progressBar.setVisibility(GONE);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void search(final SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = searchView.getQuery().toString();
                startApp(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void startDetail(VenueItemToDataBase venueItemToDataBase) {
        ArrayList<String> list = new ArrayList<>();
        list.add(venueItemToDataBase.getCode());
        list.add(venueItemToDataBase.getName());
        list.add(venueItemToDataBase.getShortName());
        list.add(Helper.getAdressWithDistance(venueItemToDataBase));
        list.add(venueItemToDataBase.getPrefix());
        list.add(venueItemToDataBase.getSyfix());
        list.add(Double.toString(venueItemToDataBase.getLatitude()));
        list.add(Double.toString(venueItemToDataBase.getLongtitude()));

        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        i.putStringArrayListExtra("list", list);
        startActivity(i);
    }

    public void onNoConnectionView() {
        emptyView.setVisibility(GONE);
        noConnectionView.setVisibility(VISIBLE);
        badQueryView.setVisibility(GONE);
        recListVenueView.setVisibility(GONE)
        ;
    }


    public void onEmptyView() {
        emptyView.setVisibility(VISIBLE);
        recListVenueView.setVisibility(GONE);
        noConnectionView.setVisibility(GONE);
        badQueryView.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    public void onView() {
        emptyView.setVisibility(GONE);
        recListVenueView.setVisibility(VISIBLE);
        noConnectionView.setVisibility(GONE);
        badQueryView.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    public void onBadView() {
        emptyView.setVisibility(GONE);
        recListVenueView.setVisibility(GONE);
        noConnectionView.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        badQueryView.setVisibility(VISIBLE);
    }

    public void onLoading() {
        emptyView.setVisibility(GONE);
        recListVenueView.setVisibility(GONE);
        noConnectionView.setVisibility(GONE);
        badQueryView.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

}

