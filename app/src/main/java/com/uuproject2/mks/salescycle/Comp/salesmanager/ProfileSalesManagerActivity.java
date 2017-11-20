package com.uuproject2.mks.salescycle.Comp.salesmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uuproject2.mks.salescycle.Comp.company.MainActivity;
import com.uuproject2.mks.salescycle.Comp.salesman.SalesBySalesManActivitiy;
import com.uuproject2.mks.salescycle.R;

import java.net.URL;

public class ProfileSalesManagerActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imgbtProducts,customer,logout;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sales_manager);
     imgbtProducts= (ImageView) findViewById(R.id.imbtProduct);
        customer= (ImageView) findViewById(R.id.imCustomer);
        logout= (ImageView) findViewById(R.id.logout);

        imgbtProducts.setOnClickListener(this);
        logout.setOnClickListener(this);
        customer.setOnClickListener(this);
//        imgbtProducts.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//              startActivity(new Intent(getApplication(),AddProductActivity.class));
//          }
//      });
//       // findViewById(R.id.imbtProduct).setOnClickListener(this);
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
            case R.id.imbtProduct:
                startActivity(new Intent(getApplicationContext(),AddProductActivity.class));
                break;
            case R.id.imCustomer:
                startActivity(new Intent(getApplicationContext(),AddCustomerActivity.class));
                break;
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }
    }
}
