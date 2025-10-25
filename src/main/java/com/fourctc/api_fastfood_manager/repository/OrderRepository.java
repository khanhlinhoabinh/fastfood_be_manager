package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findAll(Pageable pageable);
    @Query("SELECT o FROM Order o WHERE o.customer.customerID = :customerID")
    List<Order> findByCustomerID(@Param("customerID") Integer customerID);

    @Query("SELECT o FROM Order o WHERE o.customer.customerID = :customerID AND o.status = :status")
    List<Order> findByCustomerIDAndStatus(@Param("customerID") Integer customerID, @Param("status") String status);

    List<Order> findByCustomer_CustomerID(Integer customerID);
    List<Order> findByStatus(String status);
    List<Order> findByCustomer_CustomerIDAndStatus(Integer customerID, String status);

}
