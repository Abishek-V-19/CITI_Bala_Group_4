package com.AAGST.CustomerApp.utils;

import java.util.List;

public class SummaryData {
    private List<AggregateData> gender;
    private List<AggregateData> category;
    private List<AggregateData> merchant;
    private List<AggregateData> city;
    private List<AggregateData> state;
    private List<AggregateData> profession;

    private long totalRecords;

    public SummaryData(List<AggregateData> gender, List<AggregateData> category, List<AggregateData> merchant, List<AggregateData> city, List<AggregateData> state, List<AggregateData> profession,long totalRecords) {
        this.gender = gender;
        this.category = category;
        this.merchant = merchant;
        this.city = city;
        this.state = state;
        this.profession = profession;
        this.totalRecords = totalRecords;
    }

    public SummaryData() {
    }

    public List<AggregateData> getGender() {
        return gender;
    }

    public void setGender(List<AggregateData> gender) {
        this.gender = gender;
    }

    public List<AggregateData> getCategory() {
        return category;
    }

    public void setCategory(List<AggregateData> category) {
        this.category = category;
    }

    public List<AggregateData> getMerchant() {
        return merchant;
    }

    public void setMerchant(List<AggregateData> merchant) {
        this.merchant = merchant;
    }

    public List<AggregateData> getCity() {
        return city;
    }

    public void setCity(List<AggregateData> city) {
        this.city = city;
    }

    public List<AggregateData> getState() {
        return state;
    }

    public void setState(List<AggregateData> state) {
        this.state = state;
    }

    public List<AggregateData> getProfession() {
        return profession;
    }

    public void setProfession(List<AggregateData> profession) {
        this.profession = profession;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    @Override
    public String toString() {
        return "SummaryData{" +
                "gender=" + gender +
                ", category=" + category +
                ", merchant=" + merchant +
                ", city=" + city +
                ", state=" + state +
                ", profession=" + profession +
                ", totalRecords=" + totalRecords +
                '}';
    }
}
