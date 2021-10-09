package com.example.cacheit.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cacheit.cc4_adaptor;
import com.example.cacheit.cc4_card;
import com.example.cacheit.DatabaseHelper;
import com.example.cacheit.Homepage;
import com.example.cacheit.MainActivity;
import com.example.cacheit.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.cacheit.MainActivity.cc4CardList;

/**
 * A simple {@link Fragment} subclass.
 */
public class cc4 extends Fragment {

    private DatabaseHelper db;
    private cc4_adaptor mAdapter;
    private List<cc4_card> cc4CardList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    GraphView graphView;

    public cc4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cc4, container, false);
        graphView = view.findViewById(R.id.graphview);
        recyclerView = view.findViewById(R.id.cc4);
        db = new DatabaseHelper(getActivity());
        cc4CardList = MainActivity.cc4CardList;
        mAdapter = new cc4_adaptor(getActivity(), cc4CardList);
        LoadGraph(cc4CardList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        Log.d("fragment", "cc4");
        //new EntertainmentAdaptor(getActivity(), entertainmentCardList);
        mAdapter.notifyDataSetChanged();
        //getEntertainmentList();
        return view;
    }

    private void LoadGraph(List<cc4_card> cc1Cards) {
        if (cc1Cards.size() > 0) {
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
                Date d = sdf.parse(cc1Cards.get(0).getDate());
                java.sql.Date sql = new java.sql.Date(d.getTime());
                Log.d("sql: ", String.valueOf(d));
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
            for (int i = 0; i < cc1Cards.size(); i++) {
                String[] strings = cc1Cards.get(i).getDate().split("-");
                Log.d("splitter: ", strings[1].split("-")[0]);
                if ((calendar.get(Calendar.MONTH) + 1) == Integer.valueOf(strings[1].split("-")[0])) {
                    DataPoint dataPoint = null;

                    dataPoint = new DataPoint(Double.parseDouble(strings[2].split(" ")[0])/*sdf.parse(recentCards.get(i).getDate())*/, Double.parseDouble(cc1Cards.get(i).getAmount()));

                    series.appendData(dataPoint, false, 100);
                }
            }
            //LineGraphSeries series = new LineGraphSeries<>(new DataPoint[]{new DataPoint(0, 1), new DataPoint(1, 5), new DataPoint(2, 3)});
            graphView.addSeries(series);
        }
    }

/*
    private void getcc1List() {
        dialog = new ProgressDialog(getActivity());
        dialog.show();
        cc4CardList = db.getcc4();
        new cc4_adaptor(getActivity(), cc4CardList);
        mAdapter.notifyDataSetChanged();
        //Toast.makeText(getActivity(), "entertainmentList size " + entertainmentCardList.size(), Toast.LENGTH_SHORT).show();
        // Cursor food = db.getType("Food");
        // Log.d("FOOD LIST", food.toString());
        if (dialog.isShowing())
            dialog.dismiss();
    }
*/

}
