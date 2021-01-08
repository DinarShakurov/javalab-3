package ru.shakurov.hateoas.hateoas_example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shakurov.hateoas.hateoas_example.domain.Account;
import ru.shakurov.hateoas.hateoas_example.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public Account fillMoney(Long accountId, Long money) {
        Account account = accountRepository.findById(accountId).orElseThrow(IllegalArgumentException::new);
        account.setMoney(account.getMoney() + money);
        return account;
    }
}
