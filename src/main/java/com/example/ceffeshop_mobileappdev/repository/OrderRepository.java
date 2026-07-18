package com.example.ceffeshop_mobileappdev.repository;

import com.example.ceffeshop_mobileappdev.entity.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    List<Order> findByCustomerID_Id(Integer customerId, Sort sort);
}
