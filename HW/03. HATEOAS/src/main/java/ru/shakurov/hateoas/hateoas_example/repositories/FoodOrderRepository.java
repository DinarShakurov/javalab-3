package ru.shakurov.hateoas.hateoas_example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shakurov.hateoas.hateoas_example.domain.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
}
