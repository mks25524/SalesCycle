package com.uuproject2.mks.salescycle.Comp.customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uuproject2.mks.salescycle.Comp.company.ProfileActivityCompany;
import com.uuproject2.mks.salescycle.Comp.salesman.InitialSalesCreate;
import com.uuproject2.mks.salescycle.Comp.salesman.ShowSalesManProductForSale;
import com.uuproject2.mks.salescycle.R;

public class CustomerPurchaseActivity extends AppCompatActivity {
    Button setDate;
    TextView tvDateView;
    DatePicker picker;
    ImageView imProduct;
    //EditText etCustomerId;
    DatabaseReference databaseSales;

    public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_purchase);
        tvDateView= (TextView) findViewById(R.id.tvDateView);
        setDate= (Button) findViewById(R.id.btSetDate);
        picker= (DatePicker) findViewById(R.id.datePicker1);
        imProduct= (ImageView) findViewById(R.id.imProductSale);

       // etCustomerId= (EditText) findViewById(R.id.etSalesCustomerId);
        imProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSaleIdViewProductList();
            }
        });

        tvDateView.setText(getCurrentDate());
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDateView.setText(getCurrentDate());
            }
        });
    }

    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        builder.append((picker.getDayOfMonth()+"-"));
        builder.append((picker.getMonth()+1)+"-");
        builder.append(picker.getYear());
        return builder.toString();
    }
    public void createSaleIdViewProductList(){
        String date=tvDateView.getText().toString();
       // String id=etCustomerId.getText().toString().trim();
       // SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
       // String salesManId=prefs.getString("id",null);
        String salesManId="customer";
        String nameMonth=MONTHS[picker.getMonth()];
        String nameYear=String.valueOf(picker.getYear());
        Bundle bundle=getIntent().getExtras();
        String id=bundle.getString("id");

        Toast.makeText(getApplicationContext(),""+nameMonth,Toast.LENGTH_LONG).show();
        databaseSales= FirebaseDatabase.getInstance().getReference("sales").child(nameYear).child(nameMonth).child(date);
        if(!TextUtils.isEmpty(id)){
            InitialSalesCreate salesCreate=new InitialSalesCreate(date,id,salesManId);
            databaseSales.child(id).setValue(salesCreate);

            //blank again
//            etCustomerId.setText("");
            Intent intent=new Intent(this,ShowProductForCustomerPurchase.class);
            intent.putExtra("date",date);
            intent.putExtra("id",id);
            intent.putExtra("year",nameYear);
            intent.putExtra("month",nameMonth);
            startActivity(intent);
            //startActivity(new Intent(this,ShowSalesManProductForSale.class));
        }else{
            Toast.makeText(getApplicationContext(),"Please fill up Customer id",Toast.LENGTH_LONG).show();
        }
}}
