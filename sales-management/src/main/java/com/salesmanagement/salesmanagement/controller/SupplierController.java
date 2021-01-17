package com.salesmanagement.salesmanagement.controller;

import com.salesmanagement.salesmanagement.exception.ResourceNotFoundException;
import com.salesmanagement.salesmanagement.model.Sale;
import com.salesmanagement.salesmanagement.model.Supplier;
import com.salesmanagement.salesmanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class SupplierController {
    
   @Autowired 
   private SupplierRepository supplierRepository;


    //getAll
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }

    //getById
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found for this id :: " + id));
        return ResponseEntity.ok().body(supplier);
    }

    //Post
    @PostMapping("/suppliers")
    public Supplier createSupplier(@Validated @RequestBody Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    //Put
    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable(value = "id") Long id,
                                           @Validated @RequestBody Supplier supplierDetails) throws ResourceNotFoundException {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found for this id :: " + id));
        supplier.setSupplierName(supplierDetails.getSupplierName());
        supplier.setAddress(supplierDetails.getAddress());
        supplier.setPhone(supplierDetails.getPhone());
        supplier.setEmail(supplierDetails.getEmail());
        final Supplier updatedSupplier = supplierRepository.save(supplier);
        return ResponseEntity.ok(updatedSupplier);
    }

    //Delete
    @DeleteMapping("/suppliers/{id}")
    public Map<String, Boolean> deleteSupplier(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found for this id :: " + id));
        supplierRepository.delete(supplier);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
