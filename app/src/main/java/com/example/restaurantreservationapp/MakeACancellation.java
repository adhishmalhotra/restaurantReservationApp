package com.example.restaurantreservationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class MakeACancellation extends AppCompatActivity {
    HashMap<String, String> cancellationinfo;
    EditText emailtext;
    EditText phonenumber;
    FirebaseFirestore db; //initalize the firebase store
    Button checkbtn;
    TextView retrievebox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_acancellation);
        cancellationinfo = new HashMap<>();
        emailtext = findViewById(R.id.editTextTextEmailAddress2);
        phonenumber = findViewById(R.id.editTextPhone);
        db = FirebaseFirestore.getInstance();
        retrievebox = findViewById(R.id.canceldetail);
        checkbtn = findViewById(R.id.button);



        //on checkbtn need to see if the persons email and phonenumber is stored in firebase
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("bookings")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                           
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    StringBuffer buffer = new StringBuffer();//mutable string
                                    boolean exist = false;

                                    for (DocumentSnapshot documentSnapshot: task.getResult()){
                                        if (emailtext.getText().toString().equals(documentSnapshot.getString("Email"))) {
                                            buffer.append(documentSnapshot.getString("Email") + "\n");
                                            //Query query = emailref.whereEqualTo("Email",buffer);
                                            //db.collection("bookings").document().delete();
                                            exist = true;
                                        }
                                    }
                                    //retrievebox.setText(buffer);
                                    if (exist) {

                                        Toast.makeText(MakeACancellation.this, "Your Reservation Exists ", Toast.LENGTH_SHORT).show();
                                        String txtEmail = emailtext.getText().toString();
                                        String txtNumber = phonenumber.getText().toString();
                                        cancellationinfo.put("Email", txtEmail);
                                        cancellationinfo.put("Phone Number", txtNumber);
                                        Intent intent = new Intent(MakeACancellation.this, Confirmation_Cancellation_Page.class);
                                        intent.putExtra("cancellationinfo", cancellationinfo);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(MakeACancellation.this, "Entry Does Not Exists", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MakeACancellation.this, "Your Reservation Does not Exist in our Database", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }
}