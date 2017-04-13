package com.example.devops.repository;

import com.example.devops.entity.User;
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
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllTest() {
        List<User> users = userRepository.findAll();
        Assert.assertTrue(users.size() > 0);
    }
}
