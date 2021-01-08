package ru.shakurov.hateoas.hateoas_example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shakurov.hateoas.hateoas_example.domain.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
