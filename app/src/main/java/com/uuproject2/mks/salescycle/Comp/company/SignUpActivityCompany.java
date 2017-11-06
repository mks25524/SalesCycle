package com.uuproject2.mks.salescycle.Comp.company;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.uuproject2.mks.salescycle.R;

public class SignUpActivityCompany extends AppCompatActivity implements View.OnClickListener{

    EditText editTextUserEmail,editTextPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_company);
        editTextUserEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        mAuth=FirebaseAuth.getInstance();

findViewById(R.id.buttonSignup).setOnClickListener(this);

    }
    private void registerUser(){
        String userEmail=editTextUserEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if(userEmail.isEmpty()){
            editTextUserEmail.setError("Email is required");
            editTextUserEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            editTextUserEmail.setError("Please enter a valid email address");
            editTextUserEmail.requestFocus();
            return;

        }
        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextUserEmail.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(userEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Registered successfull",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUpActivityCompany.this,ProfileActivityCompany.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else{

                    if(task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_LONG).show();

                    }else
                    {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                    //Toast.makeText(getApplicationContext(),"Some error occoured",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSignup:
                registerUser();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }


    }
}
