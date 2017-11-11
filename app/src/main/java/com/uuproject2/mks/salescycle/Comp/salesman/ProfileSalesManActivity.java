package com.uuproject2.mks.salescycle.Comp.salesman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.uuproject2.mks.salescycle.R;

public class ProfileSalesManActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imSales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sales_man);
        imSales= (ImageView) findViewById(R.id.imbtSalesManSales);
        imSales.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imbtSalesManSales:
                startActivity(new Intent(getApplicationContext(),SalesBySalesManActivitiy.class));
                break;
        }
    }
}
