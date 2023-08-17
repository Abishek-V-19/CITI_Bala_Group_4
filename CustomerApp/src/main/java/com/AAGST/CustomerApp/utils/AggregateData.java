package com.AAGST.CustomerApp.utils;

public class AggregateData {
    private String _id;
    private double amount;
    private long record_count;

    public AggregateData(String _id, double amount,long record_count) {
        this._id = _id;
        this.amount = amount;
        this.record_count = record_count;
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

    public long getRecord_count() {
        return record_count;
    }

    public void setRecord_count(long record_count) {
        this.record_count = record_count;
    }

    @Override
    public String toString() {
        return "AggregateData{" +
                "_id='" + _id + '\'' +
                ", amount=" + amount +
                ", record_count=" + record_count +
                '}';
    }
}
