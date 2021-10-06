package com.codedifferently.todoserver.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ToDoService {
    private static final Logger logger = LoggerFactory.getLogger(ToDoService.class);
    private ArrayList<ToDo> todos;

    public ToDoService(){
        this.todos = new ArrayList<>();

    }

    public ToDo create(String text){
        ToDo todo = ToDo.builder(text);
        logger.info("Created item {} with id {}", todo.getText(), todo.getId());
        todos.add(todo);
        return todo;
    }

    public ArrayList<ToDo> getAll(){
        return todos;
    }

    public void remove(Integer id){
        int index = getById(id);
        ToDo todo = todos.get(index);
        logger.info("Removed item {}", todo.getText());
        todos.remove(index);
    }

    public void toggleComplete(Integer id){
        int index = getById(id);
        ToDo item = todos.get(index);
        Boolean status = (item.getIsDone())?false:true;
        logger.info("Changing {} status to {}", item.getText(), item.getIsDone());
        item.setIsDone(status);
    }

    private int getById(Integer id){
        for(int x = 0; x < todos.size(); x++){
            ToDo item = todos.get(x);
            if(item.getId() == id){
                return x;
            }
        }
        return -1;
    }
}
