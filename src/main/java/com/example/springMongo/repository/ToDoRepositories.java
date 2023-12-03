package com.example.springMongo.repository;

import com.example.springMongo.model.ToDoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepositories extends MongoRepository <ToDoDTO, String>{
}
