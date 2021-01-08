package ru.shakurov.querydsl.repositories.mongo_repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.shakurov.querydsl.domain.Menu;

public interface MenuRepository extends MongoRepository<Menu, String> {
}
