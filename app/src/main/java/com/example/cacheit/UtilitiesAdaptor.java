package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UtilitiesAdaptor extends RecyclerView.Adapter<UtilitiesAdaptor.MyViewHolder> {

    private List<UtilitiesCard> utilitiesCardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            card = (TextView) view.findViewById(R.id.card);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public UtilitiesAdaptor(Context context, List<UtilitiesCard> utilitiesCardList) {
        this.utilitiesCardList = utilitiesCardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_utilities, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        UtilitiesCard utilitiesCard = utilitiesCardList.get(position);
        holder.amount.setText("Amount: " + utilitiesCard.getAmount());
        holder.date.setText("Date: " + utilitiesCard.getDate());
        holder.card.setText("Card: " + utilitiesCard.getCard());
        //holder.type.setText(clothesCard.getType());
    }

    @Override
    public int getItemCount() {
        return utilitiesCardList.size();
    }
}