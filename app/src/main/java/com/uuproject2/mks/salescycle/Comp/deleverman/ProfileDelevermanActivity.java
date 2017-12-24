package com.uuproject2.mks.salescycle.Comp.deleverman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import com.uuproject2.mks.salescycle.Comp.company.MainActivity;
import com.uuproject2.mks.salescycle.Comp.salesman.SalesBySalesManActivitiy;
import com.uuproject2.mks.salescycle.Comp.salesmanager.ShowSalesHistoryBySalesmanager;
import com.uuproject2.mks.salescycle.R;

public class ProfileDelevermanActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imLogout,imDelever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_deleverman);
        imDelever= (ImageView) findViewById(R.id.imDelever);
        imLogout= (ImageView) findViewById(R.id.logout);
        imDelever.setOnClickListener(this);
        imLogout.setOnClickListener(this);

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
        }
    }
}
