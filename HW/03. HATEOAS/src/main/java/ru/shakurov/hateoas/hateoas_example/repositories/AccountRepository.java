package ru.shakurov.hateoas.hateoas_example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shakurov.hateoas.hateoas_example.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
