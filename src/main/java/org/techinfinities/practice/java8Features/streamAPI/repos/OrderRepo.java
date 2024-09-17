package org.techinfinities.practice.java8Features.streamAPI.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.techinfinities.practice.java8Features.streamAPI.models.Order;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
    List<Order> findAll();
}
