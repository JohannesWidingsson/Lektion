package com.lektiontest.test.repositories;

import com.lektiontest.test.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Orders, Integer>  {
    
}
