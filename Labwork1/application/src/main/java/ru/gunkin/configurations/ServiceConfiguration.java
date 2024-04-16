package ru.gunkin.configurations;

import ru.gunkin.abstraction.BanksRepository;
import ru.gunkin.abstraction.OperationsRepository;
import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.core.AccountServiceImpl;
import ru.gunkin.core.BankServiceImpl;
import ru.gunkin.core.CentralBankServiceImpl;
import ru.gunkin.core.current.CurrentServiceImpl;
import ru.gunkin.core.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public CurrentService currentService() {
        return new CurrentServiceImpl();
    }

    @Bean
    public CentralBankService centralBankService(CurrentService currentService, BanksRepository banksRepository, OperationsRepository operationsRepository) {
        return new CentralBankServiceImpl(currentService, banksRepository, operationsRepository);
    }

    @Bean
    public BankService bankService(CurrentService currentService, BanksRepository banksRepository) {
        return new BankServiceImpl(currentService, banksRepository);
    }

    @Bean
    public UserService userService(CurrentService currentService) {
        return new UserServiceImpl(currentService);
    }

    @Bean
    public AccountService accountService(CurrentService currentService) {
        return new AccountServiceImpl(currentService);
    }
}
