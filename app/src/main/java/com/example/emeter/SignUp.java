package com.example.emeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText regPassword, regEmail, regAccNumber;
    private ProgressBar progressBar;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        regPassword = (EditText)findViewById(R.id.regPassword);
        regEmail = (EditText)findViewById(R.id.regEmail);
        regAccNumber =(EditText)findViewById(R.id.regAccNumber);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);





        Button btn = (Button)findViewById(R.id.buttonRegister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buttonRegister:
                        buttonRegister();
                        break;

                }

            }

            private void buttonRegister() {
                final String email = regEmail.getText().toString().trim();
                final String accNumber = regAccNumber.getText().toString().trim();
                final String password = regPassword.getText().toString().trim();

                //add validation here


                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    User user = new User(accNumber, email, password);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()) {
                                                Toast.makeText(SignUp.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }else{
                                                Toast.makeText(SignUp.this, "Failed to register User", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);


                                            }
                                        }
                                    });
                                }else{
                                        Toast.makeText(SignUp.this, "Failed to connect", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                }
                            }
                        });




            }
        });


    }
}
