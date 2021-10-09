package com.example.cacheit;

public class cc4_card {
    private String amount, date, card, type, place;

    /*public Card() {
    }*/

    public cc4_card(int anInt, String amount, String date, String card, String type, String place) {
        this.amount = amount;
        this.date = date;
        this.card = card;
        this.type = type;
        this.place = place;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

