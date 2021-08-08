package com.example.medicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Medical Clinic");
    }

    // When user presses button
    public void patientButtonOnClick(android.view.View view) {
        Intent intent=new Intent(this,PatientLoginActivity.class);
        startActivity(intent);
    }

    public void doctorButtonOnClick(android.view.View view) {
        Intent intent=new Intent(this,DoctorLoginActivity.class);
        startActivity(intent);
    }
}