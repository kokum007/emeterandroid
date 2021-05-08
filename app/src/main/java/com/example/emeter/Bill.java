package com.example.emeter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bill extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private FirebaseUser user;
    private String userID;
    Double meter_value;
    Double Price;
    Double Tax = 150.00;
    Double Fix = 400.00;
    Double Total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        data();
        //if (meter_value < 10){}


//        Integer meter_value = 100;
//
//        ReadingTextView.setText(meter_value);
//

    }
    public void data(){

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        final TextView ReadingTextView = (TextView) findViewById(R.id.meter_value);
        final TextView kwh = (TextView) findViewById(R.id.textViewKwh);
        final TextView tax = (TextView) findViewById(R.id.textViewTax);
        final TextView fix = (TextView) findViewById(R.id.textViewFix);
        final TextView total = (TextView) findViewById(R.id.textViewTotal);

        fix.setText(String.format("%.2f",Fix));
        tax.setText(String.format("%.2f",Tax));

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(userID).orderByChild("meterReading").limitToLast(1).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                meter_value =  dataSnapshot.child("meterReading").getValue(Double.class);
                ReadingTextView.setText(String.format("%.2f",meter_value));
                Log.e(null,"reading as double "+meter_value);
                    if(meter_value<=10){
                         Price = 100.00;
                         kwh.setText(String.format("%.2f",Price));

                        //Log.e(null,"Show price "+Price);
                    }else if (meter_value>10 && meter_value<=20){
                        Price = 250.00;
                        kwh.setText(String.format("%.2f",Price));

                    }else if (meter_value>20 && meter_value<=30){
                        Price = 500.00;
                        kwh.setText(String.format("%.2f",Price));
                    } else if (meter_value>30 && meter_value<=40) {
                        Price = 800.00;
                        kwh.setText(String.format("%.2f", Price));
                    }else {
                        Price = 1200.00;
                        kwh.setText(String.format("%.2f", Price));
                    }

                    Total = Price + Tax + Fix;
                    total.setText(String.format("%.2f", Total));
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
                Toast.makeText(Bill.this,"Something went wrong!", Toast.LENGTH_LONG).show();
            }





        });


    }


}


