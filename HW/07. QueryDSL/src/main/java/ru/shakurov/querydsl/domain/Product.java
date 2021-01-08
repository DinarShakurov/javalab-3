package ru.shakurov.querydsl.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String _id;

    @Indexed(unique = true)
    private String name;

    private EnergyValue energyValue;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class EnergyValue {
        private Integer proteins;
        private Integer fats;
        private Integer carbohydrates;
    }
}
