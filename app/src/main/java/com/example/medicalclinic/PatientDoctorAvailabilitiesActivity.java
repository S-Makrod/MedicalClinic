package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PatientDoctorAvailabilitiesActivity extends AppCompatActivity {
    Spinner sp;
    ArrayList<String> appointments;
    String doctor;
    String username;
    public static final String USERNAME_INTENT = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doctor_availabilities);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        setTitle("Medical Clinic");
        // showing the back button in action bar
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        doctor = intent.getStringExtra(PatientBookDoctorActivity.DOC_USERNAME);
        username = intent.getStringExtra(PatientBookDoctorActivity.USERNAME_INTENT);
        appointments = new ArrayList<>();
        appointments.add("Select An Appointment");

        TextView t = (TextView)findViewById(R.id.textView3);
        t.setText(doctor);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("doctors/" + doctor);
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot docAvail = snapshot.child("availabilities");

                for (DataSnapshot meeting: docAvail.getChildren()) {
                    appointments.add(meeting.getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        };
        ref.addValueEventListener(listener);

        sp = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, appointments);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(spinnerArrayAdapter);
    }

    public void book(View view) throws ParseException {
        // Doc chosen
        String appointment = String.valueOf(sp.getSelectedItem());

        if (appointment.equals("Select An Appointment")) {
            Toast.makeText(PatientDoctorAvailabilitiesActivity.this,"Please Choose an Appointment",Toast.LENGTH_LONG).show();
        } else {
            SimpleDateFormat dateParser = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            Date d = dateParser.parse(appointment);
            Patient p = new Patient();
            p.book(doctor, d, username);

            Intent intent = new Intent(this, PatientMainActivity.class);
            intent.putExtra(USERNAME_INTENT, username);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}