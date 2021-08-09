package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;


public class DoctorMainActivity extends AppCompatActivity {

    public static final String USERNAME_INTENT = "username";
    public static final String PATIENT_USERNAME_INTENT = "patient_username";
    String doctor_username;
    Spinner sp;

    ArrayList<String> upcoming_appointment_list;
    ArrayList<String> patient_usernames_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        setTitle("Medical Clinic");

        Intent intent = getIntent();
        doctor_username = intent.getStringExtra(DoctorLoginActivity.USERNAME_INTENT); // CHANGE INTENT

        TextView doctor_display = findViewById(R.id.textView11);
        upcoming_appointment_list = new ArrayList<String>();
        upcoming_appointment_list.add("Select an appointment:");

        patient_usernames_list = new ArrayList<String>();
        patient_usernames_list.add("This is a list of patient usernames in order of doctor's appointments");

        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String doctor_name = dataSnapshot.child("doctors").child(doctor_username).child("name").getValue().toString();
                doctor_display.setText("Dr. " + doctor_name);

                Doctor doctor_update = new Doctor();
                try {
                    doctor_update.update_appointments(doctor_username);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                TextView appointment_display = findViewById(R.id.textView13);
                String appointment_list = "";

                for (DataSnapshot child:dataSnapshot.child("doctors").child(doctor_username).child("upcoming_appointments").getChildren()) {
                    String patientUsername = child.child("patient").child("username").getValue().toString();
                    String date = child.child("date").getValue().toString();
                    String patient_name = dataSnapshot.child("patients").child(patientUsername).child("name").getValue().toString();
                    appointment_list += "Patient: " + patient_name + "\nDate: " + date + "\n\n";

                    upcoming_appointment_list.add("Patient: " + patient_name + ", Date: " + date);
                    patient_usernames_list.add(patientUsername);
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

        sp = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, upcoming_appointment_list);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(spinnerArrayAdapter);
    }

    public void viewPatientInfoOnClick(View view) {
        String patient = String.valueOf(sp.getSelectedItem());
        if (patient.equals("Select an appointment:")) {
            Toast.makeText(DoctorMainActivity.this,"Please choose an appointment",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, ViewPatientInfoActivity.class);
            intent.putExtra(USERNAME_INTENT, doctor_username);

            int indexOfPatient = upcoming_appointment_list.indexOf(patient);
            String patient_username = patient_usernames_list.get(indexOfPatient);
            intent.putExtra(PATIENT_USERNAME_INTENT, patient_username);

            startActivity(intent);
        }
    }

    public void doctorLeaveClinic(View view) {

        Intent intent = getIntent();

        Intent sendDoctor = new Intent(this, MainActivity.class); // CHANGE INTENT
        sendDoctor.putExtra(USERNAME_INTENT, doctor_username);
        startActivity(sendDoctor);
    }

    public void doctorLogOutOnClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void doctorRefreshOnClick(View view) {

        Intent intent = getIntent();

        Intent sendDoctor = new Intent(this, DoctorMainActivity.class);
        sendDoctor.putExtra(USERNAME_INTENT, doctor_username);
        startActivity(sendDoctor);
    }

    public void doctorAvailabilitiesOnClick(View view) {
        Intent intent = getIntent();

        Intent sendDoctor = new Intent(this, DoctorAvailabilitiesActivity.class);
        sendDoctor.putExtra(USERNAME_INTENT, doctor_username);
        startActivity(sendDoctor);
    }

}