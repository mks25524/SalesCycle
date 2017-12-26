package com.uuproject2.mks.salescycle.Comp.company;

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
import com.uuproject2.mks.salescycle.Comp.customer.BuyingHistoryCustomer;
import com.uuproject2.mks.salescycle.Comp.salesmanager.Constants;
import com.uuproject2.mks.salescycle.Comp.salesmanager.MyAdapter;
import com.uuproject2.mks.salescycle.Comp.salesmanager.MyAdapterTwo;
import com.uuproject2.mks.salescycle.Comp.salesmanager.Product;
import com.uuproject2.mks.salescycle.Comp.salesmanager.RecyclerViewClickListener;
import com.uuproject2.mks.salescycle.Comp.salesmanager.RecyclerViewTouchListener;
import com.uuproject2.mks.salescycle.Comp.salesmanager.TransactionGetingDataModel;
import com.uuproject2.mks.salescycle.R;

import java.util.ArrayList;
import java.util.List;

public class ShowTransactionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase;

    //progress dialog
    private ProgressDialog progressDialog;
    String s="";

    //list to hold all the uploaded images
   private List<TransactionGetingDataModel> uploads;

    private MyAdapterTwo myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_transaction);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewtransaction);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(ShowTransactionActivity.this);
                LayoutInflater layoutInflater=getLayoutInflater();
                final View dialogView=layoutInflater.inflate(R.layout.dialog_payment_history_matching,null);
                builder.setView(dialogView);
                final TextView paymentStatus=(TextView)dialogView.findViewById(R.id.showPaymentStatus);
                final TextView tranxId=(TextView)dialogView.findViewById(R.id.showTrnxId);
                final TextView totalBill=(TextView)dialogView.findViewById(R.id.showTotalBill);
                final TextView id=(TextView)dialogView.findViewById(R.id.showId);
                Button btClose=(Button)dialogView.findViewById(R.id.btClose);
                Button btMatch=(Button)dialogView.findViewById(R.id.btMatch);
                builder.setTitle("Transaction Id Checking");
                final AlertDialog alertDialog=builder.create();
                paymentStatus.setText(uploads.get(position).getPaymentStatus());
                tranxId.setText(uploads.get(position).getTransactionId());
                totalBill.setText(uploads.get(position).getTotalBil());
                id.setText(uploads.get(position).getId());
                alertDialog.show();
                btClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                btMatch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        progressDialog = new ProgressDialog(this);
        uploads = new ArrayList<>();
        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        Bundle bundle=getIntent().getExtras();
        final String date=bundle.getString("date");
        //mDatabase = FirebaseDatabase.getInstance().getReference("transactionForAuthority").child("2017").child("Dec").child("14-12-2017").child("id");
        mDatabase = FirebaseDatabase.getInstance().getReference();
     DatabaseReference mDatabase1=mDatabase.child("transactionForAuthority").child(date);

        //adding an event listener to fetch values
//               mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                //dismissing the progress dialog
//                progressDialog.dismiss();
//
//               // iterating through all the values in database
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    TransactionGetingDataModel upload = postSnapshot.getValue(TransactionGetingDataModel.class);
//
//                    uploads.add(upload);
//                }
//                //creating adapter
//                Toast.makeText(getApplicationContext(),"yes "+uploads.get(0).getId(),Toast.LENGTH_LONG).show();
//                adapter = new MyAdapterTwo(getApplicationContext(), uploads);
//
//                //adding adapter to recyclerview
//                recyclerView.setAdapter(adapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                progressDialog.dismiss();
//            }
//        });

       ValueEventListener valueEventListener=new ValueEventListener() {
           String totalBil;
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               progressDialog.dismiss();
               for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                   TransactionGetingDataModel upload=snapshot.getValue(TransactionGetingDataModel.class);
                   uploads.add(upload);
                   totalBil=upload.getTotalBil();
               }
               adapter = new MyAdapterTwo(getApplicationContext(), uploads);
//
//                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
              // String totalBil=dataSnapshot.child("totalBill").getValue(String.class);
               Toast.makeText(getApplicationContext(),"bill: "+uploads.get(0).getTotalBil(),Toast.LENGTH_LONG).show();
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       };
       mDatabase1.addListenerForSingleValueEvent(valueEventListener);



    }
}
