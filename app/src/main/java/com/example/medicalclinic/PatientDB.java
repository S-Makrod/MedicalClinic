package com.example.medicalclinic;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientDB implements Database<PatientUser>{
    private static boolean isRecordExist=false;
    private static int code=0;
    final String child = "patients";

    public PatientDB()
    {
        code=0;
        isRecordExist=false;
    }

    @Override
    public boolean search(String username) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("patients");
        ref.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    isRecordExist=true;

                } else {
                    isRecordExist=false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return isRecordExist;
    }

    @Override
    public void add(PatientUser patient) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("patients").child(patient.getUsername()).setValue(patient);
    }


    public Integer loginUser(String userName, String password) {

        //Login for FireBase Authentication
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("patients");
        if(userName!=null && password!=null) {
            ref.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Patient patient = dataSnapshot.getValue(Patient.class);
                        if (patient != null) {
                            if (patient.getPassword().equals(password))
                                code = 1;
                        } else {
                            code = -1;
                        }


                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return new Integer(code);
    }

    public Patient getPatient(String userName)
    {
        final Patient[] patient = new Patient[1];
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("patients");
        ref.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    patient[0] = dataSnapshot.getValue(Patient.class);
                    System.out.println("**************\n Yes Got it2\n**********************");
                    if(patient[0]!=null)
                        System.out.println("**************\n Yes Got it3\n**********************");


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return patient[0];

    }
}
