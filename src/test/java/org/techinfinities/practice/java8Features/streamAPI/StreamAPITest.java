package org.techinfinities.practice.java8Features.streamAPI;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.techinfinities.practice.java8Features.streamAPI.models.Product;
import org.techinfinities.practice.java8Features.streamAPI.repos.CustomerRepo;
import org.techinfinities.practice.java8Features.streamAPI.repos.OrderRepo;
import org.techinfinities.practice.java8Features.streamAPI.repos.ProductRepo;

import java.util.List;

@Slf4j
@DataJpaTest
public class StreamAPITest {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;


    @Test
    @DisplayName("Obtain a list of product with category = \"Books\" and price > 100 ")
    public void exercise1() {
        long startTime = System.currentTimeMillis();
        List<Product> products = productRepo.findAll()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Books"))
                .filter(product -> product.getPrice() > 100)
                .toList();

        long endTime = System.currentTimeMillis();
        log.info("Product List Size : {}", products.size());
        log.info(String.format("exercise 1 - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));
    }



}
