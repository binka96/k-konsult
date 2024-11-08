package com.k_konsult.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k_konsult.Entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>  {

        
}
