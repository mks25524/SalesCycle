package com.uuproject2.mks.salescycle.Comp.customer;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uuproject2.mks.salescycle.Comp.model.NewSalesModel;
import com.uuproject2.mks.salescycle.Comp.model.SalesHistoryAdapterForManager;
import com.uuproject2.mks.salescycle.Comp.salesmanager.Customer;
import com.uuproject2.mks.salescycle.Comp.salesmanager.RecyclerViewClickListener;
import com.uuproject2.mks.salescycle.Comp.salesmanager.RecyclerViewTouchListener;
import com.uuproject2.mks.salescycle.R;

import java.util.ArrayList;
import java.util.List;

public class PyamentHistoryCustomer extends AppCompatActivity {
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase,mDatabaseTwo,mDatabseThree,mDatabaseFour;

    //progress dialog
    private ProgressDialog progressDialog;
    String s="";

    //list to hold all the uploaded images
    private List<CustomerPurchaseModel> uploads;
    private List<Customer>pick;

    private SalesHistoryAdapterForManager myAdapter;
    String address,mobile,name,shop;
    String deleverStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyament_history_customer);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCustomerPaymentHistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); progressDialog = new ProgressDialog(this);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(PyamentHistoryCustomer.this);
                LayoutInflater layoutInflater=getLayoutInflater();
                final View dialogView=layoutInflater.inflate(R.layout.dialog_paymenthistory_customer,null);
                builder.setView(dialogView);
                final TextView paymentHistory=(TextView)dialogView.findViewById(R.id.showPaymentStatus);
                final TextView trnxId=(TextView)dialogView.findViewById(R.id.showTrnxId);
                final TextView totalBill=(TextView)dialogView.findViewById(R.id.showTotalBill);
                Button btClose=(Button)dialogView.findViewById(R.id.btClose);
                builder.setTitle("Payment Status");
                final AlertDialog alertDialog=builder.create();
                paymentHistory.setText(uploads.get(position).getPaymentStatus());
                trnxId.setText(uploads.get(position).getTransactionId());
                totalBill.setText(uploads.get(position).getTotalBil());
                alertDialog.show();
                btClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        uploads = new ArrayList<>();
        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        Bundle bundle=getIntent().getExtras();
        //final String date=bundle.getString("date");
        String id=bundle.getString("id");
        //mDatabase = FirebaseDatabase.getInstance().getReference("transactionForAuthority").child("2017").child("Dec").child("14-12-2017").child("id");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mDatabase1=mDatabase.child("transaction").child(id);


        ValueEventListener valueEventListener=new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    CustomerPurchaseModel upload=snapshot.getValue(CustomerPurchaseModel.class);
                    uploads.add(upload);


                }
                adapter = new PurchaseHistoryAdapterCustomer(getApplicationContext(), uploads);
//
//                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
                // String totalBil=dataSnapshot.child("totalBill").getValue(String.class);
                // Toast.makeText(getApplicationContext(),"bill: "+uploads.get(0).getName(),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"hi good"+uploads.get(0).getId(),Toast.LENGTH_LONG).show();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase1.addListenerForSingleValueEvent(valueEventListener);

    }
}
