package com.example.medicalclinic;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorDB implements Database<DoctorUser>, DatabaseDelete{
    private boolean isRecordExist=false;
    final String child = "doctors";
    private static int errorCode=0;

    @Override
    public boolean search(String username) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref=database.getReference("doctors");
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
    public void add(DoctorUser doctor) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("doctors").child(doctor.getUsername()).setValue(doctor);

    }

    @Override
    public void delete(String username) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("doctors").child(username).removeValue();
    }


    public Integer loginUser(String userName, String password) {
        //Login for FireBase Authentication
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("doctors");
        if(userName!=null && password!=null) {
            ref.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                      //  Doctor doctor=dataSnapshot.getValue(Doctor.class);
                        //if (doctor != null) {
                          //  if (doctor.getPassword().equals(password))
                            //    errorCode = 1;
                      //  } else {
                        //    errorCode = -1;
                       // }


                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return new Integer(errorCode);
    }
}
