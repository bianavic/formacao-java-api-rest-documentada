package org.edu.formacaojavaapirestdocumentada.controller;

import org.edu.formacaojavaapirestdocumentada.model.User;
import org.edu.formacaojavaapirestdocumentada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping()
    public void post(@RequestBody User user){
        repository.save(user);
    }
    @PutMapping()
    public void put(@RequestBody User user){
        repository.update(user);
    }
    @GetMapping()
    public List<User> getAll(){
        return repository.listAll();
    }
    @GetMapping("/{id}")
    public User getOne(@PathVariable("id") Integer id){
        return repository.finById(id);
    }
    @GetMapping("/user/{username}")
    public User getByName(@PathVariable String username) {
        return repository.findByName(username);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        repository.remove(id);
    }

}
