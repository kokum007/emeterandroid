package com.example.emeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText loginName,loginPassword;
    private Button login;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = (EditText) findViewById(R.id.loginName);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();




        Button btn = (Button)findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.login:
                        login();
                        break;


                }
            }

            private void login() {

                String username = loginName.getText().toString().trim();
                String password =  loginPassword.getText().toString().trim();

                if(username.isEmpty()){
                    loginName.setError("E-mail is required");
                    loginName.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                    loginName.setError("please enter a valid email address");
                    loginName.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    loginPassword.setError("Password is required");
                    loginPassword.requestFocus();
                    return;

                }
                if (password.length()<6){
                    loginPassword.setError("Min length for password is six");
                    loginPassword.requestFocus();
                    return;

                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            //redirect to home page

                            startActivity(new Intent(ActivityLogin.this,Home.class));
                            progressBar.setVisibility(View.GONE);
                        }else {
                            Toast.makeText(ActivityLogin.this,"Failed to login please check your credentials",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        Button btn2 = (Button)findViewById(R.id.register);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this,SignUp.class));
            }
        });
        }
    }








