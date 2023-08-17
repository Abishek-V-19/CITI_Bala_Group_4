package com.AAGST.CustomerApp.utils;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;

import java.util.List;

public class CreditCardDetailsSender {
    private Customer customer;
    private List<CreditCard> creditCards;

    public CreditCardDetailsSender(Customer customer, List<CreditCard> creditCards) {
        this.customer = customer;
        this.creditCards = creditCards;
    }

    public CreditCardDetailsSender() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public String toString() {
        return "CreditCardDetailsSender{" +
                "customer=" + customer +
                ", creditCards=" + creditCards +
                '}';
    }
}
