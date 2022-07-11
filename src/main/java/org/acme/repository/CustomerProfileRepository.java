package org.acme.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.acme.dto.CustomerProfileDto;
import org.acme.entity.CustomerProfile;

import java.util.List;
import java.util.Optional;

public interface CustomerProfileRepository extends PanacheMongoRepository<CustomerProfile> {

    Optional<CustomerProfile> findCustomerProfileById(String id);

    List<CustomerProfile> getCustomerProfileWithLimit(int limit);

//    List<CustomerProfile> getCustomerCisWithLimit(int limit);

//    List<CustomerProfile> getAddressCisWithLimit(int limit);

//    List<CustomerProfile> getInvestmentLimitCisWithLimit(int limit);

    List<CustomerProfileDto> getTest(int limit);

}
