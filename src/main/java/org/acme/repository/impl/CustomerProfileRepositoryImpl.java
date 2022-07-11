package org.acme.repository.impl;

import org.acme.entity.CustomerProfile;
import org.acme.repository.CustomerProfileRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("customerProfileRepository")
public class CustomerProfileRepositoryImpl implements CustomerProfileRepository {

    public static final String _id = "_id";
    public static final String titleName = "titleName";
    public static final String firstName = "firstName";
    public static final String lastName = "lastName";
    public static final String birthDay = "birthDay";
    public static final String createdDateTime = "createdDateTime";
    public static final String customerCisList = "customerCisList";

    public Optional<CustomerProfile> findCustomerProfileById(String id) {
        return this.findByIdOptional(new ObjectId(id));
    }

    public List<CustomerProfile> getCustomerProfileWithLimit(int limit) {
        return this.findAll().stream().limit(limit).collect(Collectors.toList());
    }

    public List<CustomerProfile> deleteCustomerProfileManyProfile(List<CustomerProfile> customerProfileList) {
        List<CustomerProfile> deleteCustomerProfileList = new ArrayList<>();
        Document query;
        CustomerProfile deleteCustomerProfile;
        for (CustomerProfile customerProfile : customerProfileList) {

            query = this.validateAndAppendQueryToDocument(customerProfile);
            deleteCustomerProfile = this.find(query).firstResult();

            deleteCustomerProfileList.add(deleteCustomerProfile);

            this.delete(deleteCustomerProfile);
        }

        return deleteCustomerProfileList;
    }

    private Document validateAndAppendQueryToDocument(CustomerProfile customerProfile) {
        Document query = new Document();

        if (!StringUtils.isEmpty(customerProfile.getCustomerId())) {
            query.append(_id, customerProfile.getCustomerId());
        }
        if (!StringUtils.isEmpty(customerProfile.getTitleName())) {
            query.append(titleName, customerProfile.getTitleName());
        }
        if (!StringUtils.isEmpty(customerProfile.getFirstName())) {
            query.append(firstName, customerProfile.getFirstName());
        }
        if (!StringUtils.isEmpty(customerProfile.getLastName())) {
            query.append(lastName, customerProfile.getLastName());
        }
        if (!StringUtils.isEmpty(customerProfile.getBirthDay())) {
            query.append(birthDay, customerProfile.getBirthDay());
        }
        if (!StringUtils.isEmpty(customerProfile.getCreatedDateTime())) {
            query.append(createdDateTime, customerProfile.getCreatedDateTime());
        }
        if (!StringUtils.isEmpty(customerProfile.getCustomerCisList())) {
            query.append(customerCisList, customerProfile.getCustomerCisList());
        }

        return query;
    }
}
