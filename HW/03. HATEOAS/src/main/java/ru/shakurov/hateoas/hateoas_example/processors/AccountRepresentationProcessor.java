package ru.shakurov.hateoas.hateoas_example.processors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.shakurov.hateoas.hateoas_example.controllers.AccountController;
import ru.shakurov.hateoas.hateoas_example.domain.Account;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Account>> {

    @Override
    public EntityModel<Account> process(EntityModel<Account> model) {
        Account account = model.getContent();
        if (account != null && account.getMoney() < 500_000) {
            model.add(linkTo(methodOn(AccountController.class)
                    .fillMoney(account.getId(), null))
                    .withRel("fillMoney"));
        }
        return model;
    }
}
