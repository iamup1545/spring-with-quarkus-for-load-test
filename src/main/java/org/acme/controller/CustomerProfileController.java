package org.acme.controller;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import org.acme.dto.customerprofile.AddressCisDto;
import org.acme.dto.customerprofile.CustomerCisDto;
import org.acme.dto.customerprofile.CustomerProfileDto;
import org.acme.dto.customerprofile.InvestmentLimitCisDto;
import org.acme.entity.customerprofile.CustomerProfile;
import org.acme.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NonBlocking
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService customerProfileService;

    @GetMapping("/getCustomerProfileById/{customerId}") // for load test
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerProfileDto> getCustomerProfileById(@PathVariable String customerId){
        return this.customerProfileService.getCustomerProfileById(String.valueOf(customerId));
    }

    @GetMapping("/getCustomerProfileWithLimit/{limit}") // for load test
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerProfileDto> getCustomerProfileWithLimit(@PathVariable int limit){
        return this.customerProfileService.getCustomerProfileWithLimit(limit);
    }

    @GetMapping("/getCustomerProfileOnly/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerProfileDto> getCustomerProfileOnlyWithLimit(@PathVariable int limit){
        return this.customerProfileService.getCustomerProfileOnlyWithLimit(limit);
    }

    @GetMapping("/getCustomerCisWithLimit/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerCisDto>getCustomerCisWithLimit(@PathVariable int limit){
        return this.customerProfileService.getCustomerCisWithLimit(limit/2);
    }

    @GetMapping("/getAddressCisWithLimit/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressCisDto> getAddressCisWithLimit(@PathVariable int limit){
        return this.customerProfileService.getAddressCisWithLimit(limit/4);
    }

    @GetMapping("/getInvestmentLimitCisWithLimit/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvestmentLimitCisDto> getInvestmentLimitCisWithLimit(@PathVariable int limit){
        return this.customerProfileService.getInvestmentLimitCisWithLimit(limit/2);
    }

    @PostMapping("/addCustomerProfileList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> addCustomerProfileList(@RequestBody List<CustomerProfile> customerProfileList){
        return this.customerProfileService.addCustomerProfileManyProfiles(customerProfileList);
    }

    @DeleteMapping("/deleteCustomerProfileList")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerProfileDto> deleteCustomerProfileList(@RequestBody List<CustomerProfile> customerProfileList){
        return this.customerProfileService.deleteCustomerProfileManyProfiles(customerProfileList);
    }

    @GetMapping("/getCustomerProfileWithMounteBank")
    @ResponseStatus(HttpStatus.OK)
    public Uni<Object[]> getCustomerProfileWithMounteBank(){
        return this.customerProfileService.getCustomerProfileWithMounteBank();
    }
}
