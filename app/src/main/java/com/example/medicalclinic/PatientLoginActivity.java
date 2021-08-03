package com.example.medicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientLoginActivity extends AppCompatActivity {
    public static final String USERNAME_INTENT = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
    }
    public void patientLoginButtonOnClick(android.view.View view)
    {
        //Login for FireBase Authentication
        TextView patientUserNameTextBox=(TextView)findViewById(R.id.patientUserNameTextBox);
        TextView patientPasswordTextBox=(TextView)findViewById(R.id.patientPasswordTextBox);
        String userName= patientUserNameTextBox.getText().toString();
        String password=patientPasswordTextBox.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("patients");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Check if entered username is a registered user
                PatientDB patientDB = new PatientDB();

                if (dataSnapshot.child(userName).exists()) {
                    Patient patient = dataSnapshot.getValue(Patient.class);
                    String patient_pass = dataSnapshot.child(userName).child("password").getValue(String.class);
                    Log.i("info", patient_pass);
                    if(patient_pass.equals(password))
                    {
                        Intent intent=new Intent(PatientLoginActivity.this,PatientMainActivity.class);
                        intent.putExtra(USERNAME_INTENT, userName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(PatientLoginActivity.this,"Invalid Password",Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(PatientLoginActivity.this,"The entered username is not in our records",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void patientSignUpButtonOnClick(android.view.View view)
    {
        Intent intent=new Intent(this,PatientSignUpActivity.class);
        startActivity(intent);
    }

}