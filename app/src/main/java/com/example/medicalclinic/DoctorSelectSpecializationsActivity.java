package com.example.medicalclinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorSelectSpecializationsActivity extends AppCompatActivity {
    private List<String> specializations;
    private String chosenSpecializations;
    private StringBuffer displaySelectedSpecializations;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12, cb13, cb14,
            cb15, cb16, cb17, cb18, cb19, cb20, cb21, cb22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_select_specializations);
        setTitle("Medical Clinic");

        specializations = new ArrayList<String>();
        chosenSpecializations = "";

        displaySelectedSpecializations = new StringBuffer();

        initializeCheckboxes();
    }

    public void selectSpecializationOnClick(View v) {
        displaySelectedSpecializations.append("Specializations selected:\n");
        addSelectedSpecialization();
        Toast.makeText(DoctorSelectSpecializationsActivity.this, displaySelectedSpecializations.toString(), Toast.LENGTH_LONG).show();

        for (String s : specializations) {
            chosenSpecializations += s + ";";
        }

        if (chosenSpecializations.isEmpty()) {
            Toast.makeText(DoctorSelectSpecializationsActivity.this,"Please select your specialization(s)!",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, DoctorSignUpActivity.class);
            intent.putExtra("SPECIALIZATIONS_INTENT", chosenSpecializations);
            startActivity(intent);
        }
    }

    private void initializeCheckboxes() {
        cb1 = findViewById(R.id.allergy);
        cb2 = findViewById(R.id.anesthesiology);
        cb3 = findViewById(R.id.cardiology);
        cb4 = findViewById(R.id.emergencyMedicine);
        cb5 = findViewById(R.id.endocrinology);
        cb6 = findViewById(R.id.dermatology);
        cb7 = findViewById(R.id.familyMedicine);
        cb8 = findViewById(R.id.gastroenterology);
        cb9 = findViewById(R.id.geriatricMedicine);
        cb10 = findViewById(R.id.hematology);
        cb11 = findViewById(R.id.neurology);
        cb12 = findViewById(R.id.obstetrics);
        cb13 = findViewById(R.id.ophthalmology);
        cb14 = findViewById(R.id.orthopedic);
        cb15 = findViewById(R.id.pathology);
        cb16 = findViewById(R.id.pediatrics);
        cb17 = findViewById(R.id.physicalMedicine);
        cb18 = findViewById(R.id.psychiatry);
        cb19 = findViewById(R.id.radiationOncology);
        cb20 = findViewById(R.id.respirology);
        cb21 = findViewById(R.id.rheumatology);
        cb22 = findViewById(R.id.urology);
    }

    private void addSelectedSpecialization() {
        if (cb1.isChecked()) {
            displaySelectedSpecializations.append(cb1.getText().toString()).append("\n");
            specializations.add(cb1.getText().toString());
        }
        if (cb2.isChecked()) {
            displaySelectedSpecializations.append(cb2.getText().toString()).append("\n");
            specializations.add(cb2.getText().toString());
        }
        if (cb3.isChecked()) {
            displaySelectedSpecializations.append(cb3.getText().toString()).append("\n");
            specializations.add(cb3.getText().toString());
        }
        if (cb4.isChecked()) {
            displaySelectedSpecializations.append(cb4.getText().toString()).append("\n");
            specializations.add(cb4.getText().toString());
        }
        if (cb5.isChecked()) {
            displaySelectedSpecializations.append(cb5.getText().toString()).append("\n");
            specializations.add(cb5.getText().toString());
        }
        if (cb6.isChecked()) {
            displaySelectedSpecializations.append(cb6.getText().toString()).append("\n");
            specializations.add(cb6.getText().toString());
        }
        if (cb7.isChecked()) {
            displaySelectedSpecializations.append(cb7.getText().toString()).append("\n");
            specializations.add(cb7.getText().toString());
        }
        if (cb8.isChecked()) {
            displaySelectedSpecializations.append(cb8.getText().toString()).append("\n");
            specializations.add(cb8.getText().toString());
        }
        if (cb9.isChecked()) {
            displaySelectedSpecializations.append(cb9.getText().toString()).append("\n");
            specializations.add(cb9.getText().toString());
        }
        if (cb10.isChecked()) {
            displaySelectedSpecializations.append(cb10.getText().toString()).append("\n");
            specializations.add(cb10.getText().toString());
        }
        if (cb11.isChecked()) {
            displaySelectedSpecializations.append(cb11.getText().toString()).append("\n");
            specializations.add(cb11.getText().toString());
        }
        if (cb12.isChecked()) {
            displaySelectedSpecializations.append(cb12.getText().toString()).append("\n");
            specializations.add(cb12.getText().toString());
        }
        if (cb13.isChecked()) {
            displaySelectedSpecializations.append(cb13.getText().toString()).append("\n");
            specializations.add(cb13.getText().toString());
        }
        if (cb14.isChecked()) {
            displaySelectedSpecializations.append(cb14.getText().toString()).append("\n");
            specializations.add(cb14.getText().toString());
        }
        if (cb15.isChecked()) {
            displaySelectedSpecializations.append(cb15.getText().toString()).append("\n");
            specializations.add(cb15.getText().toString());
        }
        if (cb16.isChecked()) {
            displaySelectedSpecializations.append(cb16.getText().toString()).append("\n");
            specializations.add(cb16.getText().toString());
        }
        if (cb17.isChecked()) {
            displaySelectedSpecializations.append(cb17.getText().toString()).append("\n");
            specializations.add(cb17.getText().toString());
        }
        if (cb18.isChecked()) {
            displaySelectedSpecializations.append(cb18.getText().toString()).append("\n");
            specializations.add(cb18.getText().toString());
        }
        if (cb19.isChecked()) {
            displaySelectedSpecializations.append(cb19.getText().toString()).append("\n");
            specializations.add(cb19.getText().toString());
        }
        if (cb20.isChecked()) {
            displaySelectedSpecializations.append(cb20.getText().toString()).append("\n");
            specializations.add(cb20.getText().toString());
        }
        if (cb21.isChecked()) {
            displaySelectedSpecializations.append(cb21.getText().toString()).append("\n");
            specializations.add(cb21.getText().toString());
        }
        if (cb22.isChecked()) {
            displaySelectedSpecializations.append(cb22.getText().toString()).append("\n");
            specializations.add(cb22.getText().toString());
        }
    }
}