package com.example.exercise46.Controller;

import com.example.exercise46.Repository.PersonRepository;
import com.example.exercise46.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class MainController {
    @Autowired
    private PersonRepository repository;
    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }
    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }
    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }
    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        if (status == HttpStatus.OK){
            person.setId(id);
            return new ResponseEntity(repository.save(person), status);
        }
        else
            return new ResponseEntity(repository.save(person), status);
    }
    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }
}
