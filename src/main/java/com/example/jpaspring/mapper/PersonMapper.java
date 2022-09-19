package com.example.jpaspring.mapper;

import com.example.jpaspring.entity.PersonEntity;
import com.example.jpaspring.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
   public  PersonEntity personToEntity(Person person);
    public  Person entityToPerson(PersonEntity personEntity);

    public List <Person> entityToPersons(List<PersonEntity> personEntity);


}
