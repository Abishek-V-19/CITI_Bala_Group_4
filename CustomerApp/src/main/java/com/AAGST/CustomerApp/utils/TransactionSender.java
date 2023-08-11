package com.AAGST.CustomerApp.utils;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class TransactionSender {

    private String gender;
    private String category;
    private String merchant;
    private String city;
    private String state;
    private long cityPopulationUpper ;
    private long cityPopulationLower ;
    private double transactionAmountUpper;
    private double transactionAmountLower;
    public TransactionSender(){

    }
    public TransactionSender( String gender, String category, String merchant, String city, String state, long cityPopulationUpper, long cityPopulationLower, double transactionAmountUpper, double transactionAmountLower) {

        this.gender = gender;
        this.category = category;
        this.merchant = merchant;
        this.city = city;
        this.state = state;
        this.cityPopulationUpper = cityPopulationUpper;
        this.cityPopulationLower = cityPopulationLower;
        this.transactionAmountUpper = transactionAmountUpper;
        this.transactionAmountLower = transactionAmountLower;
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

    public long getCityPopulationUpper() {
        return cityPopulationUpper;
    }

    public void setCityPopulationUpper(long cityPopulationUpper) {
        this.cityPopulationUpper = cityPopulationUpper;
    }

    public long getCityPopulationLower() {
        return cityPopulationLower;
    }

    public void setCityPopulationLower(long cityPopulationLower) {
        this.cityPopulationLower = cityPopulationLower;
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

    @Override
    public String toString() {
        return "TransactionSender{" +
                "gender='" + gender + '\'' +
                ", category='" + category + '\'' +
                ", merchant='" + merchant + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", cityPopulationUpper=" + cityPopulationUpper +
                ", cityPopulationLower=" + cityPopulationLower +
                ", transactionAmountUpper=" + transactionAmountUpper +
                ", transactionAmountLower=" + transactionAmountLower +
                '}';
    }
}

