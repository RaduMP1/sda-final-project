package com.salesmanagement.salesmanagement.repository;

import com.salesmanagement.salesmanagement.model.SupplierContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierContactRepository extends JpaRepository<SupplierContact, Long> {

}
