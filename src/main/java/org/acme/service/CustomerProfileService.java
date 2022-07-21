package org.acme.service;

import io.smallrye.mutiny.Uni;
import org.acme.common.service.BaseService;
import org.acme.dto.customerprofile.AddressCisDto;
import org.acme.dto.customerprofile.CustomerCisDto;
import org.acme.dto.customerprofile.CustomerProfileDto;
import org.acme.dto.customerprofile.InvestmentLimitCisDto;
import org.acme.entity.customerprofile.AddressCis;
import org.acme.entity.customerprofile.CustomerCis;
import org.acme.entity.customerprofile.CustomerProfile;
import org.acme.repository.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("customerProfileService")
public class CustomerProfileService extends BaseService {

    @Autowired
    private CustomerProfileRepository customerProfileRepository;

    public List<CustomerProfileDto> getCustomerProfileById(String id){
        List<CustomerProfile> customerProfileList =  customerProfileRepository.findCustomerProfileById(id);
        return this.mapCustomerProfileData(customerProfileList);
    }

    public List<CustomerProfileDto> getCustomerProfileWithLimit(int limit){
        List<CustomerProfile> customerProfileList =  customerProfileRepository.getCustomerProfileWithLimit(limit);
        return this.mapCustomerProfileData(customerProfileList);
    }

    public List<CustomerProfileDto> getCustomerProfileOnlyWithLimit(int limit){
        List<CustomerProfile> customerProfileList = customerProfileRepository.getCustomerProfileWithLimit(limit);
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

            customerProfileDtoList.add(customerProfileDto);
        }

        return customerProfileDtoList;
    }

    public List<CustomerCisDto> getCustomerCisWithLimit(int limit){
        List<CustomerProfile> customerProfileList = customerProfileRepository.getCustomerProfileWithLimit(limit);
        List<CustomerCisDto> customerCisDtoList = new ArrayList<>();
        for ( CustomerProfile customerProfile : customerProfileList) {
            for (CustomerCis customerCis: customerProfile.getCustomerCisList()) {
                CustomerCisDto customerCisDto = new CustomerCisDto();
                customerCisDto.setCustomer_id(String.valueOf(customerProfile.getCustomerId()));
                customerCisDto.setCisId(customerCis.getCisId());
                customerCisDtoList.add(customerCisDto);
            }
        }

        return customerCisDtoList;
    }

    public List<AddressCisDto> getAddressCisWithLimit(int limit){
        List<CustomerProfile> customerProfileList = customerProfileRepository.getCustomerProfileWithLimit(limit);
        List<AddressCisDto> addressCisDtoList = new ArrayList<>();
        for ( CustomerProfile customerProfile : customerProfileList) {
            for (CustomerCis customerCis: customerProfile.getCustomerCisList()) {
                for (AddressCis addressCis : customerCis.getAddressCisList()) {
                    AddressCisDto addressCisDto = new AddressCisDto();
                    addressCisDto.setAddressName(addressCis.getAddressName());
                    addressCisDto.setCountry(addressCis.getCountry());
                    addressCisDto.setZipCode(addressCis.getZipCode());
                    addressCisDtoList.add(addressCisDto);
                }
            }
        }

        return addressCisDtoList ;
    }

    public List<InvestmentLimitCisDto> getInvestmentLimitCisWithLimit(int limit) {

        List<CustomerProfile> customerProfileList = customerProfileRepository.getCustomerProfileWithLimit(limit);
        List<InvestmentLimitCisDto> investmentCisDtoList = new ArrayList<>();


        for ( CustomerProfile customerProfile : customerProfileList) {
            for (CustomerCis customerCis : customerProfile.getCustomerCisList()) {
                InvestmentLimitCisDto investmentCisDto = new InvestmentLimitCisDto();
                investmentCisDto.setOverLimit(customerCis.getInvestmentLimitCis().getOverLimit());
                investmentCisDto.setCreateDate(customerCis.getInvestmentLimitCis().getCreateDate());
                investmentCisDto.setCreatedBy(customerCis.getInvestmentLimitCis().getCreatedBy());
                investmentCisDtoList.add(investmentCisDto);
            }
        }

        return investmentCisDtoList;

    }

    public List<String> addCustomerProfileManyProfiles(List<CustomerProfile> customerProfileList) {
        return customerProfileRepository.addManyProfile(customerProfileList);
    }

    public Uni<Object[]> getCustomerProfileWithMounteBank(){
        return customerProfileRepository.getCustomerProfileWithMounteBank();
    }

    public List<CustomerProfileDto> deleteCustomerProfileManyProfiles(List<CustomerProfile> customerProfileList) {
        List<CustomerProfile> deleteCustomerProfileList = customerProfileRepository.deleteManyProfile(customerProfileList);
        List<CustomerProfileDto> customerProfileDtoList = new ArrayList<>();
        CustomerProfileDto customerProfileDto;
        for ( CustomerProfile customerProfile : deleteCustomerProfileList) {
            customerProfileDto = new CustomerProfileDto();
            customerProfileDto.setCustomerId(String.valueOf(customerProfile.getCustomerId()));
            customerProfileDto.setTitleName(customerProfile.getTitleName());
            customerProfileDto.setFirstName(customerProfile.getFirstName());
            customerProfileDto.setLastName(customerProfile.getLastName());

            customerProfileDtoList.add(customerProfileDto);
        }

        return customerProfileDtoList;
    }

    private List<CustomerProfileDto> mapCustomerProfileData(List<CustomerProfile> customerProfileList) {
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
