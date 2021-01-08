package ru.shakurov.querydsl.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "dish")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dish {

    @Id
    private String _id;

    @Indexed(unique = true)
    private String name;

    private Integer price;

    @DBRef
    private List<Product> products;
}
