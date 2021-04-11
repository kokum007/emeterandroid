package com.example.emeter;

public class User {

    public String accNumber, email, meterReading;
    //public  int meterReading=0; this was commented because it define as integer

    public User(){

    }
    public User(String accNumber, String email, String meterReading){
        this.accNumber = accNumber;
        this.email = email;
        //this.password = password;
        this.meterReading = meterReading;
    }
}
