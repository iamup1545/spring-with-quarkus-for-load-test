package org.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.acme.entity.CustomerCis;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileDto {

    private String customerId;

    private String titleName;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private LocalDate createdDateTime;

    private List<CustomerCis> customerCisList;

}
