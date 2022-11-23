package com.example.restaurantreservationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
        next = findViewById(R.id.btnNextContact);

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
                if (TextUtils.isEmpty(txtName) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtReEmail) || TextUtils.isEmpty(txtNumber)){
                    Toast.makeText(ContactInfoActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }else{
                    if(txtEmail.equals(txtReEmail)){
                        allGood = true;
                    }
                    else{
                        Toast.makeText(ContactInfoActivity.this, "The emails must match", Toast.LENGTH_SHORT).show();
                    }
                }

                if(allGood){
                    Intent intent =new Intent(ContactInfoActivity.this, MakeAReservation.class);
                    intent.putExtra("reservationInfo", reservationInfo);
                    startActivity(intent);
//                    System.out.println(reservationInfo);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Name",name.getText().toString());
        outState.putString("Email",email.getText().toString());
        outState.putString("Reemail",reemail.getText().toString());
        outState.putString("PhoneNumber",number.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Intent intent_obj = new Intent();
        String txtname = intent_obj.getStringExtra("Name");
        String txtemail = intent_obj.getStringExtra("Email");
        String txtreemail = intent_obj.getStringExtra("Reemail");
        String txtphoneNumber = intent_obj.getStringExtra("PhoneNumber");
        name.setText(txtname);
        email.setText(txtemail);
        reemail.setText(txtreemail);
        number.setText(txtphoneNumber);
    }
}