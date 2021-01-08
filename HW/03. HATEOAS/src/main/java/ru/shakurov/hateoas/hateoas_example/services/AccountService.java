package ru.shakurov.hateoas.hateoas_example.services;

import ru.shakurov.hateoas.hateoas_example.domain.Account;

public interface AccountService {
    Account fillMoney(Long accountId, Long money);
}
