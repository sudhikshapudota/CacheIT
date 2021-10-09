package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EducationAdaptor extends RecyclerView.Adapter<EducationAdaptor.MyViewHolder> {

    private List<EducationCard> educationCardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            card = (TextView) view.findViewById(R.id.card);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public EducationAdaptor(Context context, List<EducationCard> educationCardList) {
        this.educationCardList = educationCardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_education, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        EducationCard educationCard = educationCardList.get(position);
        holder.amount.setText("Amount: " + educationCard.getAmount());
        holder.date.setText("Date: " + educationCard.getDate());
        holder.card.setText("Card: " + educationCard.getCard());
        //holder.type.setText(clothesCard.getType());
    }

    @Override
    public int getItemCount() {
        return educationCardList.size();
    }
}