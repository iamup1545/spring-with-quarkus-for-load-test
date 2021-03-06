package org.acme.entity.customerprofile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCis {

    private String addressName;

    private String country;

    private String zipCode;

}
