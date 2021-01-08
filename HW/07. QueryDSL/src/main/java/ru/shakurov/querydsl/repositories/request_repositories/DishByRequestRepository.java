package ru.shakurov.querydsl.repositories.request_repositories;

import ru.shakurov.querydsl.dto.DishDto;
import ru.shakurov.querydsl.dto.DishRequest;

import java.util.List;

public interface DishByRequestRepository {
    List<DishDto> findByRequest(DishRequest dishRequest);
}
