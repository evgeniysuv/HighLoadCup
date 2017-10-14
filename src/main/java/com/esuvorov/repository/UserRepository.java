package com.esuvorov.repository;

import com.esuvorov.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findUsersByLastName(String lastName);

}
