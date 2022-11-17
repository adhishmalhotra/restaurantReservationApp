package com.example.restaurantreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class ReviewDetailsActivity extends AppCompatActivity {
    HashMap<String,String> reservationInfo;
    TextView txtDetails;
    Button btnSubmit;
    Button btnPrevious;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);
        HashMap<String,String> reservationInfo;
        reservationInfo = (HashMap<String,String>) getIntent().getSerializableExtra("reservationInfo");
        txtDetails = findViewById(R.id.reviewDetails);
        btnSubmit =findViewById(R.id.submitReservation);
        btnPrevious = findViewById(R.id.editReservation);
        String txtOutput;
        String name = reservationInfo.get("Name");
        String email = reservationInfo.get("Email");
        String phoneNumber = reservationInfo.get("PhoneNumber");
        String partySize = reservationInfo.get("PartySize");
        String seatingType = reservationInfo.get("SeatingType");
        String date = reservationInfo.get("Date");
        String time = reservationInfo.get("Time");
        String specialRequest = reservationInfo.get("SpecialRequest");
        txtOutput = "Name: " + "\t" + name + "\r\n"
                + "Email: " + "\t" + email + "\r\n"
                + "Phone Number: " + "\t" + phoneNumber + "\r\n"
                + "Party Size: " + "\t" + partySize + "\r\n"
                + "Date: " + "\t" + date + "\r\n"
                + "Time: " + "\t" + time + "\r\n"
                + "Seating Type: " + "\t" + seatingType + "\r\n"
                + "Special Request: " + "\t" + specialRequest + "\r\n";
        txtDetails.setText(txtOutput);
    }
}