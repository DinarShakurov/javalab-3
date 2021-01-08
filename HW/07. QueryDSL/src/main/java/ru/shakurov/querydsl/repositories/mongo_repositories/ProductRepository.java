package ru.shakurov.querydsl.repositories.mongo_repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.shakurov.querydsl.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
