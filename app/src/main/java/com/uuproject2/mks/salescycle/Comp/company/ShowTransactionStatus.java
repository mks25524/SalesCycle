package com.uuproject2.mks.salescycle.Comp.company;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.uuproject2.mks.salescycle.Comp.salesmanager.SalesHistoryListForSalesmanager;
import com.uuproject2.mks.salescycle.R;

public class ShowTransactionStatus extends AppCompatActivity {
    Button setDate;
    TextView tvDateView;
    DatePicker picker;
    Button showList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_transaction_status); tvDateView= (TextView) findViewById(R.id.tvDateView);
        setDate= (Button) findViewById(R.id.btSetDate);
        picker= (DatePicker) findViewById(R.id.datePicker1);
        showList= (Button) findViewById(R.id.imProductSale);


        showList.setOnClickListener(new View.OnClickListener() {
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



        String nameYear=String.valueOf(picker.getYear());


        Intent intent=new Intent(this,ShowTransactionActivity.class);
        intent.putExtra("date",date);



        startActivity(intent);
        //startActivity(new Intent(this,ShowSalesManProductForSale.class));
    }
}


