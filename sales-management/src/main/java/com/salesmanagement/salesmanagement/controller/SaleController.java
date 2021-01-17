package com.salesmanagement.salesmanagement.controller;

import com.salesmanagement.salesmanagement.exception.ResourceNotFoundException;
import com.salesmanagement.salesmanagement.model.Product;
import com.salesmanagement.salesmanagement.model.Sale;
import com.salesmanagement.salesmanagement.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    //getAll
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/sales")
    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    //getById
    @GetMapping("/sales/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found for this id :: " + id));
        return ResponseEntity.ok().body(sale);
    }

    //Post
    @PostMapping("/sales")
    public Sale createSale(@Validated @RequestBody Sale sale) {
        return saleRepository.save(sale);
    }

    //Put
    @PutMapping("/sales/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable(value = "id") Long id,
                                                 @Validated @RequestBody Sale saleDetails) throws ResourceNotFoundException {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found for this id :: " + id));
        sale.setSalesName(saleDetails.getSalesName());
        sale.setSalesData(saleDetails.getSalesData());
        sale.setStatus(saleDetails.getStatus());
        sale.setObservation(saleDetails.getObservation());
        final Sale updatedSale = saleRepository.save(sale);
        return ResponseEntity.ok(updatedSale);
    }

    //Delete
    @DeleteMapping("/sales/{id}")
    public Map<String, Boolean> deleteSale(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found for this id :: " + id));
        saleRepository.delete(sale);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
