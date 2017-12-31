package com.uuproject2.mks.salescycle.Comp.company;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.uuproject2.mks.salescycle.Comp.salesmanager.ShowProductActivity;
import com.uuproject2.mks.salescycle.Map.MapsActivity;
import com.uuproject2.mks.salescycle.R;

public class ProfileActivityCompany extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_company);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //for actionbar title

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);


        //actionBar.setIcon(R.mipmap.customer);

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.show_actionbar_title, null);
        tv=(TextView)v.findViewById(R.id.titleAction);
        tv.setText("Sales Cycle");
        tv.setTextColor(Color.WHITE);


        actionBar.setCustomView(v);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_activity_company, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.addAuthority) {

            Intent intent=new Intent(getApplicationContext(),AddAuthorityActivity.class);
            startActivity(intent);
        } else if (id == R.id.Products) {
            Intent intent=new Intent(getApplicationContext(),ShowProductActivity.class);
            startActivity(intent);

        } else if (id == R.id.logout) {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.payments) {
            Intent intent=new Intent(getApplicationContext(),ShowTransactionStatus.class);
            startActivity(intent);


        } else if (id == R.id.location) {
            GPSTracker gps=new GPSTracker(this);
          String location=gps.getLocation().toString();
           String city=gps.getCityName();
            String longlat=gps.getLatitude()+""+gps.getLongitude();
            Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"longLat:"+city,Toast.LENGTH_LONG).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
