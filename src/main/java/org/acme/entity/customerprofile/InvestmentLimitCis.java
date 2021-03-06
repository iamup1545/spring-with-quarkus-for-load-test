package org.acme.entity.customerprofile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentLimitCis {

    private BigDecimal overLimit;

    private String createdBy;

    private LocalDate createDate;

}
