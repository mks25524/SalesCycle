package com.uuproject2.mks.salescycle.Comp.deleverman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uuproject2.mks.salescycle.Comp.LocationSharing.RealLocationActivity;
import com.uuproject2.mks.salescycle.Comp.company.MainActivity;
import com.uuproject2.mks.salescycle.Comp.model.CoordinatesModel;
import com.uuproject2.mks.salescycle.Comp.model.NewSalesModel;
import com.uuproject2.mks.salescycle.Comp.model.SalesHistoryAdapterForManager;
import com.uuproject2.mks.salescycle.Comp.salesman.SalesBySalesManActivitiy;
import com.uuproject2.mks.salescycle.Comp.salesman.ShowSalesManProductForSale;
import com.uuproject2.mks.salescycle.Comp.salesmanager.ShowSalesHistoryBySalesmanager;
import com.uuproject2.mks.salescycle.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileDelevermanActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imLogout,imDelever,location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_deleverman);
        imDelever= (ImageView) findViewById(R.id.imDelever);
        imLogout= (ImageView) findViewById(R.id.logout);
        location= (ImageView) findViewById(R.id.imDeleverLocation);
        imDelever.setOnClickListener(this);
        imLogout.setOnClickListener(this);
        location.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.imDelever:
                startActivity(new Intent(getApplicationContext(),ShowSalesHistoryBySalesmanager.class));
                break;
            case R.id.logout:
               startActivity(new Intent(getApplicationContext(),MainActivity.class));

                break;
            case R.id.imDeleverLocation:
                Bundle bundle=getIntent().getExtras();
                final String id=bundle.getString("id");
                Intent intent=new Intent(this,RealLocationActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
        }
    }
}
