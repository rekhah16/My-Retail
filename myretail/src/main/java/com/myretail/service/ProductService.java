package com.myretail.service;

import com.myretail.model.Product;

public interface ProductService {

    Product getProductById(String productId);

    void updateProduct(Product product);


}
