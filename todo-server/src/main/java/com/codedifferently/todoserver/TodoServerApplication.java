package com.codedifferently.todoserver;

import com.codedifferently.todoserver.todo.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoServerApplication implements CommandLineRunner {

    @Autowired
    ToDoService service;

    public static void main(String[] args) {
        SpringApplication.run(TodoServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.create("My First Task");
    }
}
