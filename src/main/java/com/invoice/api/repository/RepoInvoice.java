package com.invoice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invoice.api.entity.Invoice;

@Repository
public interface RepoInvoice extends JpaRepository<Invoice, Integer> {

	@Query(value="SELECT * FROM invoice WHERE invoice_id = :invoice_id",nativeQuery=true) 
    List<Invoice> findCarrito(Integer invoice_id);
	
	@Query(value="SELECT * FROM invoice WHERE user_id = :user_id",nativeQuery=true) 
    List<Invoice> findByUser_Id(Integer user_id);
}
