package com.example.medicalclinic;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginData implements Contract.Model{
    private String username;
    private String password;
    private Contract.Presenter presenter;

    public LoginData(String password, String username, Contract.Presenter presenter){
        this.username = username;
        this.password = password;
        this.presenter = presenter;
    }

    @Override
    public void executeLogin(String db, Contract.View view) {

        if(getUsername().isEmpty() || getPassword().isEmpty()){
            String x = presenter.getMessage(4);
            view.loginFailure(x);
        } else {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot = dataSnapshot.child(db);
                if(snapshot.child(getUsername()).exists()){
                    String password = snapshot.child(getUsername()).child("password").getValue().toString();
                    if(password.equals(getPassword())){
                        String x = presenter.getMessage(1);
                        view.loginSuccess(x);
                    } else {
                        String x = presenter.getMessage(3);
                        view.loginFailure(x);
                    }
                } else{
                    String x = presenter.getMessage(2);
                    view.loginFailure(x);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        }; Database.ref.addValueEventListener(listener);
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
