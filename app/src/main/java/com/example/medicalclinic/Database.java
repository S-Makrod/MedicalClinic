package com.example.medicalclinic;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public interface Database<G>{
    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public boolean search(String username);
    public void add(G g);
}
