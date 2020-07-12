package com.myretail;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.dao.repository.ProductPriceRepository;
import com.myretail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@EnableMongoRepositories(value = {"com.myretail.dao.repository"})
@SpringBootApplication
public class MyretailApplication {

    @Autowired
    ProductPriceRepository productPriceRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyretailApplication.class, args);
    }

    @PostConstruct
    public void loadInitialData() throws IOException {
        productPriceRepository.deleteAll();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Product>> mapType = new TypeReference<List<Product>>() {
        };
        InputStream is = TypeReference.class.getResourceAsStream("/product.json");

        List<Product> products = mapper.readValue(is, mapType);
        products.forEach(product -> {
            product.getPrice().setId(product.getId());
            productPriceRepository.insert(product.getPrice());
        });
    }
}

