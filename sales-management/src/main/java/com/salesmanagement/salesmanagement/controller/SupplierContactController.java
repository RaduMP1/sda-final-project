package com.salesmanagement.salesmanagement.controller;

import com.salesmanagement.salesmanagement.exception.ResourceNotFoundException;
import com.salesmanagement.salesmanagement.model.ClientContact;
import com.salesmanagement.salesmanagement.model.Supplier;
import com.salesmanagement.salesmanagement.model.SupplierContact;
import com.salesmanagement.salesmanagement.repository.SupplierContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class SupplierContactController {

    @Autowired
    private SupplierContactRepository supplierContactRepository;


    //getAll
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/supplier_contacts")
    public List<SupplierContact> getAllSupplierContacts(){
        return supplierContactRepository.findAll();
    }


    //getById
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/supplier_contacts/{id}")
    public ResponseEntity<SupplierContact> getSupplierContactById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        SupplierContact supplierContact = supplierContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier contact not found for this id :: " + id));
        return ResponseEntity.ok().body(supplierContact);
    }



    //Post
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/supplier_contacts")
    public SupplierContact createSupplierContact(@Validated @RequestBody SupplierContact supplierContact) {
        return supplierContactRepository.save(supplierContact);
    }

    //Put
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/supplier_contacts/{id}")
    public ResponseEntity<SupplierContact> updateSupplierContact(@PathVariable(value = "id") Long id,
                                                             @Validated @RequestBody SupplierContact supplierContactDetails) throws ResourceNotFoundException {
        SupplierContact supplierContact = supplierContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier contact not found for this id :: " + id));
        supplierContact.setFirstName(supplierContactDetails.getFirstName());
        supplierContact.setLastName(supplierContactDetails.getLastName());
        supplierContact.setPhone(supplierContactDetails.getPhone());
        supplierContact.setEmail(supplierContactDetails.getEmail());
        supplierContact.setDepartment(supplierContactDetails.getDepartment());
        final SupplierContact updatedSupplierContact = supplierContactRepository.save(supplierContact);
        return ResponseEntity.ok(updatedSupplierContact);
    }

    //Delete
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/supplier_contacts/{id}")
    public Map<String, Boolean> deleteSupplierContact(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        SupplierContact supplierContact = supplierContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier contact not found for this id :: " + id));
        supplierContactRepository.delete(supplierContact);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
