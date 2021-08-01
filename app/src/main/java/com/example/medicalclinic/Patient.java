package com.example.medicalclinic;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends PatientUser{
    String name, password, gender, username;
    String date_of_birth;		//Can be either date like Java.Date() or string
    AppointmentListPro upcoming_appointments; //List of dates like Java.Date()
    AppointmentList previous_appointments; //List of dates like Java.Date()
    List<AppointmentInfo> doctors;				//List of doctor id and name no need to have other info

    public Patient() {

    }

    public Patient(String name, String password, String gender, String username, String date_of_birth){
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.username = username;
        this.date_of_birth = date_of_birth;

        upcoming_appointments = new UpcomingAppointments();
        previous_appointments = new PreviousAppointments();
        doctors = new ArrayList<>();
    }

    /*@Override
    public void book(String doc_username, Date d) throws ParseException {

        AppointmentInfo newAppointmentInfo = new AppointmentMeeting(doc_username);
        AppointmentSlot newAppointment = new AppointmentSlot(doc_username, d);

        boolean shouldAdd = true;
        for (AppointmentInfo info: doctors) {

            if (info.getUsername() == doc_username) {
                shouldAdd = false;
            }
        }

        if (shouldAdd) {
            doctors.add(newAppointmentInfo);
        }

        upcoming_appointments.add(newAppointment);
        this.update_appointments();
    }*/

    @Override
    public void book(String doc_username, Date d, String patient_username) throws ParseException {

        AppointmentInfo newAppointmentInfo = new AppointmentMeeting(doc_username);
        AppointmentSlot newAppointment = new AppointmentSlot(doc_username, d);


        Date current = new Date(System.currentTimeMillis());
        if (current.after(d)) {
            Database.ref.child("patients").child(patient_username).child("previous_appointments").child(newAppointment.getDate()).setValue(newAppointment);
            Database.ref.child("patients").child(patient_username).child("doctors").child(newAppointmentInfo.getUsername()).setValue(newAppointmentInfo.getUsername());
        }
        else {
            Database.ref.child("patients").child(patient_username).child("upcoming_appointments").child(newAppointment.getDate()).setValue(newAppointment);
        }

    }

    @Override
    public void update_appointments(String patient_username) throws ParseException{

        Date current = new Date(System.currentTimeMillis());
        DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference("patients/" + patient_username + "/upcoming_appointments");
        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren()) {
                    AppointmentSlot post = child.getValue(AppointmentSlot.class);

                    try {
                        if (current.after(Appointment.dateParser.parse(post.getDate()))) {
                            Database.ref.child("patients").child(patient_username).child("previous_appointments").child(post.getDate()).setValue(post);
                            Database.ref.child("patients").child(patient_username).child("doctors").child(post.a.getUsername()).setValue(post.a.getUsername());
                            child.getRef().removeValue();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mPostReference.addValueEventListener(postListener);

    }

    /*@Override
    public void update_appointments() throws ParseException{

        Date current = new Date(System.currentTimeMillis());
        List<Appointment> upcoming_appointments_copy = upcoming_appointments.getUpcoming();

        for (Appointment appointment: upcoming_appointments_copy) {

            if (current.after(Appointment.dateParser.parse(appointment.getDate()))) {

                previous_appointments.add(appointment);
                upcoming_appointments.delete(appointment);
            }
        }

        Database.ref.child("patients").child(this.getUsername()).setValue(this);
    }*/

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getDate_of_birth() {
        return date_of_birth;
    }

    @Override
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public AppointmentListPro getUpcoming_appointments() {
        return upcoming_appointments;
    }

    @Override
    public void setUpcoming_appointments(AppointmentListPro upcoming_appointments) {
        this.upcoming_appointments = upcoming_appointments;
    }

    @Override
    public AppointmentList getPrevious_appointments() {
        return previous_appointments;
    }

    @Override
    public void setPrevious_appointments(AppointmentList previous_appointments) {
        this.previous_appointments = previous_appointments;
    }

    @Override
    public List<AppointmentInfo> getDoctors() {
        return doctors;
    }

    @Override
    public void setDoctors(List<AppointmentInfo> doctors) {
        this.doctors = doctors;
    }
}
