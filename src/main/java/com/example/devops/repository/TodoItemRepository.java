package com.example.devops.repository;

import com.example.devops.entity.TodoItem;
import com.example.devops.entity.TodoList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {
    public List<TodoItem> findAllByTodoList(TodoList todoList);
}
