package com.AAGST.CustomerApp.utils;

public class CreditCardDeleteSender {
    private String cardNumber;
    private long customerId;
    private String firstName;
    private String lastName;

    public CreditCardDeleteSender(String cardNumber, long customerId, String firstName, String lastName) {
        this.cardNumber = cardNumber;
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CreditCardDeleteSender() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "CreditCardDeleteSender{" +
                "cardNumber='" + cardNumber + '\'' +
                ", customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
