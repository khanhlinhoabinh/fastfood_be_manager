package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findAll(Pageable pageable);

    // Tìm theo customerID
    @Query("SELECT o FROM Order o WHERE o.customer.customerID = :customerID")
    List<Order> findByCustomerID(@Param("customerID") Integer customerID);

    // Tìm theo customerID + status
    @Query("SELECT o FROM Order o WHERE o.customer.customerID = :customerID AND o.status = :status")
    List<Order> findByCustomerIDAndStatus(@Param("customerID") Integer customerID, @Param("status") String status);

    // Tìm theo status
    List<Order> findByStatus(String status);

    // Tìm theo customerID + status + khoảng thời gian
    @Query("SELECT o FROM Order o WHERE o.customer.customerID = :customerID AND o.status = :status AND o.orderDate BETWEEN :start AND :end")
    List<Order> findByCustomerIDAndStatusAndOrderDateBetween(
            @Param("customerID") Integer customerID,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("status") String status
    );

    // Tìm theo customerID + khoảng thời gian
    @Query("SELECT o FROM Order o WHERE o.customer.customerID = :customerID AND o.orderDate BETWEEN :start AND :end")
    List<Order> findByCustomerIDAndOrderDateBetween(
            @Param("customerID") Integer customerID,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    // Tìm theo status + khoảng thời gian
    @Query("SELECT o FROM Order o WHERE o.status = :status AND o.orderDate BETWEEN :start AND :end")
    List<Order> findByStatusAndOrderDateBetween(
            @Param("status") String status,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    // Tìm theo khoảng thời gian
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :start AND :end")
    List<Order> findByOrderDateBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}