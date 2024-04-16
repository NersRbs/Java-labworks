import org.junit.jupiter.api.Test;
import ru.gunkin.contracts.account.Account;
import ru.gunkin.contracts.account.ResultAccountOperation;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.operation.Operation;
import ru.gunkin.core.AccountServiceImpl;
import ru.gunkin.model.Bank;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceClassTest {


    // login with valid bank, user and account id
    @Test
    public void login_validBankUserAndAccountId_success() {
        // Mock dependencies
        CurrentService currentService = mock(CurrentService.class);
        HashMap<UUID, Account> accountsRepository = mock(HashMap.class);
        Account account = mock(Account.class);
        Bank bank = Bank.builder()
                .id(UUID.randomUUID())
                .addName("Bank")
                .addInterestRate(12.0)
                .addCommission(12.0)
                .addCreditLimit(1000.0)
                .addSubscribers(mock(HashMap.class))
                .addUsers(mock(HashMap.class))
                .addAccounts(accountsRepository)
                .addOperations(mock(HashMap.class))
                .build();

    
        // Set up mock behavior
        when(currentService.tryGetBank()).thenReturn(Optional.of(bank));
        when(currentService.tryGetUserId()).thenReturn(Optional.of(UUID.randomUUID()));
        when(bank.accounts().containsKey(any(UUID.class))).thenReturn(true);
        when(bank.accounts().get(any(UUID.class))).thenReturn(account);
        when(account.getUserId()).thenReturn(UUID.randomUUID());
        
        // Create instance of AccountServiceClass
        AccountServiceImpl accountService = new AccountServiceImpl(currentService);
        
        // Call the login method
        ResultAccountOperation result = accountService.login(UUID.randomUUID());
        
        // Assert the result
        assertInstanceOf(ResultAccountOperation.Success.class, result);
    }

    // top up with valid bank, user and account id
    @Test
    public void topUp_validBankUserAndAccountId_success() {
        // Mock dependencies
        CurrentService currentService = mock(CurrentService.class);
        HashMap<UUID, Account> accountsRepository = mock(HashMap.class);
        Account account = mock(Account.class);
        Bank bank = Bank.builder()
                .id(UUID.randomUUID())
                .addName("Bank")
                .addInterestRate(12.0)
                .addCommission(12.0)
                .addCreditLimit(1000.0)
                .addSubscribers(mock(HashMap.class))
                .addUsers(mock(HashMap.class))
                .addAccounts(accountsRepository)
                .addOperations(mock(HashMap.class))
                .build();
    
        // Set up mock behavior
        when(currentService.tryGetBank()).thenReturn(Optional.of(bank));
        when(currentService.tryGetUserId()).thenReturn(Optional.of(UUID.randomUUID()));
        when(currentService.tryGetAccountId()).thenReturn(Optional.of(UUID.randomUUID()));
        when(accountsRepository.containsKey(any(UUID.class))).thenReturn(true);
        when(accountsRepository.get(any(UUID.class))).thenReturn(account);
        when(account.topUp(anyDouble())).thenReturn(new ResultAccountOperation.Success());
    
        // Create instance of AccountServiceClass
        AccountServiceImpl accountService = new AccountServiceImpl(currentService);
    
        // Call the topUp method
        ResultAccountOperation result = accountService.topUp(100.0);
    
        // Assert the result
        assertInstanceOf(ResultAccountOperation.Success.class, result);
    }

    // withdraw with valid bank, user and account id and sufficient funds
    @Test
    public void withdraw_validBankUserAndAccountId_success() {
        // Mock dependencies
        CurrentService currentService = mock(CurrentService.class);
        HashMap<UUID, Account> accountsRepository = mock(HashMap.class);
        Account account = mock(Account.class);
        Bank bank = Bank.builder()
                .id(UUID.randomUUID())
                .addName("Bank")
                .addInterestRate(12.0)
                .addCommission(12.0)
                .addCreditLimit(1000.0)
                .addSubscribers(mock(HashMap.class))
                .addUsers(mock(HashMap.class))
                .addAccounts(accountsRepository)
                .addOperations(mock(HashMap.class))
                .build();
    
        // Set up mock behavior
        when(currentService.tryGetBank()).thenReturn(Optional.of(bank));
        when(currentService.tryGetUserId()).thenReturn(Optional.of(UUID.randomUUID()));
        when(currentService.tryGetAccountId()).thenReturn(Optional.of(UUID.randomUUID()));
        when(accountsRepository.containsKey(any(UUID.class))).thenReturn(true);
        when(accountsRepository.get(any(UUID.class))).thenReturn(account);
        when(account.withdraw(anyDouble())).thenReturn(new ResultAccountOperation.Success());
    
        // Create instance of AccountServiceClass
        AccountServiceImpl accountService = new AccountServiceImpl(currentService);
    
        // Call the withdraw method
        ResultAccountOperation result = accountService.withdraw(100.0);
    
        // Assert the result
        assertInstanceOf(ResultAccountOperation.Success.class, result);
    }

    // transfer with valid bank, user and account id and sufficient funds
    @Test
    public void transfer_validBankUserAndAccountId_success() {
        // Mock dependencies
        CurrentService currentService = mock(CurrentService.class);
        HashMap<UUID, Account> accountsRepository = mock(HashMap.class);
        HashMap<UUID, Operation> operationsRepository = mock(HashMap.class);
        Account account = mock(Account.class);
        Bank bank = Bank.builder()
                .id(UUID.randomUUID())
                .addName("Bank")
                .addInterestRate(12.0)
                .addCommission(12.0)
                .addCreditLimit(1000.0)
                .addSubscribers(mock(HashMap.class))
                .addUsers(mock(HashMap.class))
                .addAccounts(accountsRepository)
                .addOperations(mock(HashMap.class))
                .build();
    
        // Set up mock behavior
        when(currentService.tryGetBank()).thenReturn(Optional.of(bank));
        when(currentService.tryGetUserId()).thenReturn(Optional.of(UUID.randomUUID()));
        when(currentService.tryGetAccountId()).thenReturn(Optional.of(UUID.randomUUID()));
        when(accountsRepository.containsKey(any(UUID.class))).thenReturn(true);
        when(accountsRepository.get(any(UUID.class))).thenReturn(account);
    
        // Create instance of AccountServiceClass
        AccountServiceImpl accountService = new AccountServiceImpl(currentService);
    
        // Call the transfer method
        ResultAccountOperation result = accountService.transfer(UUID.randomUUID(), 100.0);
    
        // Assert the result
        assertInstanceOf(ResultAccountOperation.Success.class, result);
    }

}