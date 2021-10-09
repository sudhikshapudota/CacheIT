package com.example.cacheit;

public class FuelCard {
    private String amount, date, card, type;

    /*public Card() {
    }*/

    public FuelCard(String amount, String date, String card, String type) {
        this.amount = amount;
        this.date = date;
        this.card = card;
        this.type = type;
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
}

