package com.salesmanagement.salesmanagement.controller;

import com.salesmanagement.salesmanagement.exception.ResourceNotFoundException;
import com.salesmanagement.salesmanagement.model.Client;
import com.salesmanagement.salesmanagement.model.ClientContact;
import com.salesmanagement.salesmanagement.repository.ClientContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class ClientContactController {

    @Autowired
    private ClientContactRepository clientContactRepository;


    //getAll
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/client_contacts")
    public List<ClientContact> getAllClientContacts(){
        return clientContactRepository.findAll();
    }


    //getById
    @GetMapping("/client_contacts/{id}")
    public ResponseEntity<ClientContact> getClientContactById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        ClientContact clientContact = clientContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client contact not found for this id :: " + id));
        return ResponseEntity.ok().body(clientContact);
    }



    //Post
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/client_contacts")
    public ClientContact createClientContact(@Validated @RequestBody ClientContact clientContact) {
        return clientContactRepository.save(clientContact);
    }

    //Put
    @PutMapping("/client_contacts/{id}")
    public ResponseEntity<ClientContact> updateClientContact(@PathVariable(value = "id") Long id,
                                               @Validated @RequestBody ClientContact clientContactDetails) throws ResourceNotFoundException {
        ClientContact clientContact = clientContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client contact not found for this id :: " + id));
        clientContact.setFirstName(clientContactDetails.getFirstName());
        clientContact.setLastName(clientContactDetails.getLastName());
        clientContact.setPhone(clientContactDetails.getPhone());
        clientContact.setEmail(clientContactDetails.getEmail());
        clientContact.setDepartment(clientContactDetails.getDepartment());
        final ClientContact updatedClientContact = clientContactRepository.save(clientContact);
        return ResponseEntity.ok(updatedClientContact);
    }

    //Delete
    @DeleteMapping("/client_contacts/{id}")
    public Map<String, Boolean> deleteClientContact(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        ClientContact clientContact = clientContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client contact not found for this id :: " + id));
        clientContactRepository.delete(clientContact);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
