package com.uuproject2.mks.salescycle.Comp.customer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uuproject2.mks.salescycle.Comp.salesman.ShowSalesManProductForSale;
import com.uuproject2.mks.salescycle.Comp.salesmanager.Product;
import com.uuproject2.mks.salescycle.R;

import java.util.ArrayList;

public class CustomerProfileActivity extends AppCompatActivity {

    ImageView imPurchaseHistory,purchaseCustomer,imPaymentHistory;


    public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        purchaseCustomer= (ImageView) findViewById(R.id.purchaseCustomer);
        imPurchaseHistory= (ImageView) findViewById(R.id.imPurchaseHistory);
        imPaymentHistory= (ImageView) findViewById(R.id.imPaymentHistory);
        //get data from customer login

purchaseCustomer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Bundle bundle=getIntent().getExtras();
        String id=bundle.getString("id");
        Intent intent=new Intent(getApplicationContext(),CustomerPurchaseActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
});
        imPurchaseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=getIntent().getExtras();
                String id=bundle.getString("id");
                Intent intent=new Intent(getApplicationContext(),BuyingHistoryCustomer.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        imPaymentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=getIntent().getExtras();
                String id=bundle.getString("id");
                Intent intent=new Intent(getApplicationContext(),PyamentHistoryCustomer.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }

//


}
