package com.example.sportshopv2.repository;

import com.example.sportshopv2.model.HoaDon;
import com.example.sportshopv2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NhanVienRepo extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u JOIN u.account a LEFT JOIN FETCH u.addresses  WHERE  a.role = 'Staff' AND u.deleted = false")
    Page<User> findAllEmp(Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.account a LEFT JOIN FETCH u.addresses WHERE a.role = 'Staff' AND " +
            "(u.fullName LIKE %:keyword% OR u.phoneNumber LIKE %:keyword% OR u.email LIKE %:keyword%)")
    Page<User> searchEmp(@Param("keyword") String keyword, Pageable pageable);
    @Query("SELECT u "
            + "FROM User u "
            + "JOIN u.account a "
            + "WHERE u.id = :userId AND a.role = 'Staff'")
    User getNhanVienById(@Param("userId") Integer userId);
}
