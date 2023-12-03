package com.example.springMongo.controller;

import com.example.springMongo.model.ToDoDTO;
import com.example.springMongo.repository.ToDoRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ToDoController {
    @Autowired
     private ToDoRepositories toDoRepositories;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){

     List<ToDoDTO> todos = toDoRepositories.findAll();
     if (todos.size() > 0){
         return new ResponseEntity<List<ToDoDTO>>(todos, HttpStatus.OK);
     }
     return new ResponseEntity<>("No todos available", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createToDo(@RequestBody ToDoDTO todo){
        try {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            toDoRepositories.save(todo) ;
            return new ResponseEntity<ToDoDTO>(todo,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
