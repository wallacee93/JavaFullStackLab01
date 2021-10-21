package com.codedifferently.todoserver.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/allToDos")
    public ResponseEntity<List<ToDo>> getAllToDos(){

        HttpStatus status = HttpStatus.OK;

        List<ToDo> todos = toDoService.getAll();

        return new ResponseEntity<List<ToDo>>(status);


    }

    //Todo: Create a POST Method that creates an item with a status code of 201

    @PostMapping("/createToDo")
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo todo){

        HttpStatus status = HttpStatus.CREATED;

        ToDo created = toDoService.create(todo.getText());

        return new ResponseEntity<>(created, status);


    }

    //Todo: Create a PUT Method that updates a status code with 204

    @PutMapping("/updateToDo")
    public ResponseEntity<Void> updateToDo(@PathVariable("id") Integer id, @RequestBody ToDo toDo){

        HttpStatus status = HttpStatus.NO_CONTENT;

        toDoService.toggleComplete(id);

        return new ResponseEntity<Void>(status);

    }

    //Todo: Create a DELETE method that removes the item and returns a status code of 202

    @DeleteMapping("/deleteToDo")
    public ResponseEntity<ToDo> deleteToDoById(@PathVariable("id") Integer id){

        HttpStatus status = HttpStatus.ACCEPTED;

        toDoService.remove(id);

        return new ResponseEntity<>(status);


    }

}
