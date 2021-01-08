package ru.shakurov.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.shakurov.querydsl.domain.Dish;
import ru.shakurov.querydsl.domain.Menu;
import ru.shakurov.querydsl.domain.Product;
import ru.shakurov.querydsl.repositories.mongo_repositories.DishRepository;
import ru.shakurov.querydsl.repositories.mongo_repositories.MenuRepository;
import ru.shakurov.querydsl.repositories.mongo_repositories.ProductRepository;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class MongoQuerydslApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MongoQuerydslApplication.class, args);

        ProductRepository productRepository = context.getBean(ProductRepository.class);
        DishRepository dishRepository = context.getBean(DishRepository.class);
        MenuRepository menuRepository = context.getBean(MenuRepository.class);

        menuRepository.deleteAll();
        dishRepository.deleteAll();
        productRepository.deleteAll();

        Product potato = Product.builder()
                .name("potato")
                .energyValue(Product.EnergyValue.builder()
                        .carbohydrates(12)
                        .fats(15)
                        .proteins(25)
                        .build())
                .build();
        Product carrot = Product.builder()
                .name("carrot")
                .energyValue(Product.EnergyValue.builder()
                        .carbohydrates(25)
                        .fats(7)
                        .proteins(15)
                        .build())
                .build();
        Product tomato = Product.builder()
                .name("tomato")
                .energyValue(Product.EnergyValue.builder()
                        .carbohydrates(7)
                        .fats(15)
                        .proteins(16)
                        .build())
                .build();
        Product onion = Product.builder()
                .name("onion")
                .energyValue(Product.EnergyValue.builder()
                        .carbohydrates(20)
                        .fats(5)
                        .proteins(5)
                        .build())
                .build();
        Product pepper = Product.builder()
                .name("pepper")
                .energyValue(Product.EnergyValue.builder()
                        .carbohydrates(5)
                        .fats(35)
                        .proteins(15)
                        .build())
                .build();

        Dish dish1 = Dish.builder()
                .name("dish #1")
                .price(100)
                .products(Arrays.asList(potato, carrot, tomato))
                .build();
        Dish dish2 = Dish.builder()
                .name("dish #2")
                .price(150)
                .products(Collections.singletonList(potato))
                .build();
        Dish dish3 = Dish.builder()
                .name("dish #3")
                .price(200)
                .products(Arrays.asList(pepper, tomato, carrot))
                .build();
        Dish dish4 = Dish.builder()
                .name("dish #4")
                .price(250)
                .products(Arrays.asList(tomato, onion))
                .build();

        Menu menu1 = Menu.builder()
                .name("menu #1")
                .dishes(Arrays.asList(dish1, dish2))
                .build();
        Menu menu2 = Menu.builder()
                .name("menu #2")
                .dishes(Arrays.asList(dish3, dish4))
                .build();

        productRepository.saveAll(Arrays.asList(potato, tomato, pepper, carrot, onion));
        dishRepository.saveAll(Arrays.asList(dish1, dish2, dish3, dish4));
        menuRepository.saveAll(Arrays.asList(menu1, menu2));

    }

}
