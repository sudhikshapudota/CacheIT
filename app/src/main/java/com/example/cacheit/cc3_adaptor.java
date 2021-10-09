package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class cc3_adaptor extends RecyclerView.Adapter<cc3_adaptor.MyViewHolder> {

    private List<cc3_card> cc3CardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            type = (TextView) view.findViewById(R.id.type);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public cc3_adaptor(Context context, List<cc3_card> cc3CardList) {
        this.cc3CardList = cc3CardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_cc3, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        cc3_card cc3Card = cc3CardList.get(position);
        holder.amount.setText("Amount: " + cc3Card.getAmount());
        holder.date.setText("Date: " + cc3Card.getDate());
        holder.type.setText("Type: " + cc3Card.getType());
        //holder.type.setText(entertainmentCard.getType());
    }

    @Override
    public int getItemCount() {
        return cc3CardList.size();
    }
}