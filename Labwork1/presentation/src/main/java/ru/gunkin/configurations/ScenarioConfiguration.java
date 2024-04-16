package ru.gunkin.configurations;

import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.scenarios.account.login.LoginAccountProvider;
import ru.gunkin.scenarios.account.topup.TopUpProvider;
import ru.gunkin.scenarios.account.transfer.TransferAccountProvider;
import ru.gunkin.scenarios.bank.addsubscriber.AddSubscriberProvider;
import ru.gunkin.scenarios.bank.notify.NotifyProvider;
import ru.gunkin.scenarios.bank.removesubscriber.RemoveSubscriberProvider;
import ru.gunkin.scenarios.bank.undoaccountoperation.UndoAccountOperationProvider;
import ru.gunkin.scenarios.account.withdraw.WithdrawProvider;
import ru.gunkin.scenarios.accountcreater.createcredit.CreateCreditProvider;
import ru.gunkin.scenarios.accountcreater.createdebit.CreateDebitProvider;
import ru.gunkin.scenarios.accountcreater.createdeposit.CreateDepositProvider;
import ru.gunkin.scenarios.accountcreater.selectcreator.SelectCreatorProvider;
import ru.gunkin.scenarios.back.BackProvider;
import ru.gunkin.scenarios.bank.adminlogin.LoginAdminProvider;
import ru.gunkin.scenarios.bank.createuser.CreateUserProvider;
import ru.gunkin.scenarios.bank.deleteuser.DeleteUserProvider;
import ru.gunkin.scenarios.bank.login.LoginBankProvider;
import ru.gunkin.scenarios.bank.showaccountsoperations.ShowAccountsOperationsProvider;
import ru.gunkin.scenarios.bank.showusers.ShowUsersProvider;
import ru.gunkin.scenarios.centralbank.createbank.CreateBankProvider;
import ru.gunkin.scenarios.centralbank.deletebank.DeleteBankProvider;
import ru.gunkin.scenarios.centralbank.login.LoginCentralBankProvider;
import ru.gunkin.scenarios.centralbank.showbanks.ShowBanksProvider;
import ru.gunkin.scenarios.centralbank.showcentralbankoperation.ShowCentralBankOperationProvider;
import ru.gunkin.scenarios.centralbank.transfer.TransferCentralBankProvider;
import ru.gunkin.scenarios.centralbank.undocentralbankoperation.UndoCentralBankOperationProvider;
import ru.gunkin.scenarios.ScenarioProvider;
import ru.gunkin.scenarios.ScenarioRunner;
import ru.gunkin.scenarios.user.deleteaccount.DeleteAccountProvider;
import ru.gunkin.scenarios.user.login.LoginUserProvider;
import ru.gunkin.scenarios.user.showaccounts.ShowAccountsProvider;
import ru.gunkin.scenarios.user.shownotification.ShowNotificationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration
@Import({ServiceConfiguration.class, RepositoryConfiguration.class})
public class ScenarioConfiguration {
    @Bean
    public ScenarioRunner scenarioRunner(List<ScenarioProvider> scenarioProviders) {
        return new ScenarioRunner(scenarioProviders);
    }

    @Bean
    public ScenarioProvider backProvider(CurrentService currentService) {
        return new BackProvider(currentService);
    }

    @Bean
    public ScenarioProvider loginCentralBankProvider(CentralBankService centralBankService, CurrentService currentService) {
        return new LoginCentralBankProvider(centralBankService, currentService);
    }

    @Bean
    public ScenarioProvider createBankProvider(CentralBankService centralBankService,
                                               CurrentService currentService) {
        return new CreateBankProvider(centralBankService, currentService);
    }

    @Bean
    public ScenarioProvider deleteBankProvider(CentralBankService centralBankService, CurrentService currentService) {
        return new DeleteBankProvider(centralBankService, currentService);
    }

    @Bean
    public ScenarioProvider showBankProvider(CentralBankService centralBankService, CurrentService currentService) {
        return new ShowBanksProvider(centralBankService, currentService);
    }

    @Bean
    ScenarioProvider transferCentralBankOperationProvider(CurrentService currentService, CentralBankService centralBankService) {
        return new TransferCentralBankProvider(currentService, centralBankService);
    }

    @Bean
    ScenarioProvider undoCentralBankOperationProvider(CurrentService currentService, CentralBankService centralBankService) {
        return new UndoCentralBankOperationProvider(currentService, centralBankService);
    }

    @Bean
    ScenarioProvider showCentralBankOperationProvider(CurrentService currentService, CentralBankService centralBankService) {
        return new ShowCentralBankOperationProvider(currentService, centralBankService);
    }

    @Bean
    ScenarioProvider loginBankProvider(CurrentService currentService, BankService bankService) {
        return new LoginBankProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider loginAdminProvider(CurrentService currentService, BankService bankService) {
        return new LoginAdminProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider createUserProvider(CurrentService currentService, BankService bankService) {
        return new CreateUserProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider deleteUserProvider(CurrentService currentService, BankService bankService) {
        return new DeleteUserProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider showUsersProvider(CurrentService currentService, BankService bankService) {
        return new ShowUsersProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider undoAccountOperationProvider(CurrentService currentService, BankService bankService) {
        return new UndoAccountOperationProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider showOperationProvider(CurrentService currentService, BankService bankService) {
        return new ShowAccountsOperationsProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider notifyProvider(CurrentService currentService, BankService bankService) {
        return new NotifyProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider addSubscriberProvider(CurrentService currentService, BankService bankService) {
        return new AddSubscriberProvider(currentService, bankService);
    }

    @Bean
    ScenarioProvider removeSubscriberProvider(CurrentService currentService, BankService bankService) {
        return new RemoveSubscriberProvider(currentService, bankService);
    }
    @Bean

    ScenarioProvider loginUserProvider(CurrentService currentService, UserService userService) {
        return new LoginUserProvider(currentService, userService);
    }

    @Bean
    ScenarioProvider loginAccountProvider(CurrentService currentService, AccountService accountService) {
        return new LoginAccountProvider(currentService, accountService);
    }

    @Bean
    ScenarioProvider selectCreatorProvider(CurrentService currentService) {
        return new SelectCreatorProvider(currentService);
    }

    @Bean
    ScenarioProvider deleteAccountProvider(CurrentService currentService, UserService userService) {
        return new DeleteAccountProvider(currentService, userService);
    }

    @Bean
    ScenarioProvider showAccountsProvider(CurrentService currentService, UserService userService) {
        return new ShowAccountsProvider(currentService, userService);
    }

    @Bean
    ScenarioProvider showNotificationProvider(CurrentService currentService, UserService userService) {
        return new ShowNotificationProvider(currentService, userService);
    }

    @Bean
    ScenarioProvider createDebitProvider(CurrentService currentService, UserService userService) {
        return new CreateDebitProvider(currentService, userService);
    }

    @Bean
    ScenarioProvider createDepositProvider(CurrentService currentService, UserService userService) {
        return new CreateDepositProvider(currentService, userService);
    }

    @Bean
    ScenarioProvider createCreditProvider(CurrentService currentService, UserService userService) {
        return new CreateCreditProvider(currentService, userService);
    }

    @Bean
    ScenarioProvider topUpProvider(CurrentService currentService, AccountService accountService) {
        return new TopUpProvider(currentService, accountService);
    }

    @Bean
    ScenarioProvider withdrawProvider(CurrentService currentService, AccountService accountService) {
        return new WithdrawProvider(currentService, accountService);
    }

    @Bean
    public ScenarioProvider transferAccountProvider(CurrentService currentService, AccountService accountService) {
        return new TransferAccountProvider(currentService, accountService);
    }
}
