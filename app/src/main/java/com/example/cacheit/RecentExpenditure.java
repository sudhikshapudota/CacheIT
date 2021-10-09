package com.example.cacheit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cacheit.DatabaseHelper;
import com.example.cacheit.Homepage;
import com.example.cacheit.MainActivity;
import com.example.cacheit.R;
import com.example.cacheit.RecentAdaptor;
import com.example.cacheit.RecentCard;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RecentExpenditure extends Fragment implements View.OnClickListener {

    private DatabaseHelper db;
/*    private RecentAdaptor mAdapter;
    private List<RecentCard> recentCardList = new ArrayList<>();*/
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    FloatingActionButton add_new;
    GraphView graphview;

    public RecentExpenditure() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_expenditure, container, false);
        graphview = view.findViewById(R.id.graphview);
        recyclerView = view.findViewById(R.id.recentExpenditures);
        add_new = view.findViewById(R.id.add_new);
        db = new DatabaseHelper(getActivity());
/*
        recentCardList = MainActivity.recentCardList;
*/
/*
        mAdapter = new RecentAdaptor(getActivity(), recentCardList);
*/
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
/*
        recyclerView.setAdapter(mAdapter);
*/
        add_new.setOnClickListener(this);
/*
        Log.d("food fragment", "food");
*/
        //new FoodAdaptor(getActivity(), foodList);
        /*mAdapter.notifyDataSetChanged();

        LoadGraph(recentCardList);*/
        //getFoodList();
        return view;
    }

    private void LoadGraph(List<RecentCard> recentCards) {

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


/*
    private void getrecentCardlist() {
        dialog = new ProgressDialog(getActivity());
        dialog.show();
        recentCardList = db.getRecent();
        new RecentAdaptor(getActivity(), recentCardList);
        mAdapter.notifyDataSetChanged();
        //Toast.makeText(getActivity(), "entertainmentList size " + entertainmentCardList.size(), Toast.LENGTH_SHORT).show();
        // Cursor food = db.getType("Food");
        // Log.d("FOOD LIST", food.toString());
        if (dialog.isShowing())
            dialog.dismiss();
    }
*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new:
                startActivity(new Intent(getActivity(), Homepage.class).putExtra("New", "true"));
        }
    }
}
