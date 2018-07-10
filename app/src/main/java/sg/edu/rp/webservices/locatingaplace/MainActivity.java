package sg.edu.rp.webservices.locatingaplace;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hqpos, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    if (map != null) {
                        LatLng centralPos = new LatLng(1.3301464, 103.8294176);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(centralPos,
                                17));
                        Toast.makeText(MainActivity.this, "HQ-Central", Toast.LENGTH_SHORT).show();

                    }
                } else if (position == 2) {
                    if (map != null) {
                        LatLng northPos = new LatLng(1.4433597, 103.7815925);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(northPos,
                                17));
                        Toast.makeText(MainActivity.this, "HQ-North", Toast.LENGTH_SHORT).show();
                    }

                } else if (position == 3) {
                    if (map != null) {
                        LatLng eastPos = new LatLng(1.3478878, 103.886232);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(eastPos,
                                17));
                        Toast.makeText(MainActivity.this, "HQ-East", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        btn1 = (Button) findViewById(R.id.btn1);
//        btn2 = (Button) findViewById(R.id.btn2);
//        btn3 = (Button) findViewById(R.id.btn3);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    LatLng northPos = new LatLng(1.4433597, 103.7815925);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(northPos,
//                            15));
//                    Toast.makeText(MainActivity.this, "HQ-North", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    LatLng centralPos = new LatLng(1.3301464, 103.8294176);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(centralPos,
//                            15));
//                    Toast.makeText(MainActivity.this, "HQ-Central", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null) {
//                    LatLng eastPos = new LatLng(1.3478878, 103.886232);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(eastPos,
//                            15));
//                    Toast.makeText(MainActivity.this, "HQ-East", Toast.LENGTH_SHORT).show();
//
//
//                }
//            }
//        });
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                LatLng currentPos = new LatLng(1.352321, 103.8193128);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPos,
                        12));


                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }


                LatLng northHQPos = new LatLng(1.4433597, 103.7815925);
                LatLng eastHQPos = new LatLng(1.3478878, 103.886232);
                LatLng centralHQPos = new LatLng(1.3301464, 103.8294176);

                Marker northHQ = map.addMarker(new
                        MarkerOptions()
                        .position(northHQPos)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 764654")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on)));
                Marker eastHQ = map.addMarker(new
                        MarkerOptions()
                        .position(eastHQPos)
                        .title("HQ - East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                Marker centralHQ = map.addMarker(new
                        MarkerOptions()
                        .position(centralHQPos)
                        .title("HQ - Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


            }
        });

    }
}
