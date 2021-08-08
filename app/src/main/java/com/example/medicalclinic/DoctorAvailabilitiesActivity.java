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

public class DoctorAvailabilitiesActivity extends AppCompatActivity {

    String doctor_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_availabilities);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        setTitle("Medical Clinic");
        // showing the back button in action bar
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        doctor_username = intent.getStringExtra(DoctorMainActivity.USERNAME_INTENT);

        TextView availabilitiesTextView = findViewById(R.id.textView15);

        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String available = "";

                for (DataSnapshot child:dataSnapshot.child("doctors").child(doctor_username).child("availabilities").getChildren()) {
                    available += "• " + child.getValue().toString() + "\n\n";
                }

                availabilitiesTextView.setText(available);
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