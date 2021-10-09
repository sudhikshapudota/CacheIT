package com.example.cacheit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecentExpenses extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton dashboard, newExpendtiure;
    private DatabaseHelper db;
    private RecentAdaptor mAdapter;
    private List<RecentCard> recentCardList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    GraphView graphview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_expenses);

        graphview = findViewById(R.id.graphview);
        recyclerView = findViewById(R.id.recentExpenditures);
        db = new DatabaseHelper(this);
        recentCardList = MainActivity.recentCardList;
        mAdapter = new RecentAdaptor(this, recentCardList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        LoadGraph(recentCardList);
        dashboard = findViewById(R.id.dashboard_goto);
        newExpendtiure = findViewById(R.id.add_new);
        dashboard.setOnClickListener(this);
        newExpendtiure.setOnClickListener(this);
    }

    private void LoadGraph(List<RecentCard> recentCards) {
        if (recentCards.size() > 0) {
       /* LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);*/

            LineGraphSeries series = new LineGraphSeries();
            Calendar calendar = Calendar.getInstance();
            DataPoint[] doubles_month;
            Log.d("Month: ", String.valueOf(calendar.get(Calendar.MONTH) + 1));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date d = sdf.parse(recentCards.get(0).getDate());
                java.sql.Date sql = new java.sql.Date(d.getTime());
                Log.d("sql: ", String.valueOf(d));
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
            for (int i = 0; i < recentCards.size(); i++) {
                String[] strings = recentCards.get(i).getDate().split("-");
                Log.d("splitter: ", strings[1].split("-")[0]);
                if ((calendar.get(Calendar.MONTH) + 1) == Integer.valueOf(strings[1].split("-")[0])) {
                    DataPoint dataPoint = null;

                    dataPoint = new DataPoint(Double.parseDouble(strings[2].split(" ")[0])/*sdf.parse(recentCards.get(i).getDate())*/, Double.parseDouble(recentCards.get(i).getAmount()));

                    series.appendData(dataPoint, false, 100);
                }
            }
            //LineGraphSeries series = new LineGraphSeries<>(new DataPoint[]{new DataPoint(0, 1), new DataPoint(1, 5), new DataPoint(2, 3)});
            graphview.addSeries(series);
        }
    }


    private void getrecentCardlist() {
        dialog = new ProgressDialog(this);
        dialog.show();
        recentCardList = db.getRecent();
        new RecentAdaptor(this, recentCardList);
        mAdapter.notifyDataSetChanged();
        //Toast.makeText(getActivity(), "entertainmentList size " + entertainmentCardList.size(), Toast.LENGTH_SHORT).show();
        // Cursor food = db.getType("Food");
        // Log.d("FOOD LIST", food.toString());
        if (dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new:
                startActivity(new Intent(this, Homepage.class).putExtra("New", "true"));
                break;
            case R.id.dashboard_goto:
                startActivity(new Intent(this,Dashboard.class));
                break;
        }
    }

}
