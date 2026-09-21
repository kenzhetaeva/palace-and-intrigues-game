package com.example.demo.repositories;

import com.example.demo.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

    // Готовые методы из коробки:
    // heroRepository.save(hero)       -> Создать или обновить
    // heroRepository.findById(id)     -> Найти по ID
    // heroRepository.delete(hero)     -> Удалить
    // heroRepository.findAll()        -> Получить всех

    // Можно создавать свои запросы просто по имени метода!
    Hero findByName(String name);
}