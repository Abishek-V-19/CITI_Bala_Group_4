package com.AAGST.CustomerApp.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("creditCard")
public class CreditCard {
    @Id
    private String cardNumber;
    private long customerId;
    private String creationTime;
    private String status;
    private String description;


    public CreditCard(String cardNumber, long customerId, String creationTime,String status,String description) {
        this.cardNumber = cardNumber;
        this.customerId = customerId;
        this.creationTime = creationTime;
        this.status = status;
        this.description = description;
    }

    public CreditCard() {
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

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", customerId=" + customerId +
                ", creationTime='" + creationTime + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
