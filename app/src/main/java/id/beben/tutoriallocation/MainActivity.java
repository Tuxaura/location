package id.beben.tutoriallocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();
    }

    void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        }else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                double lati = location.getLatitude();
                double longi = location.getLongitude();
                ((EditText) findViewById(R.id.etLocLat)).setText("Latitude :" + lati);
                ((EditText) findViewById(R.id.etLocLong)).setText("Longitude :" + longi);
            } else {
                ((EditText) findViewById(R.id.etLocLat)).setText("Unable to find correct location");
                ((EditText) findViewById(R.id.etLocLong)).setText("Unable to find correct location");
            }
        }
    }


    //@Override
//    void onRequestPermissionResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
//        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
//
//        switch(requestCode){
//            case REQUEST_LOCATION:
//                getLocation();
//                break;
//        }
//    }
}
