package com.example.restaurantreservationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Confirmation_Cancellation_Page extends AppCompatActivity {

    FirebaseFirestore db;
    HashMap<String,String>  cancellationinfo;
    TextView txtDetails;
    Button btnSubmit;
    Button btnPrevious;
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_cancellation_page);
        db = FirebaseFirestore.getInstance();
        cancellationinfo = (HashMap<String, String>) getIntent().getSerializableExtra("cancellationinfo");
        txtDetails = findViewById(R.id.canceldetails);
        btnSubmit = findViewById(R.id.submitCancellation);
        String txtOutput;
        txtOutput = "Email: " + cancellationinfo.get("Email");
        txtDetails.setText(txtOutput);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("bookings")
                        .document(cancellationinfo.get("Email")).delete();

    }
});
    }
}


