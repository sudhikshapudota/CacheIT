package com.example.cacheit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DepartmentAdaptor extends RecyclerView.Adapter<DepartmentAdaptor.MyViewHolder> {

    private List<DepartmentCard> departmentCardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            card = (TextView) view.findViewById(R.id.card);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public DepartmentAdaptor(Context context, List<DepartmentCard> departmentCardList) {
        this.departmentCardList = departmentCardList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_department, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        DepartmentCard departmentCard = departmentCardList.get(position);
        holder.amount.setText("Amount: " + departmentCard.getAmount());
        holder.date.setText("Date: " + departmentCard.getDate());
        holder.card.setText("Card: " + departmentCard.getCard());
        //holder.type.setText(clothesCard.getType());
    }

    @Override
    public int getItemCount() {
        return departmentCardList   .size();
    }
}