package com.AAGST.CustomerApp.utils;

public class CreditCardDeleteSender {
    private String cardNumber;
    private long customerId;

    public CreditCardDeleteSender(String cardNumber, long customerId) {
        this.cardNumber = cardNumber;
        this.customerId = customerId;
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



    @Override
    public String toString() {
        return "CreditCardDeleteSender{" +
                "cardNumber='" + cardNumber + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
