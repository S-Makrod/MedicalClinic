package com.example.medicalclinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DoctorSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editName, editUsername, editPassword;
    private String name, username, password, gender;
    private List<String> specializationsList;
    private Database<DoctorUser> database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        setTitle("Medical Clinic");

        Button signUp = (Button) findViewById(R.id.doctorSignUp);
        signUp.setOnClickListener(this);

        Intent specializationIntent = getIntent();
        String getSpecializations = specializationIntent.getStringExtra("SPECIALIZATIONS_INTENT");
        String[] parseSpecializations = getSpecializations.split(";");
        specializationsList = Arrays.asList(parseSpecializations);

        TextView textView = findViewById(R.id.doctorSpecializationsChosen);
        textView.setText(Arrays.toString(parseSpecializations));
    }

    @Override
    public void onClick(View v) {
        signUp();
    }

    public void signUp() {
        editName = (EditText) findViewById(R.id.doctorName);
        editUsername = (EditText) findViewById(R.id.doctorUsername);
        editPassword = (EditText) findViewById(R.id.doctorPassword);
        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.doctorRadioGroup);
        int genderGroupId = genderGroup.getCheckedRadioButtonId();
        RadioButton genderChosen = (RadioButton) findViewById(genderGroupId);
        database = new DoctorDB();

        name = editName.getText().toString().trim();
        gender = genderChosen.getText().toString().trim();
        username = editUsername.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        if (isValidated()) {
            Intent intent = new Intent(this, DoctorLoginActivity.class);
            startActivity(intent);

            FirebaseDatabase database2 = FirebaseDatabase.getInstance();
            DatabaseReference ref = database2.getReference("doctors");

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child(username).exists()) {
                        editUsername.setError("Username has been taken. Please enter another username!");
                    }
                    else {
                        DoctorUser doctor = new Doctor(name, password, gender, username, specializationsList);
                        database.add(doctor);
                        Toast.makeText(DoctorSignUpActivity.this, "Account has been created.", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

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