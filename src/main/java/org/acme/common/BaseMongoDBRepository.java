package org.acme.common;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.acme.entity.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseMongoDBRepository {

    /** Campaign Management DB **/
    public String campaignManagementDB = "campaignManagementDB";
    public static final String customerProfileCollection = "customerProfile";

    @Autowired
    private MongoClient mongoClient;

    protected MongoCollection<CustomerProfile> getCustomerProfileCollection(){
        return this.mongoClient.getDatabase(campaignManagementDB)
                .getCollection(customerProfileCollection, CustomerProfile.class);
    }
}