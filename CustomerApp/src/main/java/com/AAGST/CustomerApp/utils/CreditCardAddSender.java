package com.AAGST.CustomerApp.utils;

public class CreditCardAddSender {
    private long customerId;
    public CreditCardAddSender() {
    }
    public CreditCardAddSender(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CreditCardAddSender{" +
                "customerId=" + customerId +
                '}';
    }
}
