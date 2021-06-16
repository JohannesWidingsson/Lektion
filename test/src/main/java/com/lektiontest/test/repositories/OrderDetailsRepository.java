package com.lektiontest.test.repositories;

import com.lektiontest.test.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository <OrderDetails, Integer>  {
    
}
