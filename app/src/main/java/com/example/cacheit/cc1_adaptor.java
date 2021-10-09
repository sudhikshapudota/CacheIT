package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class cc1_adaptor extends RecyclerView.Adapter<cc1_adaptor.MyViewHolder> {

    private List<cc1_Card> cc1CardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            type = (TextView) view.findViewById(R.id.type);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public cc1_adaptor(Context context, List<cc1_Card> cc1CardList) {
        this.cc1CardList = cc1CardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_cc1, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        cc1_Card cc1Card = cc1CardList.get(position);
        holder.amount.setText("Amount: " + cc1Card.getAmount());
        holder.date.setText("Date: " + cc1Card.getDate());
        holder.type.setText("Type: " + cc1Card.getType());
        //holder.type.setText(entertainmentCard.getType());
    }

    @Override
    public int getItemCount() {
        return cc1CardList.size();
    }
}