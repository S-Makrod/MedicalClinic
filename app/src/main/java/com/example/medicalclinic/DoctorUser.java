package com.example.medicalclinic;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

abstract class DoctorUser extends User {
    List<Appointment> upcoming_appointments;	//List of dates like Java.Date()
    List<String> specializations;		//List of string
    List<Appointment> weekly_availabilities; //List of dates like Java.Date()
    List<AppointmentInfo> patients;				//List of patient id and name no need to have other info

    public abstract void book(String doc_username, Date d, String patient_username) throws ParseException; //Book appointments

    public abstract void update_appointments(String doctor_username) throws ParseException;

    public abstract List<Appointment> getUpcoming_appointments();

    public abstract void setUpcoming_appointments(List<Appointment> upcoming_appointments);

    public abstract List<String> getSpecializations();

    public abstract void setSpecializations(List<String> specializations);

    public abstract List<Appointment> getWeekly_availabilities();

    public abstract void setWeekly_availabilities(List<Appointment> weekly_availabilities);

    public abstract List<AppointmentInfo> getPatients();

    public abstract void setPatients(List<AppointmentInfo> patients);
}