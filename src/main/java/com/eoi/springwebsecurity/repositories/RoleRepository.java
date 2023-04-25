package com.eoi.springwebsecurity.repositories;

import com.eoi.springwebsecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}