package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FuelAdaptor extends RecyclerView.Adapter<FuelAdaptor.MyViewHolder> {

    private List<FuelCard> fuelCardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            card = (TextView) view.findViewById(R.id.card);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public FuelAdaptor(Context context, List<FuelCard> fuelCardList) {
        this.fuelCardList = fuelCardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_fuel, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        FuelCard fuelCard = fuelCardList.get(position);
        holder.amount.setText("Amount: " + fuelCard.getAmount());
        holder.date.setText("Date: " + fuelCard.getDate());
        holder.card.setText("Card: " + fuelCard.getCard());
        //holder.type.setText(clothesCard.getType());
    }

    @Override
    public int getItemCount() {
        return fuelCardList.size();
    }
}