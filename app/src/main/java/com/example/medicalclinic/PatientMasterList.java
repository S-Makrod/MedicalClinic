package com.example.medicalclinic;

import java.util.List;
import java.util.ArrayList;

public class PatientMasterList implements MasterList<PatientUser>{
    List<PatientUser> p;

    @Override
    public void add(PatientUser u) {

    }

    @Override
    public PatientUser get(String username) {

        /* FOR DENISE
        Instead of returning null at the end just replace it with this code
        LoadUser l = new PatientLoader();
        return (PatientUser)l.get(username);

        Similar stuff for DoctorUser get() in DoctorMasterList
        */

        return null;
    }
}
