package com.example.medicalclinic;

import java.util.List;
import java.util.ArrayList;

public class Patient extends PatientUser{
    String name, password, gender, username;
    String date_of_birth;		//Can be either date like Java.Date() or string
    List<Appointment> upcoming_appointments; //List of dates like Java.Date()
    List<Appointment> previous_appointments; //List of dates like Java.Date()
    List<AppointmentInfo> doctors;				//List of doctor id and name no need to have other info

    public Patient(String name, String password, String gender, String username, String date_of_birth){
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.username = username;
        this.date_of_birth = date_of_birth;

        upcoming_appointments = new ArrayList<>();
        previous_appointments = new ArrayList<>();
        doctors = new ArrayList<>();
    }

    @Override
    public void book(int DocID) {

    }

    @Override
    public void list() {

    }

    @Override
    public void update_appointments() {

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
    public List<Appointment> getUpcoming_appointments() {
        return upcoming_appointments;
    }

    @Override
    public void setUpcoming_appointments(List<Appointment> upcoming_appointments) {
        this.upcoming_appointments = upcoming_appointments;
    }

    @Override
    public List<Appointment> getPrevious_appointments() {
        return previous_appointments;
    }

    @Override
    public void setPrevious_appointments(List<Appointment> previous_appointments) {
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
