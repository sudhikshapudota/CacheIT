package com.example.cacheit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Collections;

import static com.example.cacheit.MainActivity.cc1CardList;
import static com.example.cacheit.MainActivity.cc2CardList;
import static com.example.cacheit.MainActivity.cc3CardList;
import static com.example.cacheit.MainActivity.cc4CardList;
import static com.example.cacheit.MainActivity.departmentCardList;
import static com.example.cacheit.MainActivity.utilitiesCardList;
import static com.example.cacheit.MainActivity.fuelCardList;
import static com.example.cacheit.MainActivity.educationCardList;
import static com.example.cacheit.MainActivity.leisureCardList;
import static com.example.cacheit.MainActivity.othersCardList;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    Button recent, category, expenditurespare, card_expenditures;
    private ProgressDialog dialog;
    DatabaseHelper db;
    String cc1, cc2, cc3, cc4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recent = findViewById(R.id.recent_exp);
        category = findViewById(R.id.category_exp);
        expenditurespare = findViewById(R.id.add_exp);
        card_expenditures = findViewById(R.id.card_exp);
        //add_card = findViewById(R.id.add_card);
        recent.setOnClickListener(this);
        category.setOnClickListener(this);
        expenditurespare.setOnClickListener(this);
        card_expenditures.setOnClickListener(this);
        //add_card.setOnClickListener(this);
        db = new DatabaseHelper(this);
        getCardsDetails();
        getTabsDetails();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recent_exp:
                Intent i = new Intent(Dashboard.this, RecentExpenses.class);
                startActivity(i);
                break;
            case R.id.category_exp:
                Intent j = new Intent(Dashboard.this, tabs.class);
                startActivity(j);
                break;
            case R.id.add_exp:
                Intent k = new Intent(Dashboard.this, Homepage.class).putExtra("New", "true");
                startActivity(k);
                break;
            case R.id.card_exp:
                Intent l = new Intent(Dashboard.this, Cards.class);
                startActivity(l);
                break;
            /*case R.id.add_card:
                Intent m = new Intent(Dashboard.this, detailsCard.class);
                startActivity(m);
                break;*/
        }
    }

    private boolean getCardsDetails() {
        dialog = new ProgressDialog(this);
        dialog.show();
        cc1CardList.clear();
        SharedPreferences prfs = getSharedPreferences("UserAccount", Context.MODE_PRIVATE);

        if (prfs.contains("cc1"))
            cc1 = prfs.getString("cc1", null);
        if (cc1 != null || !cc1.equals(""))
            cc1CardList = db.getcc1(Integer.parseInt(cc1));
        else
            Log.d("length", "empty");
        Collections.reverse(cc1CardList);
        cc2CardList.clear();

        if (prfs.contains("cc2")) {
            cc2 = prfs.getString("cc2", null);
            Log.d("a", cc2);
            if (cc2 != null || !cc2.equals("")) {
                cc2CardList = db.getcc2(Integer.parseInt(cc2));
                Collections.reverse(cc2CardList);
            }
        }

        cc3CardList.clear();
        if (prfs.contains("cc3")) {
            cc3 = prfs.getString("cc3", null);
            if (cc3 != null || !cc3.equals("")) {
                cc3CardList = db.getcc3(Integer.parseInt(cc3));
                Collections.reverse(cc3CardList);
            }
        }
        cc4CardList.clear();
        if (prfs.contains("cc4")) {
            cc4 = prfs.getString("cc4", null);
            if (cc4 != null || !cc4.equals("")) {
                cc4CardList = db.getcc4(Integer.parseInt(cc4));
                Collections.reverse(cc4CardList);
            }
        }
        MainActivity.recentCardList.clear();
        MainActivity.recentCardList = db.getRecent();
        Log.d("cc1CardList:", String.valueOf(cc1CardList.size()));
        Log.d("cc2CardList:", String.valueOf(cc2CardList.size()));
        Log.d("cc3CardList:", String.valueOf(cc3CardList.size()));
        Log.d("cc4CardList:", String.valueOf(cc4CardList.size()));
        Log.d("recentListHome:", String.valueOf(MainActivity.recentCardList));
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        return true;
        //Toast.makeText(getActivity(), "foodlist size " + foodList.size(), Toast.LENGTH_SHORT).show();
        // Cursor food = db.getType("Food");
        // Log.d("FOOD LIST", food.toString());

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
