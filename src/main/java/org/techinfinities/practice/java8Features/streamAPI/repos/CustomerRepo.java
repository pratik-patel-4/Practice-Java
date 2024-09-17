package org.techinfinities.practice.java8Features.streamAPI.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.techinfinities.practice.java8Features.streamAPI.models.Customer;


@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {
    List<Customer> findAll();
}