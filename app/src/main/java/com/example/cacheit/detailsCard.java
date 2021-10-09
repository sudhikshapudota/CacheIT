package com.example.cacheit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class detailsCard extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_WRITE_PERMISSION = 786;

    EditText cc1, name1, cc2, name2, cc3, name3, cc4, name4;
    Button submit_detailsCard, next_card_name;
    int value = 0;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //submitDetails();
        } else {
            Toast.makeText(getApplicationContext(), "cacheit needs SMS Permission to continue.", Toast.LENGTH_SHORT).show();
            requestPermission();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_card);
        cc1 = findViewById(R.id.cc1);
        int cc1_length = cc1.toString().length();
        name1 = findViewById(R.id.name1);
        cc2 = findViewById(R.id.cc2);
        int cc2_length = cc2.toString().length();
        name2 = findViewById(R.id.name2);
        cc3 = findViewById(R.id.cc3);
        int cc3_length = cc3.toString().length();
        name3 = findViewById(R.id.name3);
        cc4 = findViewById(R.id.cc4);
        int cc4_length = cc4.toString().length();
        name4 = findViewById(R.id.name4);
        submit_detailsCard = findViewById(R.id.submit);
        submit_detailsCard.setOnClickListener(this);
        next_card_name = findViewById(R.id.next_card_name);
        next_card_name.setOnClickListener(this);
        requestPermission();
        if (cc1_length == 0) {

        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, REQUEST_WRITE_PERMISSION);
        } /*else {
            submitDetails();
        }*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_card_name:
                if (value == 0) {
                    cc2.setVisibility(View.VISIBLE);
                    name2.setVisibility(View.VISIBLE);
                    value = 1;
                } else if (value == 1) {
                    cc3.setVisibility(View.VISIBLE);
                    name3.setVisibility(View.VISIBLE);
                    value = 2;
                } else if (value == 2) {
                    cc4.setVisibility(View.VISIBLE);
                    name4.setVisibility(View.VISIBLE);
                    next_card_name.setVisibility(View.GONE);
                }
                break;
            case R.id.submit:
                SubmitDetails();
                break;
        }
    }

    public void SubmitDetails() {
        if (ValidateField()) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("UserAccount", 0);
            SharedPreferences.Editor editor = preferences.edit();
            if (value == 0) {
                editor.putString("cc1", String.valueOf(cc1.getText().toString()));
                editor.putString("name1", name1.getText().toString());
            }
            if (value == 1) {
                editor.putString("cc1", String.valueOf(cc1.getText().toString()));
                editor.putString("name1", name1.getText().toString());
                editor.putString("cc2", String.valueOf(cc2.getText().toString()));
                editor.putString("name2", name2.getText().toString());
            }
            if (value == 2) {
                editor.putString("cc1", String.valueOf(cc1.getText().toString()));
                editor.putString("name1", name1.getText().toString());
                editor.putString("cc2", String.valueOf(cc2.getText().toString()));
                editor.putString("name2", name2.getText().toString());
                editor.putString("cc3", String.valueOf(cc3.getText().toString()));
                editor.putString("name3", name3.getText().toString());
            }
            if (value == 3) {
                editor.putString("cc1", String.valueOf(cc1.getText().toString()));
                editor.putString("name1", name1.getText().toString());
                editor.putString("cc2", String.valueOf(cc2.getText().toString()));
                editor.putString("name2", name2.getText().toString());
                editor.putString("cc3", String.valueOf(cc3.getText().toString()));
                editor.putString("name3", name3.getText().toString());
                editor.putString("cc4", String.valueOf(cc4.getText().toString()));
                editor.putString("name4", name4.getText().toString());
            }
            editor.commit();
            Toast.makeText(this, "Data has been successfully saved", Toast.LENGTH_SHORT).show();

            SharedPreferences prfs = getSharedPreferences("UserAccount", Context.MODE_PRIVATE);
            String cc1 = prfs.getString("cc1", null);
            String name1 = prfs.getString("name1", null);
            String cc2 = prfs.getString("cc2", null);
            String name2 = prfs.getString("name2", null);
            String cc3 = prfs.getString("cc3", null);
            String name3 = prfs.getString("name3", null);
            String cc4 = prfs.getString("cc4", null);
            String name4 = prfs.getString("name4", null);
            Toast.makeText(getApplicationContext(), "cc1: " + cc1 + "name1" + name1 + "cc2: " + cc2 + "name2" + name2 + "cc3: " + cc3 + "name3" + name3 + "cc4: " + cc4 + "name4" + name4, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(detailsCard.this, Dashboard.class);
            startActivity(i);
        }
    }

    private boolean ValidateField() {
        if (value == 0) {
            if (cc1.getText().toString().length() == 0 || name1.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (value < 2) {
            if (cc1.getText().toString().length() == 0 || name1.getText().toString().length() == 0 || cc2.getText().toString().length() == 0 || name2.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (value < 3) {
            if (cc1.getText().toString().length() == 0 || name1.getText().toString().length() == 0 || cc2.getText().toString().length() == 0 || name2.getText().toString().length() == 0 || cc3.getText().toString().length() == 0 || name3.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (value == 3) {
            if (cc1.getText().toString().length() == 0 || name1.getText().toString().length() == 0 || cc2.getText().toString().length() == 0 || name2.getText().toString().length() == 0 || cc3.getText().toString().length() == 0 || name3.getText().toString().length() == 0 || cc4.getText().toString().length() == 0 || name4.getText().toString().length() == 0) {
                Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
