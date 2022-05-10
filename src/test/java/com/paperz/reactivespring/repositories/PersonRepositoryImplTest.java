package com.paperz.reactivespring.repositories;

import com.paperz.reactivespring.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    void getByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);

        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();

        personMono.block();
    }

    @Test
    void getByIdSubscribe() {
        Mono<Person> personMono = personRepository.getById(1);

        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();

        personMono.subscribe(
                person -> {
                    System.out.println(person.toString());
                });
    }

    @Test
    void getByIdSubscribeNotFound() {
        Mono<Person> personMono = personRepository.getById(-1);

        StepVerifier.create(personMono).expectNextCount(0).verifyComplete();

        personMono.subscribe(
                person -> {
                    System.out.println(person.toString());
                });
    }

    @Test
    void testFluxSubscribe() {
        Flux<Person> personFlux = personRepository.findAll();

        StepVerifier.create(personFlux).expectNextCount(3).verifyComplete();

        personFlux.subscribe();
    }
}
