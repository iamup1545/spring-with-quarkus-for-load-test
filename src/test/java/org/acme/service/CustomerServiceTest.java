package org.acme.service;

import org.acme.dto.customerprofile.CustomerProfileDto;
import org.acme.entity.customerprofile.CustomerProfile;
import org.acme.repository.CustomerProfileRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wildfly.common.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    protected CustomerProfileService customerProfileService;

    @Mock
    protected CustomerProfileRepository customerProfileRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    private List<CustomerProfile> getMockEntityGetCustomerProfileById() {
        List<CustomerProfile> customerProfileList = new ArrayList<>();
        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setCustomerId(new ObjectId("45c98be8d5b44345b82803c1"));
        customerProfile.setTitleName("Mr.");
        customerProfile.setFirstName("mockFirstName");
        customerProfile.setLastName("mockLastName");
        customerProfileList.add(customerProfile);
        return customerProfileList;
    }

    @Test
    @DisplayName("Test loan to value case success")
    public void testGetCustomerProfileById() {
        when(this.customerProfileRepository.findCustomerProfileById(any())).thenReturn(this.getMockEntityGetCustomerProfileById());

        List<CustomerProfileDto> customerProfileDtoList = this.customerProfileService.getCustomerProfileById("45c98be8d5b44345b82803c1");

        CustomerProfileDto customerProfileResponse;
        CustomerProfile customerProfileMock;
        for (int i=0; i < customerProfileDtoList.size(); i++) {
            for (int j=0; j < this.getMockEntityGetCustomerProfileById().size(); j++)
                if (i == j) {

                    customerProfileResponse = customerProfileDtoList.get(i);
                    customerProfileMock = this.getMockEntityGetCustomerProfileById().get(j);

                    Assert.assertTrue(String.valueOf(customerProfileResponse.getCustomerId()).equals(String.valueOf(customerProfileMock.getCustomerId())));
                    Assert.assertTrue(customerProfileResponse.getTitleName().equalsIgnoreCase(customerProfileMock.getTitleName()));
                    Assert.assertTrue(customerProfileResponse.getFirstName().equalsIgnoreCase(customerProfileMock.getFirstName()));
                    Assert.assertTrue(customerProfileResponse.getLastName().equalsIgnoreCase(customerProfileMock.getLastName()));
                }
        }
    }
}
