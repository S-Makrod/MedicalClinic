package com.example.medicalclinic;

public class PatientDB implements Database<PatientUser>{
    final String child = "patients";
    @Override
    public boolean search(String username) {
        return false;
    }

    @Override
    public void add(PatientUser u) {

    }
}
