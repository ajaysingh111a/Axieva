package com.example.axieva.repository;

import com.example.axieva.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmailId(String username);

    @Query(value = "select u from Users u where u.userType='STUDENT'")
    List<Users> findAllStudents();
}
