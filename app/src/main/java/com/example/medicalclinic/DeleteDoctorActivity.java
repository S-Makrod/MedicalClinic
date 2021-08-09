package com.example.medicalclinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteDoctorActivity extends AppCompatActivity {
    private String docUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_doctor);
        setTitle("Medical Clinic");

        Intent usernameIntent = getIntent();
        docUsername = usernameIntent.getStringExtra("DOC_USERNAME_INTENT");
    }

    public void dismissDeleteAccountOnClick(View v) {
        Intent dismissIntent = new Intent(this, DoctorMainActivity.class);
        dismissIntent.putExtra("DOC_USERNAME_INTENT", docUsername);
        startActivity(dismissIntent);
    }

    public void deleteAccountOnClick(View v) {
        Intent deleteIntent = new Intent(this, MainActivity.class);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ValueEventListener listener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot docUpcomingAppointments = snapshot.child("doctors").child(docUsername).child("upcoming_appointments");
                for (DataSnapshot doctorUpcomingAppointment : docUpcomingAppointments.getChildren()) {

                    String patientUsername = doctorUpcomingAppointment.child("patient").child("username").getValue().toString();

                    DataSnapshot patientUpcomingAppointments = snapshot.child("patients").child(patientUsername).child("upcoming_appointments");
                    for (DataSnapshot patientUpcomingAppointment : patientUpcomingAppointments.getChildren()) {
                        String doctor = patientUpcomingAppointment.child("doctor").child("username").getValue().toString();
                        if (doctor.equals(docUsername)) {
                            patientUpcomingAppointment.getRef().removeValue();
                        }
                    }
                }
                DataSnapshot docUser = snapshot.child("doctors").child(docUsername);
                docUser.getRef().removeValue();
                Toast.makeText(DeleteDoctorActivity.this,"Account has been deleted.",Toast.LENGTH_LONG).show();
                startActivity(deleteIntent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addValueEventListener(listener);
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