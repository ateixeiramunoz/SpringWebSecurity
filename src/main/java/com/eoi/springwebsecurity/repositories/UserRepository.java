package com.eoi.springwebsecurity.repositories;
import com.eoi.springwebsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}