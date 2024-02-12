package io.github.Brettyuchoa.controller;

import org.springframework.web.bind.annotation.RestController;
import io.github.Brettyuchoa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/balance")
    public Double checkBalance(@RequestBody Long accountNumber) {
        return accountService.checkBalance(accountNumber);
    }

    @PostMapping("/transfer")
    public String transferBalance(@RequestBody TransferRequest transferRequest) {
        try {
            accountService.validateWithdrawal(transferRequest.getSourceAccountNumber(), transferRequest.getAmount());
            accountService.transferBalance(
                    transferRequest.getSourceAccountNumber(),
                    transferRequest.getTargetAccountNumber(),
                    transferRequest.getAmount()
            );
            return "Transferência realizada com sucesso.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    // Outros métodos e classes necessários
}
