package com.example.medicalclinic;

import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientDB implements Database<PatientUser>{
    private static boolean isRecordExist=false;
    final String child = "patients";
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
}
