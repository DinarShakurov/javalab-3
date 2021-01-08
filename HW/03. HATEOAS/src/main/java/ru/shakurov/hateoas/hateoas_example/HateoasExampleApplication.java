package ru.shakurov.hateoas.hateoas_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.shakurov.hateoas.hateoas_example.domain.Account;
import ru.shakurov.hateoas.hateoas_example.domain.Cafe;
import ru.shakurov.hateoas.hateoas_example.domain.Dish;
import ru.shakurov.hateoas.hateoas_example.domain.FoodOrder;
import ru.shakurov.hateoas.hateoas_example.domain.Payment;
import ru.shakurov.hateoas.hateoas_example.repositories.AccountRepository;
import ru.shakurov.hateoas.hateoas_example.repositories.CafeRepository;
import ru.shakurov.hateoas.hateoas_example.repositories.DishRepository;
import ru.shakurov.hateoas.hateoas_example.repositories.FoodOrderRepository;
import ru.shakurov.hateoas.hateoas_example.repositories.PaymentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class HateoasExampleApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasExampleApplication.class, args);

        AccountRepository accountRepository = context.getBean(AccountRepository.class);
        CafeRepository cafeRepository = context.getBean(CafeRepository.class);
        DishRepository dishRepository = context.getBean(DishRepository.class);
        FoodOrderRepository foodOrderRepository = context.getBean(FoodOrderRepository.class);
        PaymentRepository paymentRepository = context.getBean(PaymentRepository.class);

        Account account = Account.builder()
                .login("dinar")
                .money(1_000_000L)
                .build();
        accountRepository.save(account);

        Cafe cafe1 = Cafe.builder()
                .name("Cafe #1")
                .build();

        Cafe cafe2 = Cafe.builder()
                .name("Cafe #2")
                .build();

        cafeRepository.saveAll(Arrays.asList(
                cafe1, cafe2
        ));

        Dish dish1 = Dish.builder()
                .name("dish #1")
                .price(100L)
                .cafe(cafe1)
                .build();

        Dish dish2 = Dish.builder()
                .name("dish #2")
                .price(150L)
                .cafe(cafe1)
                .build();

        Dish dish3 = Dish.builder()
                .name("dish #3")
                .price(200L)
                .cafe(cafe2)
                .build();

        Dish dish4 = Dish.builder()
                .name("dish #4")
                .price(250L)
                .cafe(cafe2)
                .build();

        dishRepository.saveAll(Arrays.asList(
                dish1, dish2, dish3, dish4
        ));

        FoodOrder foodOrder = FoodOrder.builder()
                .customer(account)
                .dishes(new HashSet<>(Arrays.asList(dish1, dish2)))
                .createTime(new Date())
                .build();

        foodOrderRepository.save(foodOrder);

        Payment payment = Payment.builder()
                .cost(250L)
                .payer(account)
                .foodOrder(foodOrder)
                .createTime(new Date())
                .build();
        paymentRepository.save(payment);
    }

}
