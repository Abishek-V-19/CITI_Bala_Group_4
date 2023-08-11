package com.AAGST.CustomerApp.utils;

import com.AAGST.CustomerApp.Entity.Transaction;

import java.util.List;

public class TransactionPerPage {
    int totalPages;
    long totalElements;
    int numOfelements ;
    int pageSize ;
    List<Transaction> Transaction;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumOfelements() {
        return numOfelements;
    }

    public void setNumOfelements(int numOfelements) {
        this.numOfelements = numOfelements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<com.AAGST.CustomerApp.Entity.Transaction> getTransaction() {
        return Transaction;
    }

    public void setTransaction(List<com.AAGST.CustomerApp.Entity.Transaction> transaction) {
        Transaction = transaction;
    }
}
