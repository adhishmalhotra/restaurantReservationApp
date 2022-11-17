package com.example.restaurantreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ContactInfoActivity extends AppCompatActivity {

    HashMap<String, String> reservationInfo;
    EditText name, email, reemail, number;
    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        //declaration of all the elements within the form
        reservationInfo = new HashMap<>();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        reemail = findViewById(R.id.reemail);
        number = findViewById(R.id.number);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtName =name.getText().toString();
                String txtEmail = email.getText().toString();
                String txtReEmail = reemail.getText().toString();
                String txtNumber = number.getText().toString();
                reservationInfo.put("Name", txtName);
                reservationInfo.put("Email", txtEmail);
                reservationInfo.put("PhoneNumber", txtNumber);

                boolean allGood = false;
                if(txtEmail.equals(txtReEmail)){
                    allGood = true;
                }
                else{
                    Toast.makeText(ContactInfoActivity.this, "The emails must match", Toast.LENGTH_SHORT).show();
                }

                if(allGood){
                    Intent intent =new Intent(ContactInfoActivity.this, MakeAReservation.class);
                    intent.putExtra("reservationInfo", reservationInfo);
                    startActivity(intent);
                    System.out.println(reservationInfo);
                }
            }
        });

    }
}