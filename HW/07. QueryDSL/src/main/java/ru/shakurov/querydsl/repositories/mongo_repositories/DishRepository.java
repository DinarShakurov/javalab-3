package ru.shakurov.querydsl.repositories.mongo_repositories;

import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.shakurov.querydsl.domain.Dish;
import ru.shakurov.querydsl.domain.QDish;

public interface DishRepository extends MongoRepository<Dish, String>, QuerydslPredicateExecutor<Dish>, QuerydslBinderCustomizer<QDish> {

    @Override
    default void customize(QuerydslBindings bindings, QDish qDish) {
        bindings.bind(qDish.name).first(StringExpression::containsIgnoreCase);
        bindings.bind(qDish.price).first(NumberExpression::loe);
    }
}
