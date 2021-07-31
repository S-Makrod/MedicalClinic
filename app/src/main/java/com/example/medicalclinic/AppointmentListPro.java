package com.example.medicalclinic;

import java.util.List;

public abstract class AppointmentListPro {
    List<Appointment> upcoming;

    abstract void add(Appointment a);
    abstract void list();
    abstract void delete(Appointment a);
    abstract List<Appointment> getUpcoming();
}
