package com.example.medicalclinic;

import java.util.List;

public abstract class PatientUser extends User{
    String date_of_birth;		        //Can be either date like Java.Date() or string
    List<Appointment> upcoming_appointments; //List of dates like Java.Date()
    List<Appointment> previous_appointments; //List of dates like Java.Date()
    List<AppointmentInfo> doctors;				//List of doctor id and name no need to have other info

    public abstract void book(int DocID); //Book appointments

    public abstract void list(); //List upcoming appointments UNSURE OF IMPLEMENTATION

    public abstract void update_appointments();

    public abstract String getDate_of_birth();

    public abstract void setDate_of_birth(String date_of_birth);

    public abstract List<Appointment> getUpcoming_appointments();

    public abstract void setUpcoming_appointments(List<Appointment> upcoming_appointments);

    public abstract List<Appointment> getPrevious_appointments();

    public abstract void setPrevious_appointments(List<Appointment> previous_appointments);

    public abstract List<AppointmentInfo> getDoctors();

    public abstract void setDoctors(List<AppointmentInfo> doctors);
}
