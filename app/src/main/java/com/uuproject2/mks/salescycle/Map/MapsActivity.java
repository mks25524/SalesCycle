package com.uuproject2.mks.salescycle.Map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uuproject2.mks.salescycle.Comp.model.NewSalesModel;
import com.uuproject2.mks.salescycle.Comp.model.NewSalesModelLatLon;
import com.uuproject2.mks.salescycle.R;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<NewSalesModelLatLon> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        uploads=new ArrayList<>();
      DatabaseReference  mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mDatabase1=mDatabase.child("coordinates");
//        Toast.makeText(getApplicationContext(),"fast:"+uploads.get(0).getLat(),Toast.LENGTH_LONG).show();
        ValueEventListener valueEventListener=new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    NewSalesModelLatLon upload=snapshot.getValue(NewSalesModelLatLon.class);
                    uploads.add(upload);


                }
               // adapter = new SalesHistoryAdapterForManager(getApplicationContext(), uploads);
//
//                //adding adapter to recyclerview
              //  recyclerView.setAdapter(adapter);
                // String totalBil=dataSnapshot.child("totalBill").getValue(String.class);
                // Toast.makeText(getApplicationContext(),"bill: "+uploads.get(0).getName(),Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(),"hi good"+uploads.get(0).getRed_Apple(),Toast.LENGTH_LONG).show();
                for(int i=0;i<uploads.size();i++){
                    double lon,lat;
                    lat=uploads.get(i).getLat();
                    lon=uploads.get(i).getLon();
                    LatLng sydney = new LatLng(lat,lon);
                    mMap.addMarker(new MarkerOptions().position(sydney).title(uploads.get(i).getId()));
                    //  mMap.addMarker(new MarkerOptions().position(uploads.get(0).getLat(),uploads.get(0).getLon()).title("Marker in Sydney"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                }
//               Toast.makeText(getApplicationContext(),"testing done"+uploads.get(0).getLat(), Toast.LENGTH_LONG).show();
//                LatLng sydney = new LatLng(uploads.get(1).getLat(),uploads.get(1).getLon());
//                mMap.addMarker(new MarkerOptions().position(sydney).title(uploads.get(1).getId()));
//                //  mMap.addMarker(new MarkerOptions().position(uploads.get(0).getLat(),uploads.get(0).getLon()).title("Marker in Sydney"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase1.addListenerForSingleValueEvent(valueEventListener);
//        Toast.makeText(getApplicationContext(),"last:"+uploads.get(0).getLat(),Toast.LENGTH_LONG).show();
//         int s=uploads.size();
//        for(int i=0;i<s;i++){
//            String lat,lon;
//            lat=uploads.get(i).getLat();
//            lon=uploads.get(i).getLon();
//            // Add a marker in Sydney and move the camera
//            LatLng sydney = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
//            mMap.addMarker(new MarkerOptions().position(sydney).title(uploads.get(i).getId()));
//            //  mMap.addMarker(new MarkerOptions().position(uploads.get(0).getLat(),uploads.get(0).getLon()).title("Marker in Sydney"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        }
//String lat,lon;
//        lat=uploads.get(0).getLat();
//        lon=uploads.get(0).getLon();
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
//        Toast.makeText(getApplicationContext(),"lat:"+uploads.get(0).getLat(),Toast.LENGTH_LONG).show();
        //adding an event listener to fetch values
//        mDatabase1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                //dismissing the progress dialog
//
//
//                //iterating through all the values in database
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    CoordinatesModel upload = postSnapshot.getValue(CoordinatesModel.class);
//                    uploads.add(upload);
//                }
//                //creating adapter
//              // adapter = new MyAdapter(getApplicationContext(), uploads);
//
//                //adding adapter to recyclerview
//              //  recyclerView.setAdapter(adapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //progressDialog.dismiss();
//            }
//        });
////        String s=uploads.get(0).getLat();String st=uploads.get(0).getLon();
////        LatLng sydney = new LatLng(Double.parseDouble(s),Double.parseDouble(st));
//        LatLng sydney=new LatLng(-30,90);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//       // mMap.addMarker(new MarkerOptions().position(uploads.get(0).getLat(),uploads.get(0).getLon()).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
