package io.github.Brettyuchoa.service;

import io.github.Brettyuchoa.model.Account;
import io.github.Brettyuchoa.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Double checkBalance(Long accountNumber) {
        java.util.Optional<Account> account = accountRepository.findById(accountNumber);

        if (! account.isPresent()) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        return account.get().getBalance();
    }

    public void validateWithdrawal(Long accountNumber, Double withdrawalAmount) {
        Account account = accountRepository.findById();

        if (account == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        if (withdrawalAmount <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }

        if (account.getBalance() < withdrawalAmount) {
            throw new IllegalArgumentException("Saldo insuficiente para o saque.");
        }
    }

    public void transferBalance(Long sourceAccountId, Long targetAccountId, double amount) {
        System.out.println("Transfer initiated.");

        // Verificação se as contas existem
        java.util.Optional<Account> sourceAccount = accountRepository.findById(sourceAccountId);
        java.util.Optional<Account> targetAccount = accountRepository.findById(targetAccountId);

        if (sourceAccount == null || targetAccount == null) {
            throw new IllegalArgumentException("Conta de origem ou destino não encontrada.");
        }

        // Verificação de saldo suficiente
        if (sourceAccount.get().getBalance() >= amount) {
            sourceAccount.debit(amount);
            targetAccount.credit(amount);

            // Salvando as alterações
            accountRepository.save(sourceAccount);
            accountRepository.save(targetAccount);

            System.out.println("Transfer completed.");
        } else {
            // Lógica para lidar com saldo insuficiente
            throw new IllegalArgumentException("Transfer failed: Saldo insuficiente.");
        }
    }

}
