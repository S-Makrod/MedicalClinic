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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientBookDoctorActivity extends AppCompatActivity {
    Spinner sp;
    String user;
    ArrayList<String> docs;
    ArrayList<String> docUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_book_doctor);

        setTitle("Medical Clinic");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("FILTER_GENDER");
        String specialist = intent.getStringExtra("FILTER_SPECIALIZATION");
        user = intent.getStringExtra("USERNAME_INTENT");

        // Initialize Choices for Doctors
        docs = new ArrayList<>();
        docUsers = new ArrayList<>();

        docs.add("Select A Doctor");
        docUsers.add("Empty");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("doctors");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot doc: snapshot.getChildren()) {
                   boolean add = false;
                    DataSnapshot docSpecs = doc.child("specializations");
                    String docName = doc.child("name").getValue().toString();
                    String specs = "";
                    String docGender = doc.child("gender").getValue().toString();

                    for (DataSnapshot specializations: docSpecs.getChildren()) {
                        String spec = specializations.getValue().toString();
                        specs += ", " + spec;
                        if(gender.equals("Does not matter")){
                            if(spec.equals(specialist)) add = true;
                        } else {
                            if(spec.equals(specialist) && docGender.equals(gender)) add = true;
                        }
                    }

                    if(add) {
                        docs.add("Dr. " + docName + specs);
                        docUsers.add(doc.child("username").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        };
        ref.addValueEventListener(listener);

        sp = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, docs);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(spinnerArrayAdapter);
    }

    public void selectDoctor(View view) {
        // Doc chosen
        String doc = String.valueOf(sp.getSelectedItem());

        if (doc.equals("Select A Doctor")) {
            Toast.makeText(PatientBookDoctorActivity.this,"Please Choose a Doctor",Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, PatientDoctorAvailabilitiesActivity.class);

            String docUsername = docUsers.get(docs.indexOf(doc));
            intent.putExtra("DOC_USERNAME_INTENT", docUsername);
            intent.putExtra("USERNAME_INTENT", user);
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