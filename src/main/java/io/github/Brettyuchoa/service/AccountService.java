package io.github.Brettyuchoa.service;

import io.github.Brettyuchoa.model.Account;
import io.github.Brettyuchoa.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Double checkBalance(Long accountNumber) {
        java.util.Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if (accountOptional.isEmpty()) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
        return accountOptional.get().getBalance();
    }

    public void validateWithdrawal(Long accountNumber, Double withdrawalAmount) {
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if (accountOptional.isEmpty()) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
    }

    @Transactional
    public void transferBalance(Long sourceAccountId, Long targetAccountId, double amount) {
        log.info("Transfer initiated.");
        Account sourceAccount = findById(sourceAccountId);
        Account targetAccount = findById(targetAccountId);

        // Verificação de saldo suficiente
        if (sourceAccount.getBalance() >= amount) {
            debit(sourceAccount , amount);
            credit(targetAccount, amount);

            // Salvando as alterações
            accountRepository.save(sourceAccount);
            accountRepository.save(targetAccount);
            log.info("Transfer completed.");
        } else {
            // Lógica para lidar com saldo insuficiente
            log.error("Transfer failed: Saldo insuficiente.");
            throw new IllegalArgumentException("Transfer failed: Saldo insuficiente.");
        }
    }

    private void debit(Account account, double valor){
        account.setBalance(account.getBalance() - valor);
    }

    private void credit(Account account, double valor){
        account.setBalance(account.getBalance() + valor);
    }

    public Account findById(Long numberAccuont){
        // Verificação se a conta existe
        java.util.Optional<Account> accountOptional = accountRepository.findById(numberAccuont);
        if (accountOptional.isEmpty() ) {
            throw new IllegalArgumentException("Conta de origem ou destino não encontrada.");
        }
        return accountOptional.get();
    }

}
