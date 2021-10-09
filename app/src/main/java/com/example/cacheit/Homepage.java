package com.example.cacheit;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cacheit.DatabaseHelper;
import com.example.cacheit.R;
import com.example.cacheit.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.example.cacheit.MainActivity.departmentCardList;
import static com.example.cacheit.MainActivity.educationCardList;
import static com.example.cacheit.MainActivity.fuelCardList;
import static com.example.cacheit.MainActivity.leisureCardList;
import static com.example.cacheit.MainActivity.othersCardList;
import static com.example.cacheit.MainActivity.utilitiesCardList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Homepage extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_WRITE_PERMISSION = 786;
    //public static List<FoodCard> foodList = new ArrayList<>();
    //public static List<EntertainmentCard> entertainmentCardList = new ArrayList<>();
    //public static List<ClothesCard> clothesList = new ArrayList<>();
    //public static List<RecentCard> recentCardList = new ArrayList<>();
    EditText etAmount, etCard, edtPlace;
    RadioGroup types;
    Button submit, nav_button;
    DatabaseHelper db;
    String type;
    int id;
    boolean update = false;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_homepage);
        db = new DatabaseHelper(this);
        nav_button = findViewById(R.id.nav_button);
        etAmount = findViewById(R.id.enteramount);
        edtPlace = findViewById(R.id.edtPlace);
        etCard = findViewById(R.id.entercard);
        types = findViewById(R.id.radio);
        submit = findViewById(R.id.submit_button);
        Intent intent = getIntent();
        if (intent != null) {
            update = !intent.getStringExtra("New").contains("true");
            if (update) {
                id = intent.getIntExtra("id", -1);
                etCard.setText(intent.getStringExtra("card"));
                etAmount.setText(intent.getStringExtra("amount"));
                edtPlace.setText(intent.getStringExtra("place"));
            }
        }
/*
        etDate = v.findViewById(R.id.enterdate);
*/
        submit.setOnClickListener(this);
        nav_button.setOnClickListener(this);
        types.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getCheckedRadioButtonId() == R.id.departmentstores) {
                    type = "Department Stores";
                }
                else if (group.getCheckedRadioButtonId() == R.id.utilities) {
                    type = "Utilities and Bills";
                }
                else if (group.getCheckedRadioButtonId() == R.id.fuel) {
                    type = "Fuel";
                }
                else if (group.getCheckedRadioButtonId() == R.id.education) {
                    type = "Education";
                }
                else if (group.getCheckedRadioButtonId() == R.id.leisure) {
                    type = "Leisure and Entertainment";
                }
                else if (group.getCheckedRadioButtonId() == R.id.others) {
                    type = "Others";
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                submitDetails();
                break;
            case R.id.nav_button:
                if (getTabsDetails()) {
                    Intent i = new Intent(Homepage.this, tabs.class);
                    startActivity(i);
                }
                break;
        }

    }

    private void submitDetails() {
        if (ValidateField()) {
            User user = new User();
            user.setAmount(etAmount.getText().toString());
            user.setPlace(edtPlace.getText().toString());
            user.setCard(etCard.getText().toString());
            user.setType(type);
            if (!update)
                db.addUser(user);
            else {
                User user1 = new User();
                user1.setPlace(edtPlace.getText().toString());
                user1.setType(type);
                user1.setId(id);
                boolean status = db.getRowFromIndex(user1);
                Toast.makeText(getApplicationContext(), "UpdateStatus" + status, Toast.LENGTH_SHORT).show();
                //foodList.clear();
                //recentCardList.clear();

            }
            etAmount.setText("");
            types.clearCheck();
            etCard.setText("");
            edtPlace.setText("");
            if (getTabsDetails()) {
                Toast.makeText(this, "Data has been succssfully saved", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Homepage.this, RecentExpenses.class);
                startActivity(i);
            }
        }
    }

    private boolean ValidateField() {
        if (etAmount.getText().toString().length() == 0 || etCard.getText().toString().length() == 0 || type.length() == 0) {
            Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean getTabsDetails() {
        dialog = new ProgressDialog(this);
        dialog.show();
        MainActivity.recentCardList.clear();
        MainActivity.recentCardList = db.getRecent();
        Collections.reverse(othersCardList);//departmentCardList.clear();
        departmentCardList = db.getDepartmentStores();
        Collections.reverse(departmentCardList);
        //utilitiesCardList.clear();
        utilitiesCardList = db.getUtilities();
        Collections.reverse(utilitiesCardList);
        //fuelCardList.clear();
        fuelCardList = db.getFuel();
        Collections.reverse(fuelCardList);
        //educationCardList.clear();
        educationCardList = db.getEducation();
        Collections.reverse(educationCardList);
        //leisureCardList.clear();
        leisureCardList = db.getLeisure();
        Collections.reverse(leisureCardList);
        //othersCardList.clear();
        othersCardList = db.getOthers();
        Collections.reverse(othersCardList);
        Log.d("departmentListHome:", String.valueOf(departmentCardList.size()));
        Log.d("utilitiesListHome:",String.valueOf(utilitiesCardList.size()));
        Log.d("fuelListHome:",String.valueOf(fuelCardList.size()));
        Log.d("educationListHome:",String.valueOf(educationCardList.size()));
        Log.d("leisureListHome",String.valueOf(leisureCardList.size()));
        Log.d("recentListHome:", String.valueOf(MainActivity.recentCardList));
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        return true;
        //Toast.makeText(getActivity(), "foodlist size " + foodList.size(), Toast.LENGTH_SHORT).show();
        // Cursor food = db.getType("Food");
        // Log.d("FOOD LIST", food.toString());
    }
}
