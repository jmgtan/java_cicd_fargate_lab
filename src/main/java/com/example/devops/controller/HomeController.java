package com.example.devops.controller;

import com.example.devops.controller.entity.TodoItemForm;
import com.example.devops.entity.TodoItem;
import com.example.devops.entity.TodoList;
import com.example.devops.entity.User;
import com.example.devops.repository.TodoItemRepository;
import com.example.devops.repository.TodoListRepository;
import com.example.devops.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @RequestMapping(value={"/home", "/"})
    @Transactional
    public String index(Model model) {
        Map<Integer, List<TodoItem>> todoItemsMap = new HashMap<>();
        List<TodoList> todoLists = todoListRepository.findAll();

        for (TodoList todoList : todoLists) {
            todoItemsMap.put(todoList.getId(), todoItemRepository.findAllByTodoList(todoList));
        }

        model.addAttribute("todoLists", todoLists);
        model.addAttribute("todoItemsMap", todoItemsMap);
        model.addAttribute("todoItemForm", new TodoItemForm());
        return "index";
    }

    @RequestMapping(value = "/create-task-item", method = RequestMethod.POST)
    @Transactional
    public String createTaskItem(@ModelAttribute TodoItemForm todoItemForm) {
        TodoList todoList = todoListRepository.findOne(todoItemForm.getTodoListId());

        TodoItem todoItem = new TodoItem();
        todoItem.setTodoList(todoList);
        todoItem.setDescription(todoItemForm.getDescription());

        todoItemRepository.save(todoItem);

        return "redirect:/home";
    }

    @RequestMapping("/delete-task-item/{todoItemId}")
    @Transactional
    public String deleteTaskItem(@PathVariable("todoItemId") int todoItemId) {
        todoItemRepository.delete(todoItemId);
        return "redirect:/home";
    }
}
