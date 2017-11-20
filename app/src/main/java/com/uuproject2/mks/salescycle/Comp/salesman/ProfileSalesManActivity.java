package com.uuproject2.mks.salescycle.Comp.salesman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uuproject2.mks.salescycle.Comp.company.MainActivity;
import com.uuproject2.mks.salescycle.R;

public class ProfileSalesManActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imSales,logout;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sales_man);
        imSales= (ImageView) findViewById(R.id.imbtSalesManSales);
        logout= (ImageView) findViewById(R.id.logout);
        imSales.setOnClickListener(this);
        logout.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        final String name=bundle.getString("name");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);


        actionBar.setIcon(R.mipmap.customer);

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.show_actionbar_title, null);
        tv=(TextView)v.findViewById(R.id.titleAction);
        tv.setText(name);
        tv.setTextColor(Color.WHITE);


        actionBar.setCustomView(v);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imbtSalesManSales:
                startActivity(new Intent(getApplicationContext(),SalesBySalesManActivitiy.class));
                break;
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }
    }
}
