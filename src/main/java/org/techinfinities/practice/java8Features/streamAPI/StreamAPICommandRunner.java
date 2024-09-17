package org.techinfinities.practice.java8Features.streamAPI;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.techinfinities.practice.java8Features.streamAPI.repos.CustomerRepo;
import org.techinfinities.practice.java8Features.streamAPI.repos.OrderRepo;
import org.techinfinities.practice.java8Features.streamAPI.repos.ProductRepo;

@Slf4j
@Component
public class StreamAPICommandRunner implements CommandLineRunner {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        log.info("Customers:");
        customerRepo.findAll()
                .forEach(customer -> log.info(customer.toString()));

        log.info("Products:");
        productRepo.findAll()
                .forEach(product -> log.info(product.toString()));

        log.info("Orders:");
        orderRepo.findAll()
                .forEach(order -> log.info(order.toString()));


    }
}
