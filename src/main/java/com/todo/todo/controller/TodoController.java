package com.todo.todo.controller;

import java.util.List;

import com.todo.todo.model.Todo;
import com.todo.todo.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Todo> listTodo = todoService.findAllTodo();
        model.addAttribute("listTodo", listTodo);
        return "index";
    }

    @GetMapping("/new")
    public String addTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTodo(@ModelAttribute("todo") Todo todo) {
        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditTodoPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Todo todo = todoService.getTodoById(id);
        mav.addObject("todo", todo);
        return mav;

    }

    @RequestMapping("/delete/{id}")
    public String deleteTodo(@PathVariable(name = "id") int id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }
}
