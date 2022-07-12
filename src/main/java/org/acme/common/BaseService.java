package org.acme.common;

import org.acme.dto.CustomerProfileDto;
import org.acme.entity.CustomerProfile;

import java.util.ArrayList;
import java.util.List;

public class BaseService {

    protected List<CustomerProfileDto> mapCustomerProfileData(List<CustomerProfile> customerProfileList) {
        List<CustomerProfileDto> customerProfileDtoList = new ArrayList<>();
        CustomerProfileDto customerProfileDto;
        for ( CustomerProfile customerProfile : customerProfileList) {

            customerProfileDto = new CustomerProfileDto();
            customerProfileDto.setCustomerId(String.valueOf(customerProfile.getCustomerId()));
            customerProfileDto.setTitleName(customerProfile.getTitleName());
            customerProfileDto.setFirstName(customerProfile.getFirstName());
            customerProfileDto.setLastName(customerProfile.getLastName());
            customerProfileDto.setBirthDay(customerProfile.getBirthDay());
            customerProfileDto.setCreatedDateTime(customerProfile.getCreatedDateTime());
            customerProfileDto.setCustomerCisList(customerProfile.getCustomerCisList());

            customerProfileDtoList.add(customerProfileDto);
        }

        return customerProfileDtoList;
    }
}
