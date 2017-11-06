package com.uuproject2.mks.salescycle.Comp.company;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uuproject2.mks.salescycle.R;

import java.util.HashMap;
import java.util.Map;

public class AddAuthorityActivity extends AppCompatActivity {
    private EditText etId,etPassword,name;
    private AutoCompleteTextView etCatagory;
    private Button btAdd;
    String []catagorys={"Sales Manager","Sales Man","Delivery Man","Customer"};
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_authority);
        etId= (EditText) findViewById(R.id.etAuthorityId);
        etPassword= (EditText) findViewById(R.id.etAuthirityPassword);
        etCatagory= (AutoCompleteTextView) findViewById(R.id.etAuthorityCatagory);
        name= (EditText) findViewById(R.id.nameAuthority);
        btAdd= (Button) findViewById(R.id.btAddAtuthority);


        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,catagorys);
        etCatagory.setThreshold(1);
        etCatagory.setAdapter(adapter);
        etCatagory.setTextColor(Color.BLUE);

        progressBar= (ProgressBar) findViewById(R.id.progressbar1);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addAuthority();

                String id=etId.getText().toString().trim();
                String pasword=etPassword.getText().toString().trim();
                String catagory=etCatagory.getText().toString().trim();
                String nameA=name.getText().toString().trim();
                mFirebaseDatabase=FirebaseDatabase.getInstance();
                mDatabaseReference=mFirebaseDatabase.getReference("authority").child(id);
        if(id.isEmpty()){
            etId.setError("Id  is required");
            etId.requestFocus();
            return;
        }
        if(pasword.isEmpty()){
            etPassword.setError("Id  is required");
            etPassword.requestFocus();
            return;
        }
        if(catagory.isEmpty()){
            etCatagory.setError("Id  is required");
            etCatagory.requestFocus();
            return;
        }
                progressBar.setVisibility(View.VISIBLE);
                Map<String,String>map=new HashMap<>();
                map.put("name",nameA);
                map.put("id",id);
                map.put("password",pasword);
                map.put("catagory",catagory);
                mDatabaseReference.setValue(map);
                Toast.makeText(getApplicationContext(),"Authority Added",Toast.LENGTH_LONG).show();

            }
        });


    }
//   public   void addAuthority(){
//       progressBar.setVisibility(View.VISIBLE);
//        String id=etId.getText().toString().trim();
//        String pasword=etPassword.getText().toString().trim();
//        String catagory=etCatagory.getText().toString().trim();
//        String nameA=name.getText().toString().trim();
////        if(id.isEmpty()){
////            etId.setError("Id  is required");
////            etId.requestFocus();
////            return;
////        }
////        if(pasword.isEmpty()){
////            etPassword.setError("Id  is required");
////            etPassword.requestFocus();
////            return;
////        }
////        if(catagory.isEmpty()){
////            etCatagory.setError("Id  is required");
////            etCatagory.requestFocus();
////            return;
////        }
//
//        Map<String,String>map=new HashMap<>();
//        map.put("name",nameA);
//        map.put("id",id);
//        map.put("password",pasword);
//        map.put("catagory",catagory);
//        mDatabaseReference.setValue(map);
//    }


}
