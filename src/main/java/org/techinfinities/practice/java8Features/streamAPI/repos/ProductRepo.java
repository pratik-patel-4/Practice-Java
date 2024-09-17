package org.techinfinities.practice.java8Features.streamAPI.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.techinfinities.practice.java8Features.streamAPI.models.Product;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findAll();
}
