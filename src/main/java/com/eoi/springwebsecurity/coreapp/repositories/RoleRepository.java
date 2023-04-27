package com.eoi.springwebsecurity.coreapp.repositories;

import com.eoi.springwebsecurity.coreapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}