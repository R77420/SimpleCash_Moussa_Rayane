package org.formation.simplecash.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "account_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CurrentAccountDTO.class, name = "CURRENT"),
        @JsonSubTypes.Type(value = SavingAccountDTO.class, name = "SAVING")
})
@Data
@NoArgsConstructor
public abstract class AccountDTO {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime creationDate;
    // We might need to handle Client reference if it was serialized.
    // Client has ID ? Or full Client?
    // AccountEntity had 'private Client client'.
    // If serialized, it would include Client.
    // But Client has List<Account>. Loop.
    // I I will omit 'client' field in AccountDTO for now to avoid loops, unless
    // required.
}
