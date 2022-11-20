package com.example.restaurantreservationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ReviewDetailsActivity extends AppCompatActivity {
    FirebaseFirestore db;
    HashMap<String,String> reservationInfo;
    TextView txtDetails;
    Button btnSubmit;
    Button btnPrevious;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);
        //initializing the firestore db component
        db = FirebaseFirestore.getInstance();
        HashMap<String,String> reservationInfo;
        //hash map which will be uploaded to firebase db
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("bookings")
                        .add(reservationInfo)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ReviewDetailsActivity.this, "Entry uploaded to the db successfully", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ReviewDetailsActivity.this, "Entry could not be uploaded figure your stuff out!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }


}