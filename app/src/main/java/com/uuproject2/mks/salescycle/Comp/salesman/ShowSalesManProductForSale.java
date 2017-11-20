package com.uuproject2.mks.salescycle.Comp.salesman;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uuproject2.mks.salescycle.Comp.salesmanager.Constants;
import com.uuproject2.mks.salescycle.Comp.salesmanager.MyAdapter;
import com.uuproject2.mks.salescycle.Comp.salesmanager.Product;
import com.uuproject2.mks.salescycle.Comp.salesmanager.RecyclerViewClickListener;
import com.uuproject2.mks.salescycle.Comp.salesmanager.RecyclerViewTouchListener;
import com.uuproject2.mks.salescycle.Comp.salesmanager.ShowProductActivity;
import com.uuproject2.mks.salescycle.R;

import java.util.ArrayList;
import java.util.List;

public class ShowSalesManProductForSale extends AppCompatActivity {
    //recyclerview object
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase,customerDatabase;

    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private List<Product> uploads;
    private MyAdapter myAdapter;
    ArrayList<String>list=new ArrayList<>();
    int finalPrice=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sales_man_product_for_sale);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSalesman);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, final int position) {
                // showProductDetails();
                AlertDialog.Builder builder=new AlertDialog.Builder(ShowSalesManProductForSale.this);
                final int num=0;
                LayoutInflater layoutInflater=getLayoutInflater();
                final View dialogView=layoutInflater.inflate(R.layout.product_sales_dialog,null);
                builder.setView(dialogView);
                final TextView etName=(TextView)dialogView.findViewById(R.id.etProductName);
                final TextView etPrice=(TextView)dialogView.findViewById(R.id.etProductPrices);
                final TextView etQuantity=(TextView)dialogView.findViewById(R.id.etProductQuantitys);
                Button btClose=(Button)dialogView.findViewById(R.id.btClose);
                // TextView tv=(TextView)dialogView.findViewById(R.id.productDes);
             final TextView tvCalculatePrice=(TextView)dialogView.findViewById(R.id.tvShowCalculatedPrice);

                Button add=(Button)dialogView.findViewById(R.id.btAddToBucket);
                Button preview=(Button)dialogView.findViewById(R.id.btPreview);
                final ElegantNumberButton numberButton=(ElegantNumberButton)dialogView.findViewById(R.id.number_button);
                //get put extras values
                Bundle bundle=getIntent().getExtras();
                final String date=bundle.getString("date");
                final String id=bundle.getString("id");
                final String year=bundle.getString("year");
                final String month=bundle.getString("month");


                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

                builder.setTitle("Product Details & Add to Bucket");
                final AlertDialog alertDialog=builder.create();
                // tv.setText(uploads.get(position).getProductQuantity());
                etName.setText(uploads.get(position).getProductName());
                etPrice.setText(uploads.get(position).getProductPrice());
                etQuantity.setText(uploads.get(position).getProductQuantity());
                numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       String numbers=numberButton.getNumber();
                        etQuantity.setText(numbers);
                        int num=Integer.parseInt(numbers);
                       int num2=Integer.parseInt(uploads.get(position).getProductPrice());
                        int num3=num*num2;
                       tvCalculatePrice.setText(Integer.toString(num3));
                       // etName.setText(numberButton.getNumber());
                    }

                });
                alertDialog.show();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String productName=etName.getText().toString().trim();
                        String howMuch=etQuantity.getText().toString().trim();
                        String totalPrice=tvCalculatePrice.getText().toString().trim();
                        int pval=Integer.parseInt(totalPrice);
                        finalPrice=finalPrice+pval;
                        mDatabase=FirebaseDatabase.getInstance().getReference("sales").child(year).child(month).child(date).child(id).child("products").child(productName);
                        customerDatabase=FirebaseDatabase.getInstance().getReference("customerSalesHistory").child(id).child(year).child(month).child(date).child("products").child(productName);

                        FinalSalesCreate salesCreate=new FinalSalesCreate(howMuch,totalPrice);
                        mDatabase.setValue(salesCreate);
                        customerDatabase.setValue(salesCreate);
                        customerDatabase=FirebaseDatabase.getInstance().getReference("customerSalesHistory").child(id).child(year).child(month).child(date);

                        mDatabase=FirebaseDatabase.getInstance().getReference("sales").child(year).child(month).child(date).child(id);


                       // FinalPriceAddToDatabase finalPriceAddToDatabase=new FinalPriceAddToDatabase(showPrice);
                        final String showPrice=Integer.toString(finalPrice);
                        FinalPriceAddToDatabase finalPriceAddToDatabase=new FinalPriceAddToDatabase(showPrice);
                        mDatabase.child("total Price").setValue(finalPriceAddToDatabase);

                        customerDatabase.child("total price").setValue(finalPriceAddToDatabase);
                        String totalVal=productName+"   "+howMuch+"   "+"   "+totalPrice;
                        list.add(totalVal);
                        Toast.makeText(getApplicationContext(),productName+" Is added to your bucket",Toast.LENGTH_LONG).show();

                    }
                });
                preview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        for (String s:list){
//                            Toast.makeText(getApplicationContext()," "+s,Toast.LENGTH_LONG).show();
//                        }

                        AlertDialog.Builder builder=new AlertDialog.Builder(ShowSalesManProductForSale.this);
                        LayoutInflater layoutInflater=getLayoutInflater();
                        final View dialogView=layoutInflater.inflate(R.layout.preview_listview_dialog,null);

                        builder.setView(dialogView);
                        builder.setTitle("Sales List");
                        final AlertDialog alertDialog=builder.create();
                        ListView listView=(ListView)dialogView.findViewById(R.id.lvPreviewDialog);
                        TextView textView=(TextView)dialogView.findViewById(R.id.tvShowTotalprice);
                        Button btOk=(Button)dialogView.findViewById(R.id.btOk);
                        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(ShowSalesManProductForSale.this, android.R.layout.simple_list_item_1,list);
                        listView.setAdapter(arrayAdapter);
                        final String showPrice=Integer.toString(finalPrice);
                        textView.setText(showPrice);
                        alertDialog.show();
                        btOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //only add total price into database
                                mDatabase=FirebaseDatabase.getInstance().getReference("sales").child(year).child(month).child(date).child(id);


//                                FinalPriceAddToDatabase finalPriceAddToDatabase=new FinalPriceAddToDatabase(showPrice);
//                                mDatabase.child("total Price").setValue(finalPriceAddToDatabase);
                                alertDialog.dismiss();


                            }
                        });
                    }
                });

                btClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // Toast.makeText(getApplicationContext(),"h="+tvCalculatePrice.getText().toString().trim(),Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
                });

            }

            @Override
            public void onLongClick(View view, final int position) {
                //  Toast.makeText(getApplicationContext(),uploads.get(position).getProductName(),Toast.LENGTH_LONG).show();


            }
        }));

        progressDialog = new ProgressDialog(this);

        uploads = new ArrayList<>();
        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //adding an event listener to fetch values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog.dismiss();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Product upload = postSnapshot.getValue(Product.class);
                    uploads.add(upload);
                }
                //creating adapter
                adapter = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}
