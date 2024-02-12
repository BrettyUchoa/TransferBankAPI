package io.github.Brettyuchoa.service;

import io.github.Brettyuchoa.model.Account;
import io.github.Brettyuchoa.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransferBalance_AccountsExist() {
        // Configuração do mock
        when(accountService.findById(1L)).thenReturn(new Account(1L, 100L, 500.0));
        when(accountService.findById(2L)).thenReturn(new Account(2L, 200L, 300.0));

        // Execução do método a ser testado
        accountService.transferBalance(1L, 2L, 100.0);

        // Verificação se o método save foi chamado
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransferBalance_InsufficientBalance() {
        // Configuração do mock
        when(accountService.findById(1L)).thenReturn(new Account(1L, 100L, 50.0));
        when(accountService.findById(2L)).thenReturn(new Account(2L, 200L, 300.0));

        // Execução do método a ser testado
        accountService.transferBalance(1L, 2L, 100.0);
    }
}
