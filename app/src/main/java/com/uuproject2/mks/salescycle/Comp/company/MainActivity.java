package com.uuproject2.mks.salescycle.Comp.company;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.uuproject2.mks.salescycle.Comp.customer.CustomerLogin;
import com.uuproject2.mks.salescycle.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editTextUserEmail,editTextPassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        findViewById(R.id.textViewSignupp).setOnClickListener(this);
        findViewById(R.id.textViewSignupAuthority).setOnClickListener(this);
        findViewById(R.id.textViewCustomer).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();

        progressBar=(ProgressBar)findViewById(R.id.progressbar);

    }
    private void userLogin(){
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
        mAuth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(MainActivity.this,ProfileActivityCompany.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textViewSignupp:
                startActivity(new Intent(this,SignUpActivityCompany.class));
                break;
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.textViewSignupAuthority:
                startActivity(new Intent(this,AuthorityLoginActivity.class));
                break;
            case R.id.textViewCustomer:
                startActivity(new Intent(this,CustomerLogin.class));

        }


    }

}
