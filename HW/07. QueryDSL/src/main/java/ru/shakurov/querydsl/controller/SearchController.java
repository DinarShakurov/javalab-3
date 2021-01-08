package ru.shakurov.querydsl.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shakurov.querydsl.domain.Dish;
import ru.shakurov.querydsl.domain.Product;
import ru.shakurov.querydsl.dto.DishDto;
import ru.shakurov.querydsl.dto.DishRequest;
import ru.shakurov.querydsl.repositories.mongo_repositories.DishRepository;
import ru.shakurov.querydsl.repositories.request_repositories.DishByRequestRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {

    @Autowired
    private DishByRequestRepository dishByRequestRepository;

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/dishes/search/byRequest")
    public ResponseEntity<List<DishDto>> searchByRequest(DishRequest dishRequest) {
        List<DishDto> result = dishByRequestRepository.findByRequest(dishRequest);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/dishes/search")
    public ResponseEntity<List<DishDto>> searchByPredicate(@QuerydslPredicate(root = Dish.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(dishRepository.findAll(predicate).spliterator(), true)
                        .map(dish -> DishDto.builder()
                                .name(dish.getName())
                                .price(dish.getPrice())
                                .productNames(dish.getProducts().stream().map(Product::getName).collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList())
        );
    }
}
