package com.myretail.dao.repository;

import com.myretail.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends MongoRepository<Price, String> {

}
