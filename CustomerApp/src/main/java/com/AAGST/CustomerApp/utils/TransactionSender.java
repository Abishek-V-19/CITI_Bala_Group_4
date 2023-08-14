package com.AAGST.CustomerApp.utils;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class TransactionSender {

    private String gender = "null";
    private String category = "null";
    private String merchant = "null";
    private String city = "null";
    private String state = "null";
    private double transactionAmountUpper = -1;
    private double transactionAmountLower = -1;
    private String profession = "null";
    public TransactionSender(){

    }
    public TransactionSender( String gender, String category, String merchant, String city, String state, double transactionAmountUpper, double transactionAmountLower,String profession) {

        this.gender = gender;
        this.category = category;
        this.merchant = merchant;
        this.city = city;
        this.state = state;
        this.transactionAmountUpper = transactionAmountUpper;
        this.transactionAmountLower = transactionAmountLower;
        this.profession = profession;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public double getTransactionAmountUpper() {
        return transactionAmountUpper;
    }

    public void setTransactionAmountUpper(double transactionAmountUpper) {
        this.transactionAmountUpper = transactionAmountUpper;
    }

    public double getTransactionAmountLower() {
        return transactionAmountLower;
    }

    public void setTransactionAmountLower(double transactionAmountLower) {
        this.transactionAmountLower = transactionAmountLower;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "TransactionSender{" +
                "gender='" + gender + '\'' +
                ", category='" + category + '\'' +
                ", merchant='" + merchant + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", transactionAmountUpper=" + transactionAmountUpper +
                ", transactionAmountLower=" + transactionAmountLower +
                '}';
    }
}

