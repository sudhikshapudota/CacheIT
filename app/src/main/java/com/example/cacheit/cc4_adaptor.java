package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class cc4_adaptor extends RecyclerView.Adapter<cc4_adaptor.MyViewHolder> {

    private List<cc4_card> cc4CardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            type = (TextView) view.findViewById(R.id.type);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public cc4_adaptor(Context context, List<cc4_card> cc4CardList) {
        this.cc4CardList = cc4CardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_cc4, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        cc4_card cc4Card = cc4CardList.get(position);
        holder.amount.setText("Amount: " + cc4Card.getAmount());
        holder.date.setText("Date: " + cc4Card.getDate());
        holder.type.setText("Type: " + cc4Card.getType());
        //holder.type.setText(entertainmentCard.getType());
    }

    @Override
    public int getItemCount() {
        return cc4CardList.size();
    }
}