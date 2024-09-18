package org.techinfinities.practice.java8Features.streamAPI;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.techinfinities.practice.java8Features.streamAPI.models.Order;
import org.techinfinities.practice.java8Features.streamAPI.models.Product;
import org.techinfinities.practice.java8Features.streamAPI.repos.CustomerRepo;
import org.techinfinities.practice.java8Features.streamAPI.repos.OrderRepo;
import org.techinfinities.practice.java8Features.streamAPI.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

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

    @Test
    @DisplayName("Obtain a list of product with category = \"Books\" and price > 100 (using Predicate chaining for filter)")
    public void exercise1a() {
        Predicate<Product> categoryFilter = product -> product.getCategory().equalsIgnoreCase("Books");
        Predicate<Product> priceFilter = product -> product.getPrice() > 100;

        long startTime = System.currentTimeMillis();
        List<Product> products = productRepo.findAll()
                .stream()
                .filter(product -> categoryFilter.and(priceFilter).test(product))
                .toList();
        long endTime = System.currentTimeMillis();

        log.info(String.format("exercise 1a - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));
    }


    @Test
    @DisplayName("Obtain a list of product with category = \"Books\" and price > 100 (using BiPredicate for filter)")
    public void exercise1b() {
        BiPredicate<Product,String> categoryFilter = (product, category) -> product.getCategory().equalsIgnoreCase(category);
        long startTime = System.currentTimeMillis();
        List<Product> products = productRepo.findAll()
                .stream().filter(product -> categoryFilter.test(product,"Books") && product.getPrice() > 100)
                .toList();
        long endTime = System.currentTimeMillis();

        log.info(String.format("exercise 1b - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));
    }

    @Test
    @DisplayName("Obtain a list of order with products belong to category “Baby”")
    public void exercise2() {

        long startTime = System.currentTimeMillis();
        List<Order> orders = orderRepo.findAll()
                .stream()
                .filter(o -> o.getProducts()
                        .stream()
                        .anyMatch(p -> p.getCategory().equalsIgnoreCase("Baby")))
                .toList();
        long endTime = System.currentTimeMillis();
        log.info("Order List Size : {}", orders.size());
        log.info(String.format("exercise 2 - execution time: %1$d ms", (endTime - startTime)));
        orders.forEach(order -> log.info(order.toString()));

        long startTimeForTraditionalWay = System.currentTimeMillis();
        List<Order> resultOrder  = new ArrayList<>();
        for(Order order : orderRepo.findAll()) {
            Set<Product> products = order.getProducts();
            for(Product product : products) {
                if(product.getCategory().equalsIgnoreCase("Baby")) {
                    resultOrder.add(order);
                    break;
                }
            }
        }
        long endTimeForTraditionalWay = System.currentTimeMillis();
        log.info("Size of the result : " + resultOrder.size());
        log.info(String.format("exercise 2 Traditional way - execution time: %1$d ms", (endTimeForTraditionalWay - startTimeForTraditionalWay)));
        resultOrder.forEach(order -> log.info(order.toString()));
    }



}
