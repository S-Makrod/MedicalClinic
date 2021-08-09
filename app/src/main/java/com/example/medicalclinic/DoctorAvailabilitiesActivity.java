package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorAvailabilitiesActivity extends AppCompatActivity {
    String doctor_username;
    private EditText date;
    private String availability;

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
        doctor_username = intent.getStringExtra("DOC_USERNAME_INTENT");

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

    public void addAvailability(android.view.View view) throws ParseException {
        date = (EditText) findViewById(R.id.editDate);
        availability = date.getText().toString().trim();

        Pattern datePattern = Pattern.compile("((0\\d)|(1[0-2]))-(([0-2]\\d)|(3[0-1]))-\\d{4} (([0-1]\\d)|(2[0-3])):[0-5][0-9]:[0-5][0-9]");
        Matcher checkDate = datePattern.matcher(availability);

        if(checkDate.matches()){
            Date d = Appointment.dateParser.parse(availability);
            Date y = new Date(System.currentTimeMillis());

            if(d.after(y)) {
                Database.ref.child("doctors").child(doctor_username).child("availabilities").child(availability).setValue(availability);
                Toast.makeText(DoctorAvailabilitiesActivity.this,"Availability Added!",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(DoctorAvailabilitiesActivity.this,"Cannot Set an Availability in the Past!",Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(DoctorAvailabilitiesActivity.this,"Please Follow the Format Specified!",Toast.LENGTH_LONG).show();
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