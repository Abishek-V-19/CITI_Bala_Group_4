package com.AAGST.CustomerApp.Repository;

import com.AAGST.CustomerApp.Entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction,Long> {
}
