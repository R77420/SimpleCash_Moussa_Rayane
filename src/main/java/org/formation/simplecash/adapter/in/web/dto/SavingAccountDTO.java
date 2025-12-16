package org.formation.simplecash.adapter.in.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SavingAccountDTO extends AccountDTO {
    private BigDecimal rate;
}
