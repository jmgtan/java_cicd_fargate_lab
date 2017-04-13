package com.example.devops.repository;

import com.example.devops.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {
    public List<User> findAll();
}
