package com.example.devops.repository;

import com.example.devops.entity.TodoItem;
import com.example.devops.entity.TodoList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"hsqldb"})
public class TodoItemRepositoryTest {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    @Test
    @Transactional
    public void findAllByTodoListTest() {
        List<TodoList> todoLists = todoListRepository.findAll();
        Assert.assertTrue(todoLists.size() > 0);

        List<TodoItem> todoItems = todoItemRepository.findAllByTodoList(todoLists.get(0));
        Assert.assertTrue(todoItems.size() > 0);
    }
}
