package com.AAGST.CustomerApp.utils;

public class CustomerAddSender {
    private String firstName;
    private String lastName;
    private String gender;
    private String profession;
    private String dob;

    public CustomerAddSender(String firstName, String lastName, String gender, String profession, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.profession = profession;
        this.dob = dob;
    }

    public CustomerAddSender() {
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "CustomerAddSender{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", profession='" + profession + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
