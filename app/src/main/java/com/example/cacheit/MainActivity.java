package com.example.cacheit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer t;
    public static List<DepartmentCard> departmentCardList = new ArrayList<>();
    public static List<UtilitiesCard> utilitiesCardList = new ArrayList<>();
    public static List<FuelCard> fuelCardList = new ArrayList<>();
    public static List<EducationCard> educationCardList = new ArrayList<>();
    public static List<LeisureCard> leisureCardList = new ArrayList<>();
    public static List<OthersCard> othersCardList = new ArrayList<>();
    public static List<RecentCard> recentCardList = new ArrayList<>();
    public static List<cc1_Card> cc1CardList = new ArrayList<>();
    public static List<cc2_card> cc2CardList = new ArrayList<>();
    public static List<cc3_card> cc3CardList = new ArrayList<>();
    public static List<cc4_card> cc4CardList = new ArrayList<>();
    String cc1, name1;
    DatabaseHelper db;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                SharedPreferences prfs = getSharedPreferences("UserAccount", Context.MODE_PRIVATE);
                if (prfs.contains("cc1"))
                    cc1 = prfs.getString("cc1", cc1);
                else cc1 = null;
                name1 = prfs.getString("name1", name1);
                /*String cc1 = prfs.getString("cc1",null);
                String name1 = prfs.getString("name1", null);*/
                if (cc1 == null) {
                    Intent i = new Intent(MainActivity.this, detailsCard.class);
                    startActivity(i);
                    /*if (getTabsDetails()) {
                        Intent i = new Intent(MainActivity.this, detailsCard.class);
                        startActivity(i);
                    }*/
                } else if (cc1 != null && cc1.length() > 0) {
                    Intent i = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(i);
                }
            }
        }, 2000);
        getTabsDetails();
    }

    private boolean getTabsDetails() {
        dialog = new ProgressDialog(this);
        dialog.show();
        MainActivity.recentCardList.clear();
        MainActivity.recentCardList = db.getRecent();
        Collections.reverse(othersCardList);//departmentCardList.clear();
        departmentCardList = db.getDepartmentStores();
        Collections.reverse(departmentCardList);
        utilitiesCardList.clear();
        utilitiesCardList = db.getUtilities();
        Collections.reverse(utilitiesCardList);
        fuelCardList.clear();
        fuelCardList = db.getFuel();
        Collections.reverse(fuelCardList);
        educationCardList.clear();
        educationCardList = db.getEducation();
        Collections.reverse(educationCardList);
        leisureCardList.clear();
        leisureCardList = db.getLeisure();
        Collections.reverse(leisureCardList);
        othersCardList.clear();
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
