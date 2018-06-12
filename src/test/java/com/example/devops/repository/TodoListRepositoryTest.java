package com.example.devops.repository;

import com.example.devops.entity.TodoList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"hsqldb"})
public class TodoListRepositoryTest {
    @Autowired
    private TodoListRepository todoListRepository;

    @Test
    public void findAllTest() {
        List<TodoList> todoLists = todoListRepository.findAll();
        Assert.assertTrue(todoLists.size() > 0);
    }
}
