package com.example.medicalclinic;

import java.util.List;

abstract class DoctorUser extends User {
    List<Appointment> upcoming_appointments;	//List of dates like Java.Date()
    List<String> specializations;		//List of string
    List<Appointment> weekly_availabilities; //List of dates like Java.Date()
    List<AppointmentInfo> patients;				//List of patient id and name no need to have other info

    public abstract void list(); //List upcoming appointments UNSURE OF IMPLEMENTATION

    public abstract void update_appointments();

    public abstract List<Appointment> getUpcoming_appointments();

    public abstract void setUpcoming_appointments(List<String> upcoming_appointments);

    public abstract List<String> getSpecializations();

    public abstract void setSpecializations(List<String> specializations);

    public abstract List<Appointment> getWeekly_availabilities();

    public abstract void setWeekly_availabilities(List<Appointment> weekly_availabilities);

    public abstract List<AppointmentInfo> getPatients();

    public abstract void setPatients(List<AppointmentInfo> patients);
}