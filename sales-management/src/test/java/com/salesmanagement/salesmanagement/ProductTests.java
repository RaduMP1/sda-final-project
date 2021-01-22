package com.salesmanagement.salesmanagement;

import com.salesmanagement.salesmanagement.model.Product;
import com.salesmanagement.salesmanagement.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTests {

    @Autowired
    private ProductRepository repo;

    @Test
    public void testCreateProduct(){
        Product product = new Product("Sony","TV");
        repo.save(product);
    }
}
