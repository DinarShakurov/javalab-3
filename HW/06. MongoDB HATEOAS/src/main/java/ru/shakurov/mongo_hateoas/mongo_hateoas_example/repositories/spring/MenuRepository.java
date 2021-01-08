package ru.shakurov.mongo_hateoas.mongo_hateoas_example.repositories.spring;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.shakurov.mongo_hateoas.mongo_hateoas_example.domain.Dish;
import ru.shakurov.mongo_hateoas.mongo_hateoas_example.domain.Menu;

import java.util.List;

public interface MenuRepository extends MongoRepository<Menu, String> {

    @RestResource(path = "containsDish", rel = "containsDish")
    @Query(value = "{ dishes: ?dish }")
    List<Menu> find(@Param("dish") Dish dish);
}
