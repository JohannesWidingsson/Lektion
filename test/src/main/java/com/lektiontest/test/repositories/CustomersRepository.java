package com.lektiontest.test.repositories;

import com.lektiontest.test.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository <Customers, Integer>  {
    
}
