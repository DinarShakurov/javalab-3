package ru.shakurov.hateoas.hateoas_example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shakurov.hateoas.hateoas_example.services.AccountService;

@RepositoryRestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PutMapping(value = "/accounts/{accountId}/fillMoney")
    public @ResponseBody
    ResponseEntity<?> fillMoney(@PathVariable(name = "accountId") Long accountId,
                                @RequestBody Long newMoney) {
        return ResponseEntity.ok(
                EntityModel.of(accountService.fillMoney(accountId, newMoney)));
    }
}
