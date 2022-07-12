package org.acme.repository;

import org.acme.entity.customerprofile.CustomerProfile;

import java.util.List;

public interface CustomerProfileRepository {

    List<CustomerProfile> findCustomerProfileById(String id);

    List<CustomerProfile> getCustomerProfileWithLimit(int limit);

    String add(CustomerProfile customerProfile);

    List<String> addManyProfile(List<CustomerProfile> customerProfileList);

    List<CustomerProfile> deleteManyProfile(List<CustomerProfile> customerProfileList);

}
