package com.example.emeter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    //private TextView mReading;

    private DatabaseReference mDatabase;

    private FirebaseUser user;

    //private DatabaseReference reference;

    private String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn = (Button) findViewById(R.id.buttonmeter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,GraphView.class));
            }
        });

        Button btn3 = (Button) findViewById(R.id.buttonBill);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Bill.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
       // reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


    final TextView meterReadingTextView = (TextView) findViewById(R.id.meter_value);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(userID).orderByChild("meterReading").limitToLast(1).addChildEventListener(new ChildEventListener() {

            //reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
        /*    @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
              User userProfile = snapshot.getValue(User.class);

              if(userProfile != null){
                  String meter_value = userProfile.meterReading;

                  meterReadingTextView.setText(meter_value);
              }
            } */

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                //String meterReading=dataSnapshot.child("meterReading").getValue().toString();

                Log.e(null, "case  " + dataSnapshot);
                    //int count=  ((int) dataSnapshot.getChildrenCount());
//              String meterData= dataSnapshot.getKey();
//                Log.e(null, "case  "+meterData);


                     String meter_value = dataSnapshot.child("meterReading").getValue().toString();
                     meterReadingTextView.setText(meter_value);
                    Log.e(null, "case na   "+meter_value);

                }



            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home.this,"Something went wrong!", Toast.LENGTH_LONG).show();

            }
        });
    }


    }

