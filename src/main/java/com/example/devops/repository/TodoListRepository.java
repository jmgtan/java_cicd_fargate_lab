package com.example.devops.repository;

import com.example.devops.entity.TodoList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoListRepository extends CrudRepository<TodoList, Integer> {
    public List<TodoList> findAll();
}
