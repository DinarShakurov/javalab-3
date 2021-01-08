package ru.shakurov.querydsl.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DishDto {
    private String name;
    private Integer price;
    private List<String> productNames;
}
