package com.ivoyant.payment_service.repository;

import com.ivoyant.payment_service.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

}
