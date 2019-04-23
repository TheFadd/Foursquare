package com.example.foursquare.ui.list;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foursquare.R;
import com.example.foursquare.data.networking.ConnectivityHelper;
import com.example.foursquare.data.networking.VenueRepository;
import com.example.foursquare.data.networking.venuelist.VenueListResponse;
import com.example.foursquare.data.networking.venuelist.models.Item;
import com.example.foursquare.ui.detail.DetailActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS;
import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "VENUE";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private VenueRepository repository;
    private VenueAdapter adapter;
    private RecyclerView recyclerView;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean locationPermissionsGranted;

    private LinearLayout emptyView;
    private LinearLayout noConnectionView;
    private LinearLayout badQueryView;
    private ProgressBar progressBar;
    private LinearLayout noLocationView;

    private double latitude;
    private double longitude;
    private SearchView searchView;

    private final Subject<String> querySubject = PublishSubject.create();
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParams();
        getLocationPermission();
        checkLocation();
    }

    public void initParams() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        repository = new VenueRepository();
        emptyView = findViewById(R.id.empty_view);
        noConnectionView = findViewById(R.id.no_connection_view);
        badQueryView = findViewById(R.id.bad_query);
        progressBar = findViewById(R.id.load_indicator);
        noLocationView = findViewById(R.id.location_view);

        recyclerView = findViewById(R.id.recycler_view);
        onEmptyView();
        recyclerView.setHasFixedSize(true);
        LayoutManager ll = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(ll);

        adapter = new VenueAdapter(this, this::startDetail);
        recyclerView.setAdapter(adapter);

        Disposable disposable = querySubject.debounce(1000, TimeUnit.MILLISECONDS)
                .switchMap(this::performSearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processResponse, this::handleError);
        disposables.add(disposable);
    }

    private Observable<VenueListResponse> performSearch(String query) {
        return repository.getVenueList(latitude, longitude, query);
//                .doOnSubscribe(it -> onLoading());
    }

    private void showGpsDisabledDialog() {
        new AlertDialog.Builder(this).setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> startActivity(new Intent(ACTION_LOCATION_SOURCE_SETTINGS)))
                .create().show();
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        try {
            if (locationPermissionsGranted) {
                Task<Location> location = fusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Location currentLocation = task.getResult();
                        Log.d(TAG, "onComplete: found location! " + currentLocation);
                        if (currentLocation != null) {
                            latitude = currentLocation.getLatitude();
                            longitude = currentLocation.getLongitude();
//                            querySubject.onNext(searchView.getQuery().toString());
                        }
                    } else {
                        Toast.makeText(this, "unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void getLocationPermission() {
        String[] permissions = {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
        Log.d(TAG, "getLocationPermission: getting location permissions");
        boolean fine = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean course = ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (fine && course) {
            locationPermissionsGranted = true;
            getDeviceLocation();
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            boolean granted = true;
            for (int i : grantResults) {
                if (i == PERMISSION_DENIED) {
                    granted = false;
                    break;
                }
            }
            Log.d(TAG, "" + granted);
            if (granted) {
                locationPermissionsGranted = true;
                getDeviceLocation();
            }
        }
    }

    private void handleError(Throwable e) {
        Log.e(TAG, "onFailure ", e);
        if (ConnectivityHelper.isConnected(this)) {
            onBadView();
        } else {
            onNoConnectionView();
        }
    }

    private void processResponse(VenueListResponse r) {
        Log.e(TAG, "Response ok");
        Log.e(TAG, "Response body" + r);
        List<VenueModel> listOfVenues = new ArrayList<>();
        for (Item item : r.getResponse().getGroups().get(0).getItems()) {
            listOfVenues.add(VenueModel.from(item));
        }
        adapter.swapData(listOfVenues);
        if (listOfVenues.isEmpty()) {
            onBadView();
        } else {
            onView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.item_search);
        searchView = (SearchView) search.getActionView();
        initSearch();
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

    private void initSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hideKeyboard();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (latitude == 0 || longitude == 0) {
                    onNoLocationView();
                } else {
                    querySubject.onNext(searchView.getQuery().toString());
                }
                return true;
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void startDetail(VenueModel item) {
        DetailActivity.launch(this, item);
    }

    public void onNoConnectionView() {
        hideViews();
        noConnectionView.setVisibility(VISIBLE);
    }

    public void onEmptyView() {
        hideViews();
        emptyView.setVisibility(VISIBLE);
    }

    public void onView() {
        hideViews();
        recyclerView.setVisibility(VISIBLE);
    }

    public void onBadView() {
        hideViews();
        badQueryView.setVisibility(VISIBLE);
    }

    public void onLoading() {
        hideViews();
        progressBar.setVisibility(VISIBLE);
    }

    public void onNoLocationView() {
        hideViews();
        noLocationView.setVisibility(VISIBLE);
    }

    private void hideViews() {
        emptyView.setVisibility(GONE);
        noConnectionView.setVisibility(GONE);
        badQueryView.setVisibility(GONE);
        recyclerView.setVisibility(GONE);
        noLocationView.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }


    public void checkLocation() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showGpsDisabledDialog();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }
}

