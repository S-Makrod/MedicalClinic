package com.example.medicalclinic;

public interface MasterList<G>{
    void add(G u);
    G get(String username);
}
