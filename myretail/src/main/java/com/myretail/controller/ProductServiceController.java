package com.myretail.controller;

import com.myretail.exception.BadRequestException;
import com.myretail.model.Product;
import com.myretail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductServiceController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductDetail(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateProductInfo(@RequestBody Product product, @PathVariable("id") String productId) {
        if (product == null) {
            throw new BadRequestException("request body is null");
        }
        if (!product.getId().equals(productId)) {
            throw new BadRequestException("incorrect product id");
        }
        productService.updateProduct(product);
    }

}
