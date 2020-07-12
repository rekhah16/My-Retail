package com.myretail.service;

import com.myretail.dao.repository.ProductPriceRepository;
import com.myretail.model.Price;
import com.myretail.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    ProductService productService;

    @Mock
    ProductPriceRepository productPriceRepository;

    @Test
    public void testGetProductById(){
        Product product = mock(Product.class);
        Price price = mock(Price.class);
        Optional<Price> priceO = Optional.of((Price) price);
        productService.getProductById("1");
    }
}
