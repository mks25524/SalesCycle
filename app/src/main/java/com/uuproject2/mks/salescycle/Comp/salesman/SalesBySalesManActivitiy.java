package com.uuproject2.mks.salescycle.Comp.salesman;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uuproject2.mks.salescycle.Comp.salesmanager.ShowProductActivity;
import com.uuproject2.mks.salescycle.R;

public class SalesBySalesManActivitiy extends AppCompatActivity {
 Button setDate;
    TextView tvDateView;
    DatePicker picker;
    ImageView imProduct;
    EditText etCustomerId;
    DatabaseReference databaseSales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_by_sales_man_activitiy);
   tvDateView= (TextView) findViewById(R.id.tvDateView);
        setDate= (Button) findViewById(R.id.btSetDate);
        picker= (DatePicker) findViewById(R.id.datePicker1);
        imProduct= (ImageView) findViewById(R.id.imProductSale);
        etCustomerId= (EditText) findViewById(R.id.etSalesCustomerId);
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
       String id=etCustomerId.getText().toString().trim();
       Toast.makeText(getApplicationContext(),""+date,Toast.LENGTH_LONG).show();
       databaseSales= FirebaseDatabase.getInstance().getReference("sales").child(date);
       if(!TextUtils.isEmpty(id)){
           InitialSalesCreate salesCreate=new InitialSalesCreate(date,id);
           databaseSales.child(id).setValue(salesCreate);

           //blank again
           etCustomerId.setText("");
           Intent intent=new Intent(this,ShowSalesManProductForSale.class);
           intent.putExtra("date",date);
           intent.putExtra("id",id);
           startActivity(intent);
           //startActivity(new Intent(this,ShowSalesManProductForSale.class));
       }else{
           Toast.makeText(getApplicationContext(),"Please fill up Customer id",Toast.LENGTH_LONG).show();
       }
   }




}
