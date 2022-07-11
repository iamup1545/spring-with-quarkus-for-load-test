package org.acme.service;

import org.acme.dto.AddressCisDto;
import org.acme.dto.CustomerCisDto;
import org.acme.dto.CustomerProfileDto;
import org.acme.dto.InvestmentLimitCisDto;
import org.acme.entity.AddressCis;
import org.acme.entity.CustomerCis;
import org.acme.entity.CustomerProfile;
import org.acme.repository.CustomerProfileRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("customerProfileService")
public class CustomerProfileService {

    @Autowired
    private CustomerProfileRepository customerProfileRepository;

    public Optional<CustomerProfile> getCustomerProfileById(String id){
        return customerProfileRepository.findCustomerProfileById(id);
    }

    public List<CustomerProfile> getCustomerProfileWithLimit(int limit){
        return customerProfileRepository.getCustomerProfileWithLimit(limit);
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
            for (CustomerCis customerCis: customerProfile.getCustomerCisList()) {
                InvestmentLimitCisDto investmentCisDto = new InvestmentLimitCisDto();
                investmentCisDto.setOverLimit(customerCis.getInvestmentLimitCis().getOverLimit());
                investmentCisDto.setCreateDate(customerCis.getInvestmentLimitCis().getCreateDate());
                investmentCisDto.setCreatedBy(customerCis.getInvestmentLimitCis().getCreatedBy());
                investmentCisDtoList.add(investmentCisDto);
            }
        }

        return investmentCisDtoList;

    }

    public List<String> addCustomerProfileALotProfile(List<CustomerProfile> customerProfileList) {

        List<String> idCustomerProfile = new ArrayList<>();
        for (CustomerProfile customerProfile : customerProfileList) {
            customerProfile.setCreatedDateTime(LocalDate.now());
        }

        this.customerProfileRepository.persist(customerProfileList);

        for (CustomerProfile customerProfile : customerProfileList) {
            idCustomerProfile.add(String.valueOf(customerProfile.getCustomerId()));
        }

        return idCustomerProfile;

    }

    public List<CustomerProfileDto> deleteCustomerProfileALotProfile(List<CustomerProfile> customerProfileList) {

        List<CustomerProfileDto> deleteCustomerProfile = new ArrayList<>();
        for (CustomerProfile customerProfile : customerProfileList) {
            Document query = new Document();
            this.customerProfileRepository.find(query);
            this.customerProfileRepository.delete(customerProfile);
        }

        return deleteCustomerProfile;

    }

}
