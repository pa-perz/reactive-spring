package com.paperz.reactivespring.repositories;

import com.paperz.reactivespring.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    private final Person sSnake =
            Person.builder().id(0).firstName("Solid").lastName("Snake").build();
    private final Person wWhite =
            Person.builder().id(1).firstName("Walter").lastName("White").build();
    private final Person jPinkman =
            Person.builder().id(2).firstName("Jesse").lastName("Pinkman").build();

    @Override
    public Mono<Person> getById(Integer id) {

        return findAll().filter(person -> person.getId().equals(id)).next();
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(sSnake, wWhite, jPinkman);
    }
}
