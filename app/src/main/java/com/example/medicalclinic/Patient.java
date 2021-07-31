package com.example.medicalclinic;

import java.util.List;
import java.util.ArrayList;

public class Patient extends PatientUser{
    String name, password, gender, username;
    String date_of_birth;		//Can be either date like Java.Date() or string
    AppointmentListPro upcoming_appointments; //List of dates like Java.Date()
    AppointmentList previous_appointments; //List of dates like Java.Date()
    List<AppointmentInfo> doctors;				//List of doctor id and name no need to have other info

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

    @Override
    public void book(String doc_username) {
        //JUST ADD TO UPCOMING
    }

    @Override
    public void update_appointments() {
        /*
        SOMETHING LIKE THE FOLLOWING
        List<Appointment> a = upcoming_appointments.getUpcoming();

        for(Appointment x: a) {
            DO STUFF
            if(...){
                upcoming_appointments.delete(x);
                previous_appointments.add(x);
            }
            DO MORE STUFF
        }
        */
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
