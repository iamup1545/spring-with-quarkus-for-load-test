package org.acme.repository.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.acme.dto.CustomerCisDto;
import org.acme.dto.CustomerProfileDto;
import org.acme.entity.CustomerCis;
import org.acme.entity.CustomerProfile;
import org.acme.repository.CustomerProfileRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("customerProfileRepository")
public class CustomerProfileRepositoryImpl implements CustomerProfileRepository {

    @Autowired
    private MongoClient mongoClient;

    public Optional<CustomerProfile> findCustomerProfileById(String id) {
        return this.findByIdOptional(new ObjectId(id));
    }

    public List<CustomerProfile> getCustomerProfileWithLimit(int limit) {
        return this.findAll().stream().limit(limit).collect(Collectors.toList());
    }
}
