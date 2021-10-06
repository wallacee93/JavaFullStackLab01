package com.codedifferently.todoserver.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/todos")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    //Todo: Create a Get Method that returns all items with a status code of 200

    //Todo: Create a POST Method that creates an item with a status code of 201

    //Todo: Create a PUT Method that updates a status code with 204


    //Todo: Create a DELETE method that removes the item and returns a status code of 202


}
