import org.junit.jupiter.api.Test;
import ru.gunkin.abstraction.*;
import ru.gunkin.contracts.account.Account;
import ru.gunkin.contracts.centralbank.ResultCentralBankOperation;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.operation.ResultOperation;
import ru.gunkin.core.CentralBankServiceImpl;
import ru.gunkin.model.Bank;
import ru.gunkin.model.User;
import ru.gunkin.model.operation.Transfer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CentralBankServiceClassTest {


    // create bank with valid parameters
    @Test
    public void create_centralBank_success() {
        // Arrange
        CurrentService currentService = mock(CurrentService.class);
        BanksRepository banksRepository = mock(BanksRepository.class);
        OperationsRepository operationsRepository = mock(OperationsRepository.class);
        CentralBankServiceImpl centralBankService = new CentralBankServiceImpl(currentService, banksRepository, operationsRepository);

        // Act
        ResultCentralBankOperation result = centralBankService.createBank("sber", 12, 12, 12);

        // Assert
        assertInstanceOf(ResultCentralBankOperation.Success.class, result);
    }

    // delete bank that exists
    @Test
    public void delete_centralBank_success() {
        // Arrange
        CurrentService currentService = mock(CurrentService.class);
        BanksRepository banksRepository = mock(BanksRepository.class);
        OperationsRepository operationsRepository = mock(OperationsRepository.class);
        CentralBankServiceImpl centralBankService = new CentralBankServiceImpl(currentService, banksRepository, operationsRepository);
        UUID bankId = UUID.randomUUID();
        when(banksRepository.bankExists(bankId)).thenReturn(true);

        // Act
        ResultCentralBankOperation result = centralBankService.deleteBank(bankId);

        // Assert
        assertInstanceOf(ResultCentralBankOperation.Success.class, result);
    }

    // undo a transfer operation
    @Test
    public void undo_centralBank_success() {
        // Arrange
        CurrentService currentService = mock(CurrentService.class);
        BanksRepository banksRepository = mock(BanksRepository.class);
        OperationsRepository operationsRepository = mock(OperationsRepository.class);
        CentralBankServiceImpl centralBankService = new CentralBankServiceImpl(currentService, banksRepository, operationsRepository);
        UUID operationId = UUID.randomUUID();
        Transfer transfer = mock(Transfer.class);
        when(transfer.undoOperation()).thenReturn(new ResultOperation.Success());
        when(operationsRepository.operationExists(operationId)).thenReturn(true);
        when(operationsRepository.getOperation(operationId)).thenReturn(transfer);

        // Act
        ResultCentralBankOperation result = centralBankService.deleteAndUndoOperation(operationId);

        // Assert
        assertInstanceOf(ResultCentralBankOperation.Success.class, result);
    }

}