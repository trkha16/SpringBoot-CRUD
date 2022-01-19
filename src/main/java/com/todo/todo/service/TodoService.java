package com.todo.todo.service;

import java.util.List;

import com.todo.todo.model.Todo;
import com.todo.todo.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAllTodo(){
        return todoRepository.findAll();
    }

    public void saveTodo(Todo todo){
        todoRepository.save(todo);
    }

    public Todo getTodoById(long id){
        return todoRepository.findById(id).get();
    }

    public void deleteTodoById(long id){
        todoRepository.deleteById(id);
    }
}
