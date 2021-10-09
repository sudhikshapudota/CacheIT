package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class cc2_adaptor extends RecyclerView.Adapter<cc2_adaptor.MyViewHolder> {

    private List<cc2_card> cc2CardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            type = (TextView) view.findViewById(R.id.type);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public cc2_adaptor(Context context, List<cc2_card> cc2CardList) {
        this.cc2CardList = cc2CardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_cc2, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        cc2_card cc2Card = cc2CardList.get(position);
        holder.amount.setText("Amount: " + cc2Card.getAmount());
        holder.date.setText("Date: " + cc2Card.getDate());
        holder.type.setText("Type: " + cc2Card.getType());
        //holder.type.setText(entertainmentCard.getType());
    }

    @Override
    public int getItemCount() {
        return cc2CardList.size();
    }
}