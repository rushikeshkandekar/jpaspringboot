package com.example.jpaspring.controller;

import com.example.jpaspring.model.Person;
import com.example.jpaspring.model.PersonResponse;
import com.example.jpaspring.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(path = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
        PersonResponse personResponce = personService.createPerson(person);
        return new ResponseEntity<>(personResponce, HttpStatus.OK);

    }
    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllperson(){
        List<Person> personList = personService.getAllperson();
        return new ResponseEntity<>(personList,HttpStatus.OK);
    }

    @GetMapping(path = "/persons/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable Long personId) {
        Person person = personService.getPerson(personId);
        if (Objects.isNull(person)) {
            log.info("person id" + personId + "found");
        } else {
            ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    @PutMapping(path ="/persons/{personId}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable Long personId,@RequestBody Person person){
        personService.updatePerson(personId,person);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(path = "/persons/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.ok().build();
    }
}
