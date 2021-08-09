package com.example.medicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editName, editDateOfBirth, editUsername, editPassword;
    private String name, username, dateOfBirth, password,gender;
    private Database<PatientUser> database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);
        setTitle("Medical Clinic");

        Button signUp = (Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        signUp();
    }

    public void signUp() {
        editName = (EditText) findViewById(R.id.name);
        editDateOfBirth = (EditText) findViewById(R.id.dateOfBirth);
        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);
        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int genderGroupId = genderGroup.getCheckedRadioButtonId();
        RadioButton genderChosen = (RadioButton) findViewById(genderGroupId);
        database = new PatientDB();

        name = editName.getText().toString().trim();
        gender = genderChosen.getText().toString().trim();
        dateOfBirth = editDateOfBirth.getText().toString();
        username = editUsername.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        if (isValidated()) {

            Intent intent = new Intent(this, PatientLoginActivity.class);

            FirebaseDatabase database2 = FirebaseDatabase.getInstance();
            DatabaseReference ref=database2.getReference("patients");

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(username).exists()) {
                        editUsername.setError("Username has been taken. Please enter another username!");

                    } else {
                        PatientUser patient = new Patient(name, password, gender, username, dateOfBirth);
                        database.add(patient);
                        Toast.makeText(PatientSignUpActivity.this,"Account has been created.",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private boolean isValidated() {

        if (name.isEmpty()) {
            editName.setError("Please enter your name!");
            return false;
        }

        Pattern namePattern = Pattern.compile("[A-Z][a-z]* [A-Z][a-z]*");
        Matcher checkName = namePattern.matcher(name);
        if (!checkName.matches()) {
            editName.setError("Please enter a valid name! Example: John Doe");
            return false;
        }

        if (dateOfBirth.isEmpty()) {
            editDateOfBirth.setError("Please enter your date of birth!");
            return false;
        }

        Pattern datePattern = Pattern.compile("(0[1-9]|1[0-2])/(0[1-9]|1\\d|2\\d|3[0-1])/(19\\d{2}|20\\d{2})");
        Matcher checkDate = datePattern.matcher(dateOfBirth);
        if (!checkDate.matches()) {
            editDateOfBirth.setError("Please enter a valid date! Format: MM/DD/YYYY");
            return false;
        }

        if (username.isEmpty()) {
            editUsername.setError("Please enter a username!");
            return false;
        }

        Pattern usernamePattern = Pattern.compile("[\\w]{6,20}");
        Matcher checkUsername = usernamePattern.matcher(username);
        if (!checkUsername.matches()) {
            editUsername.setError("Please enter a valid username! Minimum of 6 characters." +
                    "Only letters, digits, and underscores allowed.");
            return false;
        }

        if (password.isEmpty()) {
            editPassword.setError("Please enter a password!");
            return false;
        }

        Pattern passwordPattern = Pattern.compile("[\\w!@#$%^&*]{6,20}");
        Matcher checkPassword = passwordPattern.matcher(password);
        if (!checkPassword.matches()) {
            editPassword.setError("Please enter a valid password! Minimum of 6 characters.");
            return false;
        }

        return true;
    }
}