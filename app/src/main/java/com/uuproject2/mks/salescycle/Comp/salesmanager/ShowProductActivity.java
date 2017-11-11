package com.uuproject2.mks.salescycle.Comp.salesmanager;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uuproject2.mks.salescycle.R;

import java.util.ArrayList;
import java.util.List;

public class ShowProductActivity extends AppCompatActivity {
    //recyclerview object
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase;

    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private List<Product> uploads;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressDialog = new ProgressDialog(this);

        uploads = new ArrayList<>();

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
              // showProductDetails();

                AlertDialog.Builder builder=new AlertDialog.Builder(ShowProductActivity.this);

                LayoutInflater layoutInflater=getLayoutInflater();
                View dialogView=layoutInflater.inflate(R.layout.product_description,null);
                builder.setView(dialogView);
                TextView etName=(TextView)dialogView.findViewById(R.id.etProductName);
                TextView etPrice=(TextView)dialogView.findViewById(R.id.etProductPrices);
                TextView etQuantity=(TextView)dialogView.findViewById(R.id.etProductQuantitys);



                 Button btClose=(Button)dialogView.findViewById(R.id.btClose);
               // TextView tv=(TextView)dialogView.findViewById(R.id.productDes);


                builder.setTitle("Product Details");
                final AlertDialog alertDialog=builder.create();
               // tv.setText(uploads.get(position).getProductQuantity());
                etName.setText(uploads.get(position).getProductName());
                etPrice.setText(uploads.get(position).getProductPrice());
                etQuantity.setText(uploads.get(position).getProductQuantity());
                alertDialog.show();

                btClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

            }

            @Override
            public void onLongClick(View view, final int position) {
              //  Toast.makeText(getApplicationContext(),uploads.get(position).getProductName(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(ShowProductActivity.this);
                LayoutInflater layoutInflater=getLayoutInflater();
                final View dialogView=layoutInflater.inflate(R.layout.product_description_update_dialog,null);
                builder.setView(dialogView);
               final EditText etName=(EditText)dialogView.findViewById(R.id.etUpdateProductName);
                final EditText etPrice=(EditText)dialogView.findViewById(R.id.etUpdateProductPrice);
                final EditText etQuantity=(EditText)dialogView.findViewById(R.id.etUpdateProductQuantity);
                final Button update=(Button)dialogView.findViewById(R.id.btUpdateProcuctDescription);
                final Button delete=(Button)dialogView.findViewById(R.id.btDeleteProductDescription);
                builder.setTitle("Update Product Description");
                final AlertDialog  alertDialog=builder.create();
                alertDialog.show();
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String productName=etName.getText().toString().trim();
                        String productPrice=etPrice.getText().toString().trim();
                        String productQuantity=etQuantity.getText().toString().trim();
                        if(TextUtils.isEmpty(productPrice) && TextUtils.isEmpty(productQuantity)){

                            etPrice.setError("Price Required");
                            etQuantity.setError("Quantity Required");
                            return;
                        }
                        Product product=uploads.get(position);
                        final String id=product.getId();
                        updateProductDescription(id,product.getUrl(),productName,productPrice,productQuantity);
                        alertDialog.dismiss();

                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Product product=uploads.get(position);
                        deleteProduct(product.getId());
                        alertDialog.dismiss();
                    }
                });



            }
        }));

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
//    private void showProductDetails(){
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        LayoutInflater layoutInflater=getLayoutInflater();
//        final View dialogView=layoutInflater.inflate(R.layout.product_description,null);
//        builder.setView(dialogView);
//        final EditText etName=(EditText)dialogView.findViewById(R.id.etProductName);
//        final EditText etPrice=(EditText)dialogView.findViewById(R.id.etProductPrices);
//        final EditText etQuantity=(EditText)dialogView.findViewById(R.id.etProductQuantitys);
//        final Button btClose=(Button)dialogView.findViewById(R.id.btClose);
//        builder.setTitle("Product Details");
//        final AlertDialog alertDialog=builder.create();
//        alertDialog.show();
//
//    }
    private boolean updateProductDescription(String id,String url,String name,String price,String quantity){

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("product").child(id);
        Product product=new Product(id,name,quantity,price,url);
        databaseReference.setValue(product);
        Toast.makeText(this,"Update successfully",Toast.LENGTH_LONG).show();
        return  true;
    }
    private void deleteProduct(String id){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("product").child(id);
        databaseReference.removeValue();
        Toast.makeText(this,"product deleted",Toast.LENGTH_LONG).show();

    }


}
