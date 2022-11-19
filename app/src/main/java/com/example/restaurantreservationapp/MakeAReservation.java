package com.example.restaurantreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

public class MakeAReservation extends AppCompatActivity {
    HashMap<String,String> reservationInfo;
    EditText date,time,specialRequests;
    Spinner partySize,seatingType;
    Button btnNextPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_areservation);
        HashMap<String,String> reservationInfo;
        reservationInfo = (HashMap<String,String>) getIntent().getSerializableExtra("reservationInfo");
        partySize = findViewById(R.id.partySize);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        seatingType = findViewById(R.id.seatingType);
        specialRequests = findViewById(R.id.notes);
        btnNextPage = findViewById(R.id.nextReview);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numberOfPeople, android.R.layout.simple_spinner_dropdown_item);
        partySize.setAdapter(adapter);
        partySize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reservationInfo.put("PartySize",partySize.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.seatingType, android.R.layout.simple_spinner_dropdown_item);
        seatingType.setAdapter(adapter2);
        seatingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reservationInfo.put("SeatingType",seatingType.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtDate = date.getText().toString();
                String txtTime = time.getText().toString();
                String txtNotes = specialRequests.getText().toString();
                reservationInfo.put("Date",txtDate);
                reservationInfo.put("Time",txtTime);
                reservationInfo.put("SpecialRequest", txtNotes);
                Intent intent =new Intent(MakeAReservation.this, ReviewDetailsActivity.class);
                intent.putExtra("reservationInfo", reservationInfo);
                startActivity(intent);
            }
        });

    }
}