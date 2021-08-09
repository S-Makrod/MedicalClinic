package com.example.medicalclinic;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
            Database.ref.child(db).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        String x = presenter.getMessage(5);
                        view.loginFailure(x);
                    } else {
                        if(task.getResult().child(getUsername()).exists()){
                            String password = task.getResult().child(getUsername()).child("password").getValue().toString();
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
                }
            });
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
