package org.formation.simplecash.application.port.in;

import java.math.BigDecimal;

public interface TransferUseCase {
    void transfer(String accountNumber1, String accountNumber2, BigDecimal amount);
}
