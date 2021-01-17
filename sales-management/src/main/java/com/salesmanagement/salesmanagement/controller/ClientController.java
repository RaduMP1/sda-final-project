package com.salesmanagement.salesmanagement.controller;

import com.salesmanagement.salesmanagement.exception.ResourceNotFoundException;
import com.salesmanagement.salesmanagement.model.Client;
import com.salesmanagement.salesmanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;


    //getAll
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }


    //getById
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));
        return ResponseEntity.ok().body(client);
    }
    


    //Post
    @PostMapping("/clients")
    public Client createClient(@Validated @RequestBody Client client) {
        return clientRepository.save(client);
    }

    //Put
    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long id,
                                             @Validated @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));
        client.setClientName(clientDetails.getClientName());
        client.setAddress(clientDetails.getAddress());
        client.setPhone(clientDetails.getPhone());
        client.setEmail(clientDetails.getEmail());
        final Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    //Delete
    @DeleteMapping("/clients/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));
        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
