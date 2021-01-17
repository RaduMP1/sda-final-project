package com.salesmanagement.salesmanagement.repository;

import com.salesmanagement.salesmanagement.model.ClientContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientContactRepository extends JpaRepository<ClientContact, Long> {

}
