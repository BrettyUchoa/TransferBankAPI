package io.github.Brettyuchoa.repository;

import io.github.Brettyuchoa.model.Account;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findById();
}