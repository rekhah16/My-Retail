package com.myretail.controller;

import com.myretail.dao.repository.ProductPriceRepository;
import com.myretail.model.Product;
import com.myretail.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceControllerTest {

    @Mock
    ProductService productService;

    @Mock
    ProductPriceRepository productPriceRepository;

    @Mock
    ProductServiceController productServiceController;


    @Test
    public void testGetProductDetail(){
        Product product = mock(Product.class);
        when(productPriceRepository.findById("1")).thenReturn(null);
        when(productService.getProductById("1")).thenReturn(product);
        productServiceController.getProductDetail("1");
    }

}
