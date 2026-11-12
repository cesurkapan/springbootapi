package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    public enum GENDER{MALE, FEMALE};
    public enum SortingOrder{ASC, DESC};

    public AtomicInteger idCounter = new AtomicInteger(0);


    public record Person(int id, String name, GENDER gender){

    }

    public static List<Person> people = new ArrayList<>();

    static {
        people.add(new Person(1,"Cesur",GENDER.MALE));
        people.add(new Person(2, "Jennifer", GENDER.FEMALE));
        people.add(new Person(3, "Cihan", GENDER.MALE));
        people.add(new Person(4, "Enes", GENDER.MALE));
    }

    @GetMapping
    public List<Person> getPersons(
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") SortingOrder sort,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit){

        if (sort==SortingOrder.ASC){
            return people.stream()
                    .sorted(Comparator.comparing(Person::id))
                    .collect(Collectors.toList());
        }
        return people.stream()
                .sorted(Comparator.comparing(Person::id).reversed()).collect(Collectors.toList());
    }



    @GetMapping("{id}")
    public Optional<Person> getPersonById(
            @PathVariable("id") Integer id)

    {
        return people.stream().
                filter(person -> person.id == id)
                .findFirst();
    }

    @DeleteMapping("{id}")
    public void deletePersonById (@PathVariable("id") Integer id){
        people.removeIf(person -> person.id==id);
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        people.add(new Person(
                idCounter.incrementAndGet(),
                person.name(),
                person.gender()
        ));
    }}
