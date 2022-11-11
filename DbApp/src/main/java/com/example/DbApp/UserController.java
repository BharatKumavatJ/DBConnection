package com.example.DbApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    DBManager dbManager;
    @PostMapping("/add-user")
    public void addUser(@RequestBody() User user) throws SQLException {
        System.out.printf(user.getName() + " " + user.getAge());
        dbManager.insertInfo(user);
    }

    @GetMapping("/get-users")
    public List<String> getAllUsers() throws SQLException {
        List<String> allUsersList = new ArrayList<>();
        allUsersList =  dbManager.getInfo();
        return allUsersList;
    }

    @PutMapping("/update-name")
    public void updateName(@RequestParam("name") String name) throws SQLException {
        dbManager.updateUserName(name);
    }
}
