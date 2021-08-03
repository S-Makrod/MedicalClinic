package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Filter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.Date;

public class PatientMainActivity extends AppCompatActivity {

    public static final String USERNAME_INTENT = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);
        setTitle("Medical Clinic");

        Intent intent = getIntent();
        String patient_username = intent.getStringExtra(PatientLoginActivity.USERNAME_INTENT);

        TextView patient_display = findViewById(R.id.textView6);

        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String patient_name = dataSnapshot.child("patients").child(patient_username).child("name").getValue().toString();
                patient_display.setText(patient_name);

                Patient patient_update = new Patient();
                try {
                    patient_update.update_appointments(patient_username);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                TextView appointment_display = findViewById(R.id.textView8);
                String appointment_list = "";
                for (DataSnapshot child:dataSnapshot.child("patients").child(patient_username).child("upcoming_appointments").getChildren()) {
                    AppointmentSlot post = child.getValue(AppointmentSlot.class);
                    String doctorUsername = post.doctor.getUsername();
                    String doc = dataSnapshot.child("doctors").child(doctorUsername).child("name").getValue ().toString();
                    appointment_list += "Doctor: Dr. " + doc + "\nDate: " + post.getDate() + "\n\n";
                }

                appointment_display.setText(appointment_list);
                appointment_display.setMovementMethod(new ScrollingMovementMethod());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        };
        Database.ref.addValueEventListener(postListener);


    }

    public void patientBookOnClick(View view) {

        Intent intent = getIntent();
        String patient_username = intent.getStringExtra(PatientLoginActivity.USERNAME_INTENT);

        Intent sendPatient = new Intent(this, FilterDoctorActivity.class);
        sendPatient.putExtra(USERNAME_INTENT, patient_username);
        startActivity(sendPatient);
    }

    public void patientLogOutOnClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void patientRefreshOnClick(View view) {

        Intent intent = getIntent();
        String patient_username = intent.getStringExtra(PatientLoginActivity.USERNAME_INTENT);

        Intent sendPatient = new Intent(this, PatientMainActivity.class);
        sendPatient.putExtra(USERNAME_INTENT, patient_username);
        startActivity(sendPatient);
    }
}