package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;

public class PatientPreviousDoctorsActivity extends AppCompatActivity {

    public static final String USERNAME_INTENT = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_previous_doctors);
        setTitle("Medical Clinic");

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        setTitle("Medical Clinic");
        // showing the back button in action bar
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String patient_username = intent.getStringExtra(PatientLoginActivity.USERNAME_INTENT);

        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Patient patient_update = new Patient();
                try {
                    patient_update.update_appointments(patient_username);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                TextView doctors_display = findViewById(R.id.textView8);
                String doctors_list = "";
                for (DataSnapshot child:dataSnapshot.child("patients").child(patient_username).child("doctors").getChildren()) {
                    String doctorUsername = child.getValue(String.class);

                    //Need to add this in after DoctorDB has been implemented, and remove the two lines below
                    /*DoctorDB doctorDatabase = new DoctorDB();
                    if (doctorDatabase.search(doctorUsername)) {
                        String doc = dataSnapshot.child("doctors").child(doctorUsername).child("name").getValue ().toString();
                        doctors_list += "Doctor: Dr. " + doc + "\n\n";
                    }*/

                    String doc = dataSnapshot.child("doctors").child(doctorUsername).child("name").getValue ().toString();
                    doctors_list += "Doctor: Dr. " + doc + "\n\n";
                }

                doctors_display.setText(doctors_list);
                doctors_display.setMovementMethod(new ScrollingMovementMethod());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        };
        Database.ref.addValueEventListener(postListener);

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