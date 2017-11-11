package com.uuproject2.mks.salescycle.Comp.salesmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uuproject2.mks.salescycle.R;

import java.net.URL;

public class ProfileSalesManagerActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imgbtProducts,customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sales_manager);
     imgbtProducts= (ImageView) findViewById(R.id.imbtProduct);
        customer= (ImageView) findViewById(R.id.imCustomer);

imgbtProducts.setOnClickListener(this);
        customer.setOnClickListener(this);
//        imgbtProducts.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//              startActivity(new Intent(getApplication(),AddProductActivity.class));
//          }
//      });
//       // findViewById(R.id.imbtProduct).setOnClickListener(this);
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
        }
    }
}
