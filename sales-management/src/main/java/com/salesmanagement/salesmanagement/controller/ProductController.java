package com.salesmanagement.salesmanagement.controller;


import com.salesmanagement.salesmanagement.exception.ResourceNotFoundException;
import com.salesmanagement.salesmanagement.model.Product;
import com.salesmanagement.salesmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;


    //getAll
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //getById
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
        return ResponseEntity.ok().body(product);
    }

    //Post
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/products")
    public Product createProduct(@Validated @RequestBody Product product) {
        return productRepository.save(product);
    }

    //Put
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long id,
                                               @Validated @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
        product.setProductName(productDetails.getProductName());
        product.setCategory(productDetails.getCategory());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    //Delete
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
