package org.acme.dto.customerprofile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentLimitCisDto {

    private BigDecimal overLimit;

    private String createdBy;

    private LocalDate createDate;

}
