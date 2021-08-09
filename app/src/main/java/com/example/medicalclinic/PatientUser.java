package com.example.medicalclinic;

import java.text.ParseException;
import java.util.List;
import java.util.Date;

public abstract class PatientUser extends User {
    String name, password, gender, username;
    String date_of_birth;                //Can be either date like Java.Date() or string
    List<AppointmentInfo> doctors;                //List of doctor id and name no need to have other info

    public abstract void book(String doc_username, Date d, String patient_username) throws ParseException; //Book appointments

    public abstract void update_appointments(String patient_username) throws ParseException;

    public abstract String getDate_of_birth();

    public abstract void setDate_of_birth(String date_of_birth);

    public abstract List<AppointmentInfo> getDoctors();

    public abstract void setDoctors(List<AppointmentInfo> doctors);
}

