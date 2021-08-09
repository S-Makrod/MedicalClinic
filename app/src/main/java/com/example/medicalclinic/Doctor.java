package com.example.medicalclinic;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Doctor extends DoctorUser{
    String name, password, gender, username;
    List<Appointment> upcoming_appointments;	//List of dates like Java.Date()
    List<String> specializations;		//List of string
    List<Appointment> weekly_availabilities; //List of dates like Java.Date()
    List<AppointmentInfo> patients;				//List of patient id and name no need to have other info

    public Doctor() {

    }

    public Doctor(String name, String password, String gender, String username, List<String> specializations){
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.username = username;
        this.specializations = specializations;

        patients = new ArrayList<>();
    }

    public void book(String doc_username, Date d, String patient_username) throws ParseException {

        DoctorAppointmentSlot newAppointment = new DoctorAppointmentSlot(patient_username, d);
        Database.ref.child("doctors").child(doc_username).child("upcoming_appointments").child(newAppointment.getDate()).setValue(newAppointment);

    }

    @Override
    public void update_appointments(String doctor_username) throws ParseException{

        Date current = new Date(System.currentTimeMillis());
        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.child("doctors").child(doctor_username).child("upcoming_appointments").getChildren()) {
                    DoctorAppointmentSlot post = child.getValue(DoctorAppointmentSlot.class);

                    try {
                        if (current.after(Appointment.dateParser.parse(post.getDate()))) {

                            String patientUsername = post.patient.getUsername();
                            String pat = dataSnapshot.child("patients").child(patientUsername).child("name").getValue ().toString();

                            Database.ref.child("doctors").child(doctor_username).child("patients").child(post.patient.getUsername()).setValue(pat);
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
        Database.ref.addValueEventListener(postListener);

    }

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
    public List<Appointment> getUpcoming_appointments() {
        return upcoming_appointments;
    }

    public void setUpcoming_appointments(List<Appointment> upcoming_appointments) {
        this.upcoming_appointments = upcoming_appointments;
    }

    @Override
    public List<String> getSpecializations() {
        return specializations;
    }

    @Override
    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    @Override
    public List<Appointment> getWeekly_availabilities() {
        return weekly_availabilities;
    }

    @Override
    public void setWeekly_availabilities(List<Appointment> weekly_availabilities) {
        this.weekly_availabilities = weekly_availabilities;
    }

    @Override
    public List<AppointmentInfo> getPatients() {
        return patients;
    }

    @Override
    public void setPatients(List<AppointmentInfo> patients) {
        this.patients = patients;
    }
}
