package com.example.emeter;

public class User {

    public String accNumber, email ;
    public int meterReading;
    public boolean power;
    //public  int meterReading=0; this was commented because it define as integer

    public User(){

    }
    public User(String accNumber, String email, int meterReading, boolean power){
        this.accNumber = accNumber;
        this.email = email;
        //this.password = password;
        this.meterReading = meterReading;
        this.power = power;
    }
}
