package com.uuproject2.mks.salescycle.Comp.company;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.uuproject2.mks.salescycle.Comp.deleverman.ProfileDelevermanActivity;
import com.uuproject2.mks.salescycle.Comp.salesman.ProfileSalesManActivity;
import com.uuproject2.mks.salescycle.Comp.salesmanager.ProfileSalesManagerActivity;
import com.uuproject2.mks.salescycle.R;

import java.util.Objects;

public class AuthorityLoginActivity extends AppCompatActivity {
    private EditText etId;
    private Button btLogin;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority_login);
        etId= (EditText) findViewById(R.id.etAuthorityLoginId);
       // etPassword= (EditText) findViewById(R.id.etAuthirityPassword);
        btLogin= (Button) findViewById(R.id.btAuthorityLogin);
       // mFirebaseDatabase=FirebaseDatabase.getInstance();
       // mDatabaseReference=mFirebaseDatabase.getReference().child("authority");
        //store id to shared preference for passing salesBySalesManActivity
        String pt=etId.getText().toString().trim();
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor=prefs.edit();
//        editor.putString("id",pt);
//        editor.apply();
       btLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String id=etId.getText().toString().trim();
      //String inputPassword=etPassword.getText().toString().trim();
               mDatabaseReference=FirebaseDatabase.getInstance().getReference();
               DatabaseReference ref=mDatabaseReference.child("authority").child(id);
              // DatabaseReference ref2=mDatabaseReference.child("authority").child(id).child("name");
editor.putString("id",id);
               editor.apply();


               ref.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                    //String name=dataSnapshot.getValue(String.class);
                      // String password=dataSnapshot.getValue(String.class);
                      // Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_LONG).show();
                       Authority authority=dataSnapshot.getValue(Authority.class);
                      // String inputId=etId.getText().toString().trim();
                     // String inputPassword=etPassword.getText().toString().trim();
                      // String dbId=authority.getId();

                       String dbPassword=authority.getPassword();
                       String dbCatagory=authority.getCatagory();
                       String name=authority.getName();
                   // Toast.makeText(getApplicationContext(),"catagory"+dbCatagory+"sf"+dbPassword,Toast.LENGTH_LONG).show();
                       if(Objects.equals("salescycle",dbPassword)){
                           if(Objects.equals(dbCatagory, "Delivery Man")){
                               Toast.makeText(getApplicationContext(),"Login as a Delivery Man",Toast.LENGTH_LONG).show();
                               Intent intent=new Intent(getApplicationContext(),ProfileDelevermanActivity.class);
                               intent.putExtra("name",name);
                               startActivity(intent);
                           }else  if(Objects.equals(dbCatagory, "Sales Manager")){
                               Toast.makeText(getApplicationContext(),"Login as Sales Manager",Toast.LENGTH_LONG).show();
                               Intent intent=new Intent(getApplicationContext(),ProfileSalesManagerActivity.class);
                               intent.putExtra("name",name);
                               startActivity(intent);
                           }else  if(Objects.equals(dbCatagory, "Sales Man")){
                               Toast.makeText(getApplicationContext(),"Login as a Sales Man",Toast.LENGTH_LONG).show();
                               Intent intent=new Intent(getApplicationContext(),ProfileSalesManActivity.class);
                               intent.putExtra("name",name);
                               startActivity(intent);
                           }
                       }


      //Toast.makeText(getApplicationContext(),"name:"+dbPassword,Toast.LENGTH_LONG).show();
//                       Toast.makeText(getApplicationContext(),"Password:"+authority.getPassword(),Toast.LENGTH_LONG).show();
//                       Toast.makeText(getApplicationContext(),"name:"+authority.getCatagory(),Toast.LENGTH_LONG).show();
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
