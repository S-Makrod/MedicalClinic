package com.example.medicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        PatientUser p = new Patient("Sarah Bobster", "damdasl!@#(*", "Female", "TheSarahBobster", "Jan 1st 1990");
        ref.child("patients").child(p.getUsername()).setValue(p);

    }
}