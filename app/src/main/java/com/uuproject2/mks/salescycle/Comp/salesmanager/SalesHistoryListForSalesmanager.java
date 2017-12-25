package com.uuproject2.mks.salescycle.Comp.salesmanager;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.uuproject2.mks.salescycle.Comp.model.DeleverStatusModel;
import com.uuproject2.mks.salescycle.Comp.model.DeleverSuccessStatusModel;
import com.uuproject2.mks.salescycle.Comp.model.NewSalesModel;
import com.uuproject2.mks.salescycle.Comp.model.SalesHistoryAdapterForManager;
import com.uuproject2.mks.salescycle.Comp.model.SalesHistoryModel;
import com.uuproject2.mks.salescycle.Comp.salesman.ShowSalesManProductForSale;
import com.uuproject2.mks.salescycle.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SalesHistoryListForSalesmanager extends AppCompatActivity {
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase,mDatabaseTwo,mDatabseThree,mDatabaseFour;

    //progress dialog
    private ProgressDialog progressDialog;
    String s="";

    //list to hold all the uploaded images
    private List<NewSalesModel> uploads;
    private List<Customer>pick;

    private SalesHistoryAdapterForManager myAdapter;
    String address,mobile,name,shop;
    String deleverStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_history_list_for_salesmanager);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSalesHistoryManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view,final int position) {
                Toast.makeText(getApplicationContext(),"hi good"+uploads.get(position).getRed_Apple(),Toast.LENGTH_LONG).show();
               final AlertDialog.Builder builder=new AlertDialog.Builder(SalesHistoryListForSalesmanager.this);
                LayoutInflater layoutInflater=getLayoutInflater();
                final View dialogView=layoutInflater.inflate(R.layout.dialog_saleshistory_details,null);
                builder.setView(dialogView);
                final TextView tv1=(TextView)dialogView.findViewById(R.id.tv1);
                final TextView tv2=(TextView)dialogView.findViewById(R.id.tv2);
                final TextView tv3=(TextView)dialogView.findViewById(R.id.tv3);
                final TextView tv4=(TextView)dialogView.findViewById(R.id.tv4);
                final TextView tv5=(TextView)dialogView.findViewById(R.id.tv5);
                final TextView tv6=(TextView)dialogView.findViewById(R.id.tv6);
                final TextView tv7=(TextView)dialogView.findViewById(R.id.tv7);
                final TextView tv8=(TextView)dialogView.findViewById(R.id.tv8);
                final TextView tv9=(TextView)dialogView.findViewById(R.id.tv9);
                final TextView tv10=(TextView)dialogView.findViewById(R.id.tv10);
              final TextView tvTotalbill=(TextView)dialogView.findViewById(R.id.tvtotalBil);
                final TextView tvTotalbills=(TextView)dialogView.findViewById(R.id.textView5);

                Button btOk=(Button)dialogView.findViewById(R.id.btOk);
                builder.setTitle("sales history");
                final AlertDialog alertDialog=builder.create();
//                if(!Objects.equals(uploads.get(position).getOrange(), "0")){
//                    tv1.setText(uploads.get(position).getOrange());
//                }if(!Objects.equals(uploads.get(position).getChina_Orange(), "0")){
//                    tv2.setText(uploads.get(position).getChina_Orange());
//                }if(!Objects.equals(uploads.get(position).getDragon_Fruits(), "0")){
//                    tv3.setText(uploads.get(position).getDragon_Fruits());
//                }if(!Objects.equals(uploads.get(position).getGreen_Apple(), "0")){
//                    tv4.setText(uploads.get(position).getGreen_Apple());
//                }if(!Objects.equals(uploads.get(position).getRed_Apple(), "0")){
//                    tv5.setText(uploads.get(position).getRed_Apple());
//                }if(!Objects.equals(uploads.get(position).getRed_Grapes(), "0")){
//                    tv6.setText(uploads.get(position).getRed_Grapes());
//                }if(!Objects.equals(uploads.get(position).getGuava(), "0")){
//                    tv7.setText(uploads.get(position).getGuava());
//                }if(!Objects.equals(uploads.get(position).getNashpati(), "0")){
//                    tv8.setText(uploads.get(position).getNashpati());
//                }if(!Objects.equals(uploads.get(position).getGreen_Grapes(), "0")){
//                    tv9.setText(uploads.get(position).getGreen_Grapes());
//                }if(!Objects.equals(uploads.get(position).getGreen_Apple(), "0")){
//                    tv10.setText(uploads.get(position).getGreen_Apple());
//                }

                tv1.setText(uploads.get(position).getOrange());
                tv2.setText(uploads.get(position).getChina_Orange());
                tv3.setText(uploads.get(position).getDragon_Fruits());
                tv4.setText(uploads.get(position).getGreen_Apple());
                tv5.setText(uploads.get(position).getRed_Apple());
                tv6.setText(uploads.get(position).getRed_Grapes());
                tv7.setText(uploads.get(position).getGuava());
                tv8.setText(uploads.get(position).getNashpati());
                tv9.setText(uploads.get(position).getGreen_Grapes());
                tv10.setText(uploads.get(position).getGreen_Apple());

                int s=uploads.get(position).getTotalBill();
                String ss= String.valueOf(s);
                tvTotalbill.setText(ss);
                Toast.makeText(getApplicationContext(),"bil:"+uploads.get(position).getTotalBill(),Toast.LENGTH_LONG).show();
                alertDialog.show();
                btOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onLongClick(View view, final int position) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SalesHistoryListForSalesmanager.this);
                LayoutInflater layoutInflater=getLayoutInflater();
                final View dialogView=layoutInflater.inflate(R.layout.dialog_delever_status,null);
                builder.setView(dialogView);
                final TextView showPaymentStatus=(TextView)dialogView.findViewById(R.id.showPaymentStatus);
                final TextView showDeleverStatus=(TextView)dialogView.findViewById(R.id.showDeleverStatus);
                final TextView showAddress=(TextView)dialogView.findViewById(R.id.showAddress);
                final TextView showName=(TextView)dialogView.findViewById(R.id.showName);
                final TextView showShopName=(TextView)dialogView.findViewById(R.id.showShopName);
                final TextView showMobileNum=(TextView)dialogView.findViewById(R.id.showMobile);
                Button btClose=(Button)dialogView.findViewById(R.id.btClose);
                Button btDone=(Button)dialogView.findViewById(R.id.btDone);
                builder.setTitle("Delever Report");
                final AlertDialog alertDialog=builder.create();
                mDatabaseTwo = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mDatabasex=mDatabaseTwo.child("customer");
                ValueEventListener valueEventListenerx=new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                          // address=snapshot.child("01731623524").getValue(String.class);
                           // address=upload.getChina_Orange();
//                        address=snapshot.child("customerAddress").getValue(String.class);
//                        name=snapshot.child("customerName").getValue(String.class);
//                        mobile=snapshot.child("customerMobile").getValue(String.class);
//                        shop=snapshot.child("customerShopName").getValue(String.class);
                            //showAddress.setText(address);
                            Customer customer=snapshot.getValue(Customer.class);
                            if(uploads.get(position).getId().equals(customer.getCustomerMobile())){
                                address=customer.getCustomerAddress();
                                mobile=customer.getCustomerMobile();
                                name=customer.getCustomerName();
                                shop=customer.getCustomerShopName();
                            }
                            showAddress.setText(address);
                            showName.setText(name);
                            showShopName.setText(shop);
                            showMobileNum.setText(mobile);


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                mDatabasex.addListenerForSingleValueEvent(valueEventListenerx);
//                mDatabasex.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                         Customer upload=snapshot.getValue(Customer.class);
////
////
////
////
//                           address=upload.getCustomerAddress();
//                            name=upload.getCustomerName();
//                            mobile=upload.getCustomerMobile();
//                            shop=upload.getCustomerShopName();
//
//
//                    }}
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
                showPaymentStatus.setText(uploads.get(position).getPaymentStatus());
                if(deleverStatus=="Done"){
                showDeleverStatus.setText("Delevered");}else {
                    showDeleverStatus.setText(uploads.get(position).getDeleverStatus());
                }



                btClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                btDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleverStatus="Delevered";
                        String id=uploads.get(position).getId();
                        String date=uploads.get(position).getDate();
                        String orange=uploads.get(position).getOrange();
                        String redApple=uploads.get(position).getRed_Apple();
                        String chinaOrange=uploads.get(position).getChina_Orange();
                        String dragonFruits=uploads.get(position).getDragon_Fruits();
                        String greenApple=uploads.get(position).getGreen_Apple();
                        String guava=uploads.get(position).getGuava();
                        String nashpati=uploads.get(position).getNashpati();
                        String redGrapes=uploads.get(position).getRed_Grapes();
                        String greenGrapes=uploads.get(position).getGreen_Grapes();
                        int finalPrice=uploads.get(position).getTotalBill();
                        String paymentStatus=uploads.get(position).getPaymentStatus();

//
                        mDatabseThree=FirebaseDatabase.getInstance().getReference("newSales").child(date);
                        NewSalesModel model=new NewSalesModel(id,date,orange,redApple,chinaOrange,dragonFruits,greenApple,guava,nashpati,redGrapes,greenGrapes,finalPrice,paymentStatus,deleverStatus);
                        mDatabseThree.child(id).setValue(model);
                        mDatabaseFour=FirebaseDatabase.getInstance().getReference("newSalesCustomer").child(id);
                        NewSalesModel modelx=new NewSalesModel(id,date,orange,redApple,chinaOrange,dragonFruits,greenApple,guava,nashpati,redGrapes,greenGrapes,finalPrice,paymentStatus,deleverStatus);
                        mDatabaseFour.child(date).setValue(modelx);

                    }
                });
                alertDialog.show();

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
        DatabaseReference mDatabase1=mDatabase.child("newSales").child(date);


        ValueEventListener valueEventListener=new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    NewSalesModel upload=snapshot.getValue(NewSalesModel.class);
                    uploads.add(upload);

s=upload.getRed_Apple();
                }
                adapter = new SalesHistoryAdapterForManager(getApplicationContext(), uploads);
//
//                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
                // String totalBil=dataSnapshot.child("totalBill").getValue(String.class);
               // Toast.makeText(getApplicationContext(),"bill: "+uploads.get(0).getName(),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"hi good"+uploads.get(0).getRed_Apple(),Toast.LENGTH_LONG).show();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase1.addListenerForSingleValueEvent(valueEventListener);

    }
}
