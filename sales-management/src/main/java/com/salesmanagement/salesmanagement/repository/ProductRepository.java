package com.salesmanagement.salesmanagement.repository;

import com.salesmanagement.salesmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    public Product findByProductName(String productName); // added for ProductTests class

}
