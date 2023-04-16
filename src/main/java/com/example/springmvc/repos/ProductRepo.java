package com.example.springmvc.repos;

import com.example.springmvc.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {

}