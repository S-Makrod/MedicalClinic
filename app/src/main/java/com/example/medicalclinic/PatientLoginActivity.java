package com.example.medicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//View Class of PatientLogin
public class PatientLoginActivity extends AppCompatActivity implements Contract.View {
    public static final String USERNAME_INTENT = "username";
    public TextView patientUserName,patientPassword;

    private PatientLoginPresenter loginPresenter;

    private Contract.Model model;
    private Contract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        setTitle("Medical Clinic");

        patientUserName=(TextView)findViewById(R.id.patientUserNameTextBox);
        patientPassword=(TextView)findViewById(R.id.patientPasswordTextBox);



    }





    public void patientSignUpButtonOnClick(android.view.View view) {
        Intent intent=new Intent(this,PatientSignUpActivity.class);
        startActivity(intent);
    }



    @Override
    public void LoginButtonOnClick(android.view.View view) {
        presenter = new LoginPresenter();
        model = new LoginData(patientPassword.getText().toString(), patientUserName.getText().toString(), presenter);
        model.executeLogin("patients", this);

    }

    @Override
    public void loginSuccess(String message) {
        Toast.makeText(PatientLoginActivity.this, message, Toast.LENGTH_LONG).show();
        Intent intent=new Intent(PatientLoginActivity.this,PatientMainActivity.class);
        intent.putExtra(USERNAME_INTENT, model.getUsername());
        startActivity(intent);
    }

    @Override
    public void loginFailure(String message) {
        Toast.makeText(PatientLoginActivity.this, message, Toast.LENGTH_LONG).show();
    }


}