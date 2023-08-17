package com.AAGST.CustomerApp.utils;

public class CreditCardAddSender {
    private long customerId;

    private String description;


    public CreditCardAddSender(long customerId) {
        this.customerId = customerId;
        this.description = "default";
    }
    public CreditCardAddSender() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreditCardAddSender{" +
                "customerId=" + customerId +
                ", description='" + description + '\'' +
                '}';
    }
}
