package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cacheit.fragments.Leisure;

import java.util.List;

public class LeisureAdaptor extends RecyclerView.Adapter<LeisureAdaptor.MyViewHolder> {

    private List<LeisureCard> leisureCardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            card = (TextView) view.findViewById(R.id.card);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public LeisureAdaptor(Context context, List<LeisureCard> leisureCardList) {
        this.leisureCardList = leisureCardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_leisure, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        LeisureCard leisureCard = leisureCardList.get(position);
        holder.amount.setText("Amount: " + leisureCard.getAmount());
        holder.date.setText("Date: " + leisureCard.getDate());
        holder.card.setText("Card: " + leisureCard.getCard());
        //holder.type.setText(clothesCard.getType());
    }

    @Override
    public int getItemCount() {
        return leisureCardList.size();
    }
}