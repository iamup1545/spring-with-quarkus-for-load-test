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

//    public List<CustomerProfile> getCustomerProfileOnlyWithLimit(int limit) {
//        /*Query query = new Query(); // can't implement in Repository
//        query.fields().exclude("customerCisList");
//        query.limit(limit);
//        return mongoTemplate.find(query, CustomerProfile.class);*/
//
//        return this.find()
//    }

    public List<CustomerProfile> getCustomerProfileWithLimit(int limit) {
        /*Query query = new Query(); // can't implement in Repository
        query.limit(limit);
        return mongoTemplate.find(query, CustomerProfile.class);*/

//        return this.findAll().stream().limit(limit).collect(Collectors.toList());
//        return this.findAll().stream().limit(limit).collect(Collectors.toList());

        return this.findAll().stream().limit(limit).collect(Collectors.toList());
    }

//    public List<CustomerProfile> getCustomerCisWithLimit(int limit) {
//        /*Query query = new Query(); // can't implement in Repository
//        query.fields().include("customerCisList.cisId");
//        query.limit(limit);
//        return mongoTemplate.find(query,CustomerProfile.class);*/
//        return this.find("").stream().limit(limit).collect(Collectors.toList());
//    }

    public List<CustomerProfile> getAddressCisWithLimit(int limit) {
        /*Query query = new Query(); // can't implement in Repository
        query.fields().include("customerCisList.addressCisList").include("customerCisList.cisId");
        query.limit(limit);
        return mongoTemplate.find(query,CustomerProfile.class);*/

        return this.find("customerCisList.addressCisList").stream().limit(limit).collect(Collectors.toList());
    }

    public List<CustomerProfile> getInvestmentLimitCisWithLimit(int limit) {
        /*Query query = new Query(); // can't implement in Repository
        query.fields().include("customerCisList.cisId").include("customerCisList.investmentLimitCis");
        query.limit(limit);
        return mongoTemplate.find(query,CustomerProfile.class);*/

        return this.find("customerCisList.investmentLimitCis").stream().limit(limit).collect(Collectors.toList());
    }

    public List<CustomerProfileDto> getTest(int limit) {
        List<CustomerProfileDto> list = new ArrayList<>();
        CustomerProfileDto customerProfileDto;
//        CustomerCisDto customerCisDto;
        System.out.println("limit : " + limit);
        try (MongoCursor<Document> cursor = this.getCollection().find().limit(limit).cursor()) {
//            cursor = (MongoCursor<Document>) getCollection().find().limit(limit);
            while (cursor.hasNext()) {
                Document document = cursor.next();
                customerProfileDto = new CustomerProfileDto();
                customerProfileDto.setCustomerId(String.valueOf(document.getObjectId("_id")));
                customerProfileDto.setTitleName(document.getString("titleName"));
                customerProfileDto.setFirstName(document.getString("firstName"));
                customerProfileDto.setLastName(document.getString("lastName"));
                customerProfileDto.setBirthDay(document.getDate("birthDay"));
//                customerProfileDto.setCreatedDateTime(document.getDate("createdDateTime"));
//                customerCisDto = new CustomerCisDto();
//                customerCisDto.setCisId();
//                customerProfileDto.setCustomerCisList(document("customerCisList"));
                list.add(customerProfileDto);
            }
            System.out.println("Field customerCisList : " + list.size());

        } catch (Exception exception) {
            System.out.println("Error to get field customerCisList : " + exception);
        }


        return list;
    }

    private MongoCollection<Document> getCollection(){
        return this.mongoClient.getDatabase("campaignManagementDB").getCollection("customerProfile");
    }
}
