package com.myretail.service;

import com.myretail.dao.repository.ProductPriceRepository;
import com.myretail.exception.MyRetailException;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.rest.client.ProductDataClient;
import com.myretail.util.ProductUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    protected Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class); // implement logging
    @Autowired
    ProductPriceRepository productPriceRepository;
    @Autowired
    ProductDataClient client;
    @Value("${redsky.target.com_end_point}")
    private String endPointUrl;

    @Override
    public Product getProductById(String productId) {
        Product product = null;
        try {
            Optional<Price> price = this.productPriceRepository.findById(productId);
            if (price == null) {
                throw new ProductNotFoundException("no product price data found for productId=" + productId);
            }
            Map map = client.getProductData(endPointUrl, productId, Map.class);
            product = new Product();
            product.setId(productId);
            product.setName(ProductUtil.getProductName(map));
            product.setPrice(price.get());
        } catch (ProductNotFoundException ex) {
            throw ex;
        } catch (MyRetailException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("Error while getting product data {}", ex);
            throw new MyRetailException("error while getting product data");
        }
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        if (productPriceRepository.existsById(product.getId())) {
            Price price = product.getPrice();
            price.setId(product.getId());
            productPriceRepository.save(price);
        } else {
            throw new ProductNotFoundException("product id does not exit");
        }
    }

}
