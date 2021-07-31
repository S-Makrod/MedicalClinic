package com.example.medicalclinic;

import java.util.List;

public abstract class PatientUser extends User{
    String date_of_birth;		        //Can be either date like Java.Date() or string
    AppointmentListPro upcoming_appointments; //List of dates like Java.Date()
    AppointmentList previous_appointments; //List of dates like Java.Date()
    List<AppointmentInfo> doctors;				//List of doctor id and name no need to have other info

    public abstract void book(String doc_username); //Book appointments

    public abstract void update_appointments();

    public abstract String getDate_of_birth();

    public abstract void setDate_of_birth(String date_of_birth);

    public abstract AppointmentListPro getUpcoming_appointments();

    public abstract void setUpcoming_appointments(AppointmentListPro upcoming_appointments);

    public abstract AppointmentList getPrevious_appointments();

    public abstract void setPrevious_appointments(AppointmentList previous_appointments);

    public abstract List<AppointmentInfo> getDoctors();

    public abstract void setDoctors(List<AppointmentInfo> doctors);
}
