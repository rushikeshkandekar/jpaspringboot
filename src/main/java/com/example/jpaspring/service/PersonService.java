package com.example.jpaspring.service;

import com.example.jpaspring.entity.PersonEntity;
import com.example.jpaspring.mapper.PersonMapper;
import com.example.jpaspring.model.Person;
import com.example.jpaspring.model.PersonResponse;
import com.example.jpaspring.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
@Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonResponse createPerson(Person person) {
        PersonEntity personEntity = null;
        personEntity = personMapper.personToEntity(person);
        PersonResponse personResponse = new PersonResponse();
        Long id = (long) (Math.random() * 20);
        personEntity.setPersonId(id);
        personResponse.setPersonId(personEntity.getPersonId());
        personRepository.save(personEntity);
        return personResponse;
    }

    public Person getPerson(Long personId) {
        Person person = new Person();
        Optional<PersonEntity> personEntity = personRepository.findById(personId);
        if (personEntity.isPresent()) {
            person = personMapper.entityToPerson(personEntity.get());

        } else {
            log.info("person id" + personId + "not found");
        }
        return person;
    }

    public List<Person> getAllperson() {
        List<Person> personList = personMapper.entityToPersons(personRepository.findAll());
        return personList;
    }


    public void updatePerson(Long personId, Person person) {

        Optional<PersonEntity> personEntity = personRepository.findById(personId);


        if (personEntity.isPresent()) {
            personEntity = Optional.ofNullable(personRepository.save(personMapper.personToEntity(person)));
        } else {
            log.info("Person id" + personId + "not exist");
        }
    }

    public void deletePerson(Long personId) {
        Optional<PersonEntity> personEntity =personRepository.findById(personId);
        if (personEntity.isPresent()) {
            personRepository.deleteById(personId);
            log.info("person id" + personId + "deleted successfull");
        }
        else {
            log.info("person id"+personId+" not found");

    }


}}
