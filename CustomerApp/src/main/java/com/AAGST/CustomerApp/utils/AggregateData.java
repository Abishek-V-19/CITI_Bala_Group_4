package com.AAGST.CustomerApp.utils;

public class AggregateData {
    private String _id;
    private double amount;

    public AggregateData(String _id, double amount) {
        this._id = _id;
        this.amount = amount;
    }
    public AggregateData() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AggregateData{" +
                "_id='" + _id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
