package com.salesmanagement.salesmanagement;

import com.salesmanagement.salesmanagement.model.Product;
import com.salesmanagement.salesmanagement.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTests {

    @Autowired
    private ProductRepository repo;

    @Test
    public void testCreateProduct(){
        Product product = new Product("Sony","TV");
        Product savedProduct = repo.save(product);

        assertNotNull(savedProduct);
    }

    @Test
    public void testFindProductByName(){
        String name = "Adidas";
        Product product = repo.findByProductName(name);

        assertThat(product.getProductName()).isEqualTo(name);
    }

    @Test
    public void testDoesNotFindProductByName(){
        String name = "Reebok";
        Product product = repo.findByProductName(name);

        assertNull(product);
    }


    @Test
    public void testUpdateProduct(){
        String productName = "Adidas";
        Product product = new Product("ssss", "shoes");
        product.setId(3L);

        repo.save(product);
        Product updatedProduct = repo.findByProductName(productName);

        assertThat(updatedProduct.getProductName()).isEqualTo(productName);
    }

    @Test
    public void testListProducts(){
        List<Product> products = (List<Product>) repo.findAll();

        assertThat(products).size().isGreaterThan(0);
    }

    @Test
    public void testDeleteProduct(){
        Long id = 2L;
        boolean existsBeforeDelete = repo.findById(id).isPresent();
        repo.deleteById(id);
        boolean doesNotExistAfterDelete = repo.findById(id).isPresent();

        assertTrue(existsBeforeDelete);
        assertFalse(doesNotExistAfterDelete);
    }

}
