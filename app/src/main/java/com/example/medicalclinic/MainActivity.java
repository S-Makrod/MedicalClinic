package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        //PatientUser p = new Patient("John Doe", "johnpassword123", "Male", "JohnD", "Feb 1st 2001");
        String d1_format = "07-1-2029 12:45:44";
        String d2_format = "07-1-2025 15:43:00";
        String d3_format = "09-1-2021 15:43:00";
        String d4_format = "07-1-2026 15:43:00";
        String d5_format = "07-1-2015 15:43:00";
        String d6_format = "07-1-2010 15:43:00";
        String d8_format = "08-1-2021 15:03:00";
        String d9_format = "08-1-2021 17:56:30";
        String d10_format = "08-1-2021 17:56:40";
        String d11_format = "08-1-2021 17:56:50";
        String d12_format = "08-1-2021 17:56:00";
        //Database.ref.child("patients").child(p.getUsername()).setValue(p);
        PatientUser pBook = new Patient();
        /*try {
            pBook.book("AnindroS", Appointment.dateParser.parse(d9_format), "JohnD");
            pBook.book("SaadM", Appointment.dateParser.parse(d10_format), "JohnD");
            pBook.book("SaadM", Appointment.dateParser.parse(d11_format), "JohnD");
            pBook.book("KatherinaL", Appointment.dateParser.parse(d12_format), "JohnD");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        PatientUser testp = new Patient();
        try {
            testp.update_appointments("JohnD");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}