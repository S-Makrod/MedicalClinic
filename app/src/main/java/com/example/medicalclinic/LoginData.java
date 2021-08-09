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

    private boolean usernameExists;
    private boolean correctPassword;
    private boolean dataLoadedSuccessfully;

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
                    dataLoadedSuccessfully = task.isSuccessful();
                    if (!dataLoadedSuccessfully) {
                        String x = presenter.getMessage(5);
                        view.loginFailure(x);
                    } else {
                        usernameExists = task.getResult().child(getUsername()).exists();
                        if(usernameExists){
                            String password = task.getResult().child(getUsername()).child("password").getValue().toString();
                            correctPassword = matchingPassword(password);
                            if(correctPassword){
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
    public void executeLoginAttempt(String db, Contract.View view) {
        if(getUsername().isEmpty() || getPassword().isEmpty()){
            String x = presenter.getMessage(4);
            view.loginFailure(x);
        } else {
            if (!dataLoadedSuccessfully) {
                String x = presenter.getMessage(5);
                view.loginFailure(x);
            } else {
                if(usernameExists){
                    if(correctPassword){
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
    }

    public boolean matchingPassword(String actual_password) {
        return actual_password.equals(getPassword());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public boolean isUsernameExists() {
        return usernameExists;
    }

    public boolean isCorrectPassword() {
        return correctPassword;
    }

    public boolean isDataLoadedSuccessfully() {
        return dataLoadedSuccessfully;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsernameExists(boolean usernameExists) {
        this.usernameExists = usernameExists;
    }

    public void setCorrectPassword(boolean correctPassword) {
        this.correctPassword = correctPassword;
    }

    public void setDataLoadedSuccessfully(boolean dataLoadedSuccessfully) {
        this.dataLoadedSuccessfully = dataLoadedSuccessfully;
    }

    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

}
