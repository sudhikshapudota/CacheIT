package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OthersAdaptor extends RecyclerView.Adapter<OthersAdaptor.MyViewHolder> {

    private List<OthersCard> othersCardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            card = (TextView) view.findViewById(R.id.card);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public OthersAdaptor(Context context, List<OthersCard> othersCardList) {
        this.othersCardList = othersCardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_others, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        OthersCard othersCard = othersCardList.get(position);
        holder.amount.setText("Amount: " + othersCard.getAmount());
        holder.date.setText("Date: " + othersCard.getDate());
        holder.card.setText("Card: " + othersCard.getCard());
        //holder.type.setText(clothesCard.getType());
    }

    @Override
    public int getItemCount() {
        return othersCardList.size();
    }
}