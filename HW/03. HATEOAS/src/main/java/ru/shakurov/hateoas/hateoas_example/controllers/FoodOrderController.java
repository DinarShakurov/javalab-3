package ru.shakurov.hateoas.hateoas_example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shakurov.hateoas.hateoas_example.domain.FoodOrder;
import ru.shakurov.hateoas.hateoas_example.repositories.FoodOrderRepository;

import java.util.Date;

@RepositoryRestController
public class FoodOrderController {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @PostMapping(value = "/foodOrders")
    @ResponseBody
    public ResponseEntity<?> makeOrder(@RequestBody EntityModel<FoodOrder> foodOrderEntityModel) {
        FoodOrder foodOrder = foodOrderEntityModel.getContent();
        assert foodOrder != null;
        foodOrder.setCreateTime(new Date());
        foodOrderRepository.save(foodOrder);
        return ResponseEntity.ok(
                EntityModel.of(foodOrder));
    }
}
