package com.example.cacheit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecentAdaptor extends RecyclerView.Adapter<RecentAdaptor.MyViewHolder> {

    private List<RecentCard> recentCardList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, date, card, type, place;
        LinearLayout linear;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            card = (TextView) view.findViewById(R.id.card);
            date = view.findViewById(R.id.date);
            type = view.findViewById(R.id.type);
            place = view.findViewById(R.id.place);
            linear = view.findViewById(R.id.linear);
        }

       /* @Override
        public boolean onLongClick(View v) {
            Toast.makeText(context, "LongClick", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Alert!");
            builder.setMessage("Do you want to enter the type?");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RecentCard recentCard = recentCardList.get(hol)
                    context.startActivity(new Intent(context, Homepage.class).putExtra("card", recentCard.getCard()).putExtra("amount", recentCard.getAmount()).putExtra("place", recentCard.getPlace()));
                }
            });
            return true;
        }*/
    }

    public RecentAdaptor(Context context, List<RecentCard> recentCardList) {
        this.recentCardList = recentCardList;
        this.context = context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_recent_total, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        final RecentCard recentCard = recentCardList.get(position);
        holder.amount.setText("Amount:" + recentCard.getAmount());
        holder.date.setText("Date: " + recentCard.getDate());
        holder.card.setText("Card: " + recentCard.getCard());
        holder.type.setText("Type: " + recentCard.getType());
        holder.place.setText("Place: " + recentCard.getPlace());
        holder.itemView.setLongClickable(true);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setdata(recentCard);
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /*holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Alert!");
                builder.setMessage("Do you want to enter the type?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(context, Homepage.class).putExtra("card", recentCard.getCard()).putExtra("amount", recentCard.getAmount()).putExtra("place", recentCard.getPlace()));
                    }
                });
                return true;
            }
        });*/
    }

    private void setdata(final RecentCard recentCard) {
        // Toast.makeText(context, "on click", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Type of expenditure")
                .setMessage("Are you sure you want to add the type of expenditure?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(context, Homepage.class).putExtra("card", recentCard.getCard())
                                .putExtra("amount", recentCard.getAmount()).putExtra("New", "false").putExtra("place", recentCard.getPlace())
                                .putExtra("id", recentCard.getId()));
                        dialog.dismiss();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

       /* AlertDialog.Builder builder = new AlertDialog.Builder(RecentAdaptor.this);
        builder.setTitle("Alert!");
        builder.setMessage("Do you want to enter the type?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(context, Homepage.class).putExtra("card", recentCard.getCard()).putExtra("amount", recentCard.getAmount()).putExtra("place", recentCard.getPlace()));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return recentCardList.size();
    }
}