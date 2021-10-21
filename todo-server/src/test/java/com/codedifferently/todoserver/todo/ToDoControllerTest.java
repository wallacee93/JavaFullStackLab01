package com.codedifferently.todoserver.todo;

import net.minidev.json.JSONUtil;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ToDoController.class)
public class ToDoControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private ToDoService toDoService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        // creating a clean container to put data in
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getAllToDosTest() throws Exception {

        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(ToDo.builder("Study Core Java"));
        toDoList.add(ToDo.builder("Practice Code Problems"));
        toDoList.add(ToDo.builder("Sleep , Sleep, Sleep"));

        BDDMockito.given(toDoService.getAll()).willReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/todos/allToDos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void createToDosTest() throws Exception{

        ToDo toDo = ToDo.builder("Brush Up On Spring");

        BDDMockito.given(toDoService.create(Mockito.anyString())).willReturn(toDo);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/todos/createToDo")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJson(todo)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text", CoreMatchers.is("Look more into SpringBoot")));
    }

    @Test
    public void updateToDoTest() throws Exception{

        ToDo toDo = ToDo.builder("Watch Videos");

        BDDMockito.given(toDoService.toggleComplete(toDo.getId())).willReturn(toDo));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/todos/all/updateToDo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJson(toDo)))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    public void deleteToDoTest() throws Exception{

        ToDo toDo = ToDo.builder("Read Chapter One");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/todos/all/deleteToDo"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn();
    }




}
