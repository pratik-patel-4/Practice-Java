package org.techinfinities.practice.java8Features.streamAPI;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.techinfinities.practice.java8Features.streamAPI.models.Customer;
import org.techinfinities.practice.java8Features.streamAPI.models.Order;
import org.techinfinities.practice.java8Features.streamAPI.models.Product;
import org.techinfinities.practice.java8Features.streamAPI.repos.CustomerRepo;
import org.techinfinities.practice.java8Features.streamAPI.repos.OrderRepo;
import org.techinfinities.practice.java8Features.streamAPI.repos.ProductRepo;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@DataJpaTest
public class StreamAPITest {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    private static long startTime, endTime;

    @Test
    @DisplayName("Obtain a list of product with category = \"Books\" and price > 100 ")
    public void exercise1() {
        startTime = System.currentTimeMillis();
        List<Product> products = productRepo.findAll()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Books"))
                .filter(product -> product.getPrice() > 100)
                .toList();

        endTime = System.currentTimeMillis();
        log.info("Product List Size : {}", products.size());
        log.info(String.format("exercise 1 - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));
    }

    @Test
    @DisplayName("Obtain a list of product with category = \"Books\" and price > 100 (using Predicate chaining for filter)")
    public void exercise1a() {
        Predicate<Product> categoryFilter = product -> product.getCategory().equalsIgnoreCase("Books");
        Predicate<Product> priceFilter = product -> product.getPrice() > 100;

        startTime = System.currentTimeMillis();
        List<Product> products = productRepo.findAll()
                .stream()
                .filter(product -> categoryFilter.and(priceFilter).test(product))
                .toList();
        endTime = System.currentTimeMillis();

        log.info(String.format("exercise 1a - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));
    }


    @Test
    @DisplayName("Obtain a list of product with category = \"Books\" and price > 100 (using BiPredicate for filter)")
    public void exercise1b() {
        BiPredicate<Product,String> categoryFilter = (product, category) -> product.getCategory().equalsIgnoreCase(category);
        startTime = System.currentTimeMillis();
        List<Product> products = productRepo.findAll()
                .stream().filter(product -> categoryFilter.test(product,"Books") && product.getPrice() > 100)
                .toList();
        endTime = System.currentTimeMillis();

        log.info(String.format("exercise 1b - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));
    }

    @Test
    @DisplayName("Obtain a list of order with products belong to category “Baby”")
    public void exercise2() {

        startTime = System.currentTimeMillis();
        List<Order> orders = orderRepo.findAll()
                .stream()
                .filter(o -> o.getProducts()
                        .stream()
                        .anyMatch(p -> p.getCategory().equalsIgnoreCase("Baby")))
                .toList();
        endTime = System.currentTimeMillis();
        log.info("Order List Size : {}", orders.size());
        log.info(String.format("exercise 2 - execution time: %1$d ms", (endTime - startTime)));
        orders.forEach(order -> log.info(order.toString()));

        startTime = System.currentTimeMillis();
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
        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + resultOrder.size());
        log.info(String.format("exercise 2 Traditional way - execution time: %1$d ms", (endTime - startTime)));
        resultOrder.forEach(order -> log.info(order.toString()));
    }

    @Test
    @DisplayName("Obtain a list of product with category = “Toys” and then apply 10% discount")
    public void exercise3() {
        startTime = System.currentTimeMillis();
        List<Product> resultProduct = new ArrayList<>();
        for(Product product : productRepo.findAll()) {
            if(product.getCategory().equalsIgnoreCase("Toys")) {
                product.setPrice(product.getPrice() * 0.9);
                resultProduct.add(product);
            }
        }
        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + resultProduct.size());
        log.info(String.format("exercise 3 Traditional way - execution time: %1$d ms", (endTime - startTime)));
        resultProduct.forEach(product -> log.info(product.toString()));

        startTime = System.currentTimeMillis();

        List<Product> productList = productRepo.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Toys"))
                .map( product -> product.withPrice(product.getPrice()*0.9))
                .toList();

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + productList.size());
        log.info(String.format("exercise 3 Stream API way - execution time: %1$d ms", (endTime - startTime)));
        productList.forEach(product -> log.info(product.toString()));
    }

    @Test
    @DisplayName("Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021")
    public void exercise4() {
        startTime = System.currentTimeMillis();

        Set<Product> products = new HashSet<>();

        for(Order order : orderRepo.findAll()) {
            if(order.getOrderDate().isBefore(LocalDate.of(2021,4,1))
                    && order.getOrderDate().isAfter(LocalDate.of(2021,2,1))) {
                Customer customer =  order.getCustomer();
                Set<Product> products1 = order.getProducts();
                if(customer.getTier().equalsIgnoreCase("2")) {
                    products.addAll(products1);
                }
            }
        }

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + products.size());
        log.info(String.format("exercise 4 Traditional way - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));

        startTime = System.currentTimeMillis();

        List<Product> productList = orderRepo.findAll().stream()
                .filter(order -> order.getCustomer().getTier().equalsIgnoreCase("2"))
                .filter(order -> order.getOrderDate().isAfter(LocalDate.of(2021,2,1)))
                .filter(order -> order.getOrderDate().isBefore(LocalDate.of(2021,4,1)))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + productList.size());
        log.info(String.format("exercise 4 Stream API way - execution time: %1$d ms", (endTime - startTime)));
        productList.forEach(product -> log.info(product.toString()));
    }

    @Test
    @DisplayName("Get the 3 cheapest products of \"Books\" category")
    public void exercise5() {

        startTime = System.currentTimeMillis();

        List<Product> products = new ArrayList<>();

        List<Product> allProducts = productRepo.findAll();
        Collections.sort(allProducts, (o1, o2) -> (int) (o1.getPrice() -  o2.getPrice()));

        for(Product product : allProducts) {
            if(products.size() == 3) {
                break;
            }
            if(product.getCategory().equalsIgnoreCase("Books")) {
                products.add(product);
            }
        }

        endTime = System.currentTimeMillis();

        log.info("Size of the result : " + products.size());
        log.info(String.format("exercise 5 Traditional way - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));

        startTime = System.currentTimeMillis();

        products = productRepo.findAll().stream()
                .sorted((o1 , o2) -> (int) (o1.getPrice() - o2.getPrice()))
                .filter(product -> product.getCategory().equalsIgnoreCase("Books"))
                .limit(3)
                .toList();

        endTime = System.currentTimeMillis();

        log.info("Size of the result : " + products.size());
        log.info(String.format("exercise 5 Stream API way - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(product -> log.info(product.toString()));
    }

    @Test
    @DisplayName("Get the cheapest products of “Books” category")
    public void exercise5a() {
        startTime = System.currentTimeMillis();

        Optional<Product> product = productRepo.findAll().stream()
                .filter((p) -> p.getCategory().equalsIgnoreCase("Books"))
                .min(Comparator.comparing(Product::getPrice));

        endTime = System.currentTimeMillis();

        log.info(String.format("exercise 5 Stream API way - execution time: %1$d ms", (endTime - startTime)));
        log.info(product.get().toString());

    }

    @Test
    @DisplayName("Get the 3 most recent placed order")
    public void exercise6() {
        startTime = System.currentTimeMillis();

        //Traditional way :

        // to store the result
        List<Order> orders = new ArrayList<>();

        List<Order> allOrders = orderRepo.findAll();

        Collections.sort(allOrders, (o1, o2) -> o2.getOrderDate().compareTo(o1.getOrderDate()));

        for(int i = 0; i < 3; i ++) {
            orders.add(allOrders.get(i));
        }

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + orders.size());
        log.info(String.format("exercise 6 Traditional way - execution time: %1$d ms", (endTime - startTime)));
        orders.forEach(order -> log.info(order.toString()));


        // Stream API Way:

        startTime = System.currentTimeMillis();

        orders = orderRepo.findAll().stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + orders.size());
        log.info(String.format("exercise 6 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
        orders.forEach(order -> log.info(order.toString()));
    }

    @Test
    @DisplayName("Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list")
    public void exercise7() {
        startTime = System.currentTimeMillis();

        // Traditional Way

        List<Order> orders = new ArrayList<>();

        List<Order> allOrders = orderRepo.findAll();

        for(Order order : allOrders) {
            if(order.getOrderDate().isEqual(LocalDate.of(2021,3,21))) {
                orders.add(order);
                System.out.println(order.toString());
                System.out.println("Order's Product List : ");
                order.getProducts().forEach(product -> System.out.println(product.toString()));
            }
        }

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + orders.size());
        log.info(String.format("exercise 7 Traditional way - execution time: %1$d ms", (endTime - startTime)));

        startTime = System.currentTimeMillis();

        List<Product> products = orderRepo.findAll().stream()
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021,3,21)))
                .peek(o -> System.out.println(o.toString()))
                .flatMap(order -> order.getProducts().stream())
                .distinct().toList();
        endTime = System.currentTimeMillis();

        log.info("Size of the result : " + products.size());
        log.info(String.format("exercise 7 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
        products.forEach(order -> log.info(order.toString()));
    }

    @Test
    @DisplayName("Calculate the total sum of all orders placed in Feb 2021")
    public void exercise8() {
        startTime = System.currentTimeMillis();

        Double sum = orderRepo.findAll().stream()
                .filter(o -> o.getOrderDate().isAfter(LocalDate.of(2021,2,1)))
                .filter(o -> o.getOrderDate().isBefore(LocalDate.of(2021,3,1)))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(p -> p.getPrice())
                .sum();

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + sum);
        log.info(String.format("exercise 8 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
    }

    @Test
    @DisplayName("Calculate the total lump sum of all orders placed in Feb 2021 (using reduce with BiFunction)")
    public void exercise8a() {
        startTime = System.currentTimeMillis();

        Double sum = orderRepo.findAll().stream()
                .filter(o -> o.getOrderDate().isAfter(LocalDate.of(2021,2,1)))
                .filter(o -> o.getOrderDate().isBefore(LocalDate.of(2021,3,1)))
                .flatMap(o -> o.getProducts().stream())
                .reduce(0D, (acc, product) -> acc + product.getPrice(), Double::sum);

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + sum);
        log.info(String.format("exercise 8 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
    }

    @Test
    @DisplayName("Calculate the average price of all orders placed on 15-Mar-2021")
    public void exercise9() {
        startTime = System.currentTimeMillis();

        Double avg = orderRepo.findAll().stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021,3,15)))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(p -> p.getPrice())
                .average().getAsDouble();

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + avg);
        log.info(String.format("exercise 8 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
    }

    @Test
    @DisplayName("Obtain statistics summary of all products belong to \"Books\" category")
    public void exercise10() {

        startTime = System.currentTimeMillis();

        DoubleSummaryStatistics statistics = productRepo.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .mapToDouble(p -> p.getPrice())
                .summaryStatistics();

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + statistics);
        log.info(String.format("exercise 8 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
    }

    @Test
    @DisplayName("Obtain a mapping of order id and the order's product count")
    public void exercise11() {
        startTime = System.currentTimeMillis();

        Map<Long,Integer> map = orderRepo.findAll().stream()
                .collect(Collectors.toMap(Order::getId, order -> order.getProducts().size()));

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + map.size());
        log.info(String.format("exercise 8 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
        log.info(map.toString());
    }

    @Test
    @DisplayName("Obtain a data map of customer and list of orders")
    public void exercise12() {

        startTime = System.currentTimeMillis();

        Map<Customer, List<Order>> map = orderRepo.findAll().stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        endTime = System.currentTimeMillis();
        log.info("Size of the result : " + map.size());
        log.info(String.format("exercise 12 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
        log.info(map.toString());
    }

    @Test
    @DisplayName("Obtain a data map of customer_id and list of order_id(s)")
    public void exercise12a() {

        startTime = System.currentTimeMillis();

        Map<Long, List<Long>> map = orderRepo.findAll().stream()
                .collect(
                        Collectors.groupingBy(
                                order -> order.getCustomer().getId(),
                                HashMap::new,
                                Collectors.mapping(Order::getId, Collectors.toList())
                        )
                );

        log.info("Size of the result : " + map.size());
        log.info(String.format("exercise 12 STREAM API way - execution time: %1$d ms", (endTime - startTime)));
        log.info(map.toString());
    }

    @Test
    @DisplayName("get second largest number from array")
    public void getSecondLargestNumber() {
        startTime = System.currentTimeMillis();

        List<Integer> list = new ArrayList<>(
                Arrays.asList(8,5,9,6,4,8,5,7,2,1,9)
        );

        int secondLaregest = list.stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(1)
                .findFirst().get();

        endTime = System.currentTimeMillis();

        System.out.println(secondLaregest);
        log.info("Second Largest Number  : " + secondLaregest);
        log.info(String.format("execution time: %1$d ms", (endTime - startTime)));
    }

}
