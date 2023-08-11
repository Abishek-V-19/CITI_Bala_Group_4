package com.AAGST.CustomerApp.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("transaction")
public class Transaction {
    @Id
    private long transactionId;
    @Field("trans_date_trans_time")
    private String transactionDateTime;
    @Field("amt")
    private double transactionAmount;
    @Field("customer_id")
    private long customerId;
    private String city;
    private String state;
    @Field("city_population")
    private long cityPopulation ;
    private String merchant;
    private String category;
    @Field("first")
    private String firstName;
    @Field("last")
    private String lastName;
    private String gender;

    @Field("Job")
    private String job;
    private String dob;

    public Transaction(long transactionId, String transactionDateTime, double transactionAmount, long customerId, String city, String state, long cityPopulation, String merchant, String category, String firstName, String lastName, String gender, String job, String dob) {
        this.transactionId = transactionId;
        this.transactionDateTime = transactionDateTime;
        this.transactionAmount = transactionAmount;
        this.customerId = customerId;
        this.city = city;
        this.state = state;
        this.cityPopulation = cityPopulation;
        this.merchant = merchant;
        this.category = category;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }
    public Transaction(){

    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public long getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(long cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDateTime='" + transactionDateTime + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", customerId=" + customerId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", cityPopulation='" + cityPopulation + '\'' +
                ", merchant='" + merchant + '\'' +
                ", category='" + category + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dob=" + dob +
                '}';
    }

}
