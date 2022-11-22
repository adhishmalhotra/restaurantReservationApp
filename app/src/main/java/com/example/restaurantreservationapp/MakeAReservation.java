package com.example.restaurantreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MakeAReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    HashMap<String,String> reservationInfo;
    EditText date,time,specialRequests;
    Spinner partySize,seatingType;
    Button btnNextPage, btnPreviousPage, btnDateTime;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
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
        btnPreviousPage = findViewById(R.id.previousPage);
        btnDateTime = findViewById(R.id.btnDateTime);

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

        btnDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MakeAReservation.this, MakeAReservation.this, year, month, day);
                datePickerDialog.show();
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

        btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MakeAReservation.this, ContactInfoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MakeAReservation.this, MakeAReservation.this, hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        date.setText(myYear + "/" + myMonth + "/" + myday);
        time.setText(myHour + ":" + myMinute);
    }
}
