package com.example.eventmanagment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mapactivity extends AppCompatActivity implements OnMapReadyCallback , GoogleApiClient.OnConnectionFailedListener{


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    Button btn ;
    private static final int ERROR_DIALOGUE_REQUEST = 9001;

    public static final String Fine_Location = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String Coarse_Location = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final int Location_permission_request_code = 1234;
    Boolean mlocationpermissiongranteed = false;

    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));



    private AutoCompleteTextView mSearchText;


    private ImageView mGps;
private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;

    private GoogleApiClient mGoogleApiClient;



    private GoogleMap mMap;

    private static final String TAG = "MapActivity";


    private FusedLocationProviderClient mFusedLocationProviderClient;


    private static final float DEFAULT_ZOOM = 15f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapactivity);
     btn = findViewById(R.id.confirm);
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Intent myintent = new Intent(getApplicationContext(),baseactivity.class);
             startActivity(myintent);

         }
     });


        mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search);

        mGps = (ImageView) findViewById(R.id.ic_gps);


        if(isServiceOk())
        {
            init();
            getlocationpermission();

            init();


        }


    }

    private void init(){
        Log.d(TAG, "init: initializing");

        if(mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
            try {
                mGoogleApiClient = new GoogleApiClient
                        .Builder(this)
                        .addApi(Places.GEO_DATA_API)
                        .addApi(Places.PLACE_DETECTION_API)
                        .enableAutoManage(this, this)
                        .build();
            } catch (Exception e) {
                Log.i(TAG,e.getMessage());
                e.printStackTrace();
            }
        }

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
                LAT_LNG_BOUNDS, null);


        mSearchText.setAdapter(mPlaceAutocompleteAdapter);


        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {

            if (event != null && event.getAction() != KeyEvent.ACTION_DOWN) {
                return false;

            }
            else if (actionId == EditorInfo.IME_ACTION_DONE
                    || event == null
                    || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
               mMap.clear();
                geoLocate();

                return true;
            }


                return false;
            }
        });

        mGps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked gps icon");
                getDeviceLocation();
            }
        });


        hideSoftKeyboard();
    }

    private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(mapactivity.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }

        if(list.size() > 0){
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location: " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();\\\


            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));
            String lat = Double.toString(address.getLatitude());
            String lon = Double.toString(address.getLongitude());
            app.lat = lat;
            app.lon = lon;




        }
    }












    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
try {
    if (mlocationpermissiongranteed) {
        final Task<Location> location = mFusedLocationProviderClient.getLastLocation();

        location.addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                if(task.isSuccessful()){
                    Log.d(TAG, "onComplete: found location!");
                    Location currentLocation = (Location) task.getResult();

                    moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                            DEFAULT_ZOOM,"My Location");

                }else{
                    Log.d(TAG, "onComplete: current location is null");
                    Toast.makeText(mapactivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                }




            }
        });





    }
}catch (SecurityException e){
    Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
}
    }





    private void moveCamera(LatLng latLng, float zoom,String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));


        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();



    }






    @Override
     public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        if (mlocationpermissiongranteed) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);


            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//If you want botton to get back to your location then make it true above statment


        }
        init();

        }


    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(mapactivity.this);




    }


    public void getlocationpermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Fine_Location) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Coarse_Location) == PackageManager.PERMISSION_GRANTED) {

                mlocationpermissiongranteed = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, Location_permission_request_code);


            }

        }

        else {
            ActivityCompat.requestPermissions(this, permissions, Location_permission_request_code);


        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mlocationpermissiongranteed = false;

        switch (requestCode) {
            case Location_permission_request_code: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mlocationpermissiongranteed = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mlocationpermissiongranteed = true;
                    //initialize our map
                    initMap();
                }
            }
        }


    }


    private void hideSoftKeyboard(){

        InputMethodManager inputMethodManager = (InputMethodManager)  this.getSystemService(Activity.INPUT_METHOD_SERVICE);

    }
    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(this);
        mGoogleApiClient.disconnect();
    }

    private boolean isServiceOk()
    {
        Log.i(TAG,"Is service Ok");
        int availaible = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(availaible == ConnectionResult.SUCCESS)
        {
            Log.i(TAG,"Services is OK");
            return true;



        }

        else if(GoogleApiAvailability.getInstance().isUserResolvableError(availaible))
        {

            Log.i(TAG,"Error occurred but we can resolve it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this,availaible,ERROR_DIALOGUE_REQUEST);
            dialog.show();



        }
        else
        {
            Toast.makeText(this,"You Cant make a request ", Toast.LENGTH_SHORT).show();


        }
        return false;
    }





}