package com.eoi.springwebsecurity.coreapp.repositories;

import com.eoi.springwebsecurity.coreapp.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPagingRepository extends PagingAndSortingRepository<User, Long> {



}