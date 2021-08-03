package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FilterDoctorActivity extends AppCompatActivity {
    public static final String USERNAME_INTENT = "username";
    public static final String FILTER_GENDER = "filter_gender";
    public static final String FILTER_SPECIALIZATION = "filter_specialization";
    String patient_username;

    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_doctor);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        setTitle("Medical Clinic");
        // showing the back button in action bar
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        patient_username = intent.getStringExtra(PatientLoginActivity.USERNAME_INTENT);

        // Initialize Choices for Specialization
        ArrayList<String> specs = new ArrayList<String>();

        specs.add("Select a specialization:");


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("doctors");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot doc: snapshot.getChildren()) {
                    DataSnapshot docSpecs = doc.child("specializations");
                    for (DataSnapshot specializations: docSpecs.getChildren()) {
                        String spec = specializations.getValue().toString();
                        if (!specs.contains(spec))
                            specs.add(spec);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addValueEventListener(listener);

        sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, specs);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(spinnerArrayAdapter);
    }

    public void collectFilters(View view) {
        // Gender Filter
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton rb = (RadioButton) findViewById((rg.getCheckedRadioButtonId()));

        // Specialty Filter
        String spec_filter = String.valueOf(sp.getSelectedItem());

        if (rg.getCheckedRadioButtonId() == -1) {
            Toast.makeText(FilterDoctorActivity.this,"Please choose a preference for gender",Toast.LENGTH_LONG).show();
        }
        else if (spec_filter.equals("Select a specialization:")) {
            Toast.makeText(FilterDoctorActivity.this,"Please choose a specialization",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, PatientBookDoctorActivity.class);

            intent.putExtra(USERNAME_INTENT, patient_username);

            String gender_filter = rb.getText().toString();
            intent.putExtra(FILTER_GENDER, gender_filter);

            intent.putExtra(FILTER_SPECIALIZATION, spec_filter);

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