package com.example.springmvc.controllers;

import com.example.springmvc.models.Product;
import com.example.springmvc.repos.ProductRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/product")
public class RestApiController {

    @Autowired
    private ProductRepo repo;
    private final Gson gson = new Gson();

    @GetMapping("/")
    private String getProducts() {
        List<Product> products = StreamSupport.stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return gson.toJson(products);
    }

    @GetMapping("/{id}")
    private ResponseEntity<String> getProduct(@PathVariable("id") Integer id) {
        Optional<Product> product = repo.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gson.toJson(product.get()), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<String> addProduct(@RequestBody String jsonProduct) {

        Product product = gson.fromJson(jsonProduct, Product.class);
        repo.save(product);
        return new ResponseEntity<>(gson.toJson(product), HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<String> updateProduct(@RequestBody String jsonProduct) {

        Product receivedProduct = gson.fromJson(jsonProduct, Product.class);
        repo.save(receivedProduct);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        repo.deleteById(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
