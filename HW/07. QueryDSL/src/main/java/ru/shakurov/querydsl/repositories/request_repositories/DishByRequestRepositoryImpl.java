package ru.shakurov.querydsl.repositories.request_repositories;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;
import ru.shakurov.querydsl.domain.Dish;
import ru.shakurov.querydsl.dto.DishDto;
import ru.shakurov.querydsl.dto.DishRequest;

import java.util.List;
import java.util.stream.Collectors;

import static ru.shakurov.querydsl.domain.QDish.dish;

@Repository
public class DishByRequestRepositoryImpl implements DishByRequestRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<DishDto> findByRequest(DishRequest dishRequest) {
        BooleanBuilder predicate = new BooleanBuilder();
// Так делать нельзя
// org.springframework.data.mapping.MappingException: Invalid path reference products.name! Associations can only be pointed to directly or via their id property!
//        if (dishRequest.getProductName() != null) {
//            predicate.or(dish.products.any().name.containsIgnoreCase(dishRequest.getProductName()));
//        }
        if (dishRequest.getPrice() != null) {
            predicate.or(dish.price.eq(dishRequest.getPrice()));
        }
        if (dishRequest.getName() != null) {
            predicate.or(dish.name.containsIgnoreCase(dishRequest.getName()));
        }

        return new SpringDataMongodbQuery<>(mongoTemplate, Dish.class)
                .where(predicate)
                .fetch()
                .stream()
                .map(dish -> DishDto.builder()
                        .name(dish.getName())
                        .price(dish.getPrice())
//                        .productNames(row.get(dish.products)
//                                .stream()
//                                .map(Product::getName)
//                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
