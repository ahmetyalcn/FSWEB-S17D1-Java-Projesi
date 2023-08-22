package com.workintech.getstarted.controller;

import com.workintech.getstarted.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    @Value("${instructor.name}")
    private String name;
    @GetMapping("/merhaba")
    public String sayHi(){
        return name+" selam";
    }
    private Map<Integer, Animal> animals;
    @PostConstruct
    public void init(){
        animals = new HashMap<>();
    }

    @GetMapping("/")
    public List<Animal> get(){
        return animals.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Animal get(@PathVariable int id){
        return animals.get(id);
    }

    @PostMapping("/")
    public Animal save(@RequestBody Animal animal){
        animals.put(animal.getId(), animal);
        return animals.get(animal.getId());
    }
    @PutMapping("/{id}")
    public Animal update(@PathVariable int id, @RequestBody Animal animal){
        animals.put(id, animal);
        return animals.get(id);
    }
    @DeleteMapping("/{id}")
    public Animal update(@PathVariable int id){
        Animal foundAnimal = animals.get(id);
        animals.remove(id);
        return foundAnimal;
    }
}
