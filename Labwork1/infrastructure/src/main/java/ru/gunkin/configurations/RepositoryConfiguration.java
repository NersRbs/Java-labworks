package ru.gunkin.configurations;

import ru.gunkin.abstraction.BanksRepository;
import ru.gunkin.abstraction.OperationsRepository;
import ru.gunkin.repositories.BanksRepositoryImpl;
import ru.gunkin.repositories.OperationsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public BanksRepository banksRepository() {
        return new BanksRepositoryImpl();
    }

    @Bean
    public OperationsRepository operationsRepository() {
        return new OperationsRepositoryImpl();
    }
}
