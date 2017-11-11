package com.uuproject2.mks.salescycle.Comp.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uuproject2.mks.salescycle.Comp.salesmanager.Customer;
import com.uuproject2.mks.salescycle.R;

import java.util.Objects;

public class CustomerLogin extends AppCompatActivity {
    private EditText customerId;
    private Button btCustomerLogin;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        customerId= (EditText) findViewById(R.id.etCustomerLoginIds);
       // customerPassword=(EditText)findViewById(R.id.etCustomerLoginPassword);
        btCustomerLogin= (Button) findViewById(R.id.btCustomerLogin);
        btCustomerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String customerMobile=customerId.getText().toString().trim();
                mDatabaseReference= FirebaseDatabase.getInstance().getReference();
              //  Customer customer=new Customer();
               // String id=customer.getId();
               // Toast.makeText(getApplicationContext(),"id:"+id,Toast.LENGTH_LONG).show();
                DatabaseReference ref=mDatabaseReference.child("customer").child(customerMobile);
                Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_LONG).show();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Customer customer=dataSnapshot.getValue(Customer.class);
                        String customerPassword=customer.getCustomerPassword();
                        String customerName=customer.getCustomerName();
                        if(Objects.equals("customer",customerPassword)){
                            Toast.makeText(getApplicationContext(),"Password match"+customerName,Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),CustomerProfileActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Some error occoured !!!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
