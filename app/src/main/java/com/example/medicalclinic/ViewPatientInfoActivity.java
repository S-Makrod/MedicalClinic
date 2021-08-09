package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewPatientInfoActivity extends AppCompatActivity {
    String patient_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_info);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        setTitle("Medical Clinic");
        // showing the back button in action bar
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        patient_username = intent.getStringExtra("USERNAME_INTENT");
        TextView patientInfoTextView = findViewById(R.id.textView6);
        TextView patientPastDoctorsTextView = findViewById(R.id.textView9);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String patient_name = snapshot.child("patients").child(patient_username).child("name").getValue().toString();
                String patient_gender = snapshot.child("patients").child(patient_username).child("gender").getValue().toString();
                String patient_dob = snapshot.child("patients").child(patient_username).child("date_of_birth").getValue().toString();

                patientInfoTextView.setText("Name: " + patient_name + "\n\nGender: " + patient_gender + "\n\nDate of Birth: " + patient_dob + "\n\n");

                String pastDoctors = "";
                for (DataSnapshot name: snapshot.child("patients").child(patient_username).child("doctors").getChildren()) {
                    String doctorName = name.getValue().toString();
                    pastDoctors += "• Dr. " + doctorName + "\n\n";
                }
                patientPastDoctorsTextView.setText(pastDoctors);
                patientPastDoctorsTextView.setMovementMethod(new ScrollingMovementMethod());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        Database.ref.addValueEventListener(listener);

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