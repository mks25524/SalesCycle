package com.uuproject2.mks.salescycle.Comp.salesmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uuproject2.mks.salescycle.R;

public class AddCustomerActivity extends AppCompatActivity {
    private EditText etCustomerName,etCustomerShopName,etCustomerMobile,etCustomerAddress,etCustomerPasseord;
    private Button btAddCustomer;
    DatabaseReference databaseCustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        etCustomerName= (EditText) findViewById(R.id.etAddCustomerName);
        etCustomerShopName= (EditText) findViewById(R.id.etAddCustomerShopName);
        etCustomerMobile= (EditText) findViewById(R.id.etAddCustomerMobile);
        etCustomerAddress= (EditText) findViewById(R.id.etAddCustomerAddress);
        etCustomerPasseord= (EditText) findViewById(R.id.etAddCustomerPassword);
        btAddCustomer= (Button) findViewById(R.id.btAddCustomer);

       // databaseCustomer= FirebaseDatabase.getInstance().getReference("customer").child();
        btAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCustomer();
            }
        });
    }
    private void addCustomer(){
        String name=etCustomerName.getText().toString().trim();
        String shopName=etCustomerShopName.getText().toString().trim();
        String mobileNo=etCustomerMobile.getText().toString().trim();
        String address=etCustomerAddress.getText().toString().trim();
        String password=etCustomerPasseord.getText().toString().trim();
        databaseCustomer= FirebaseDatabase.getInstance().getReference("customer");
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(shopName) && !TextUtils.isEmpty(mobileNo) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(password)){
           // String  id=databaseCustomer.push().getKey();
            Customer customer=new Customer(name,shopName,mobileNo,address,password);
            databaseCustomer.child(mobileNo).setValue(customer);
            //blank again
            etCustomerName.setText("");
            etCustomerShopName.setText("");
            etCustomerMobile.setText("");
            etCustomerAddress.setText("");
            etCustomerPasseord.setText("");

            Toast.makeText(this,"Customer Added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Please fill up everything",Toast.LENGTH_LONG).show();
        }
    }
}
