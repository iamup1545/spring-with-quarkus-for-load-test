package org.acme.repository.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import org.acme.common.db.BaseMongoDBRepository;
import org.acme.entity.customerprofile.CustomerProfile;
import org.acme.repository.CustomerProfileRepository;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("customerProfileRepository")
public class CustomerProfileRepositoryImpl extends BaseMongoDBRepository implements CustomerProfileRepository {

    private static final String collectionName = "customerProfile";

    /** Fields in collection **/
    public static final String _id = "_id";
    public static final String titleName = "titleName";
    public static final String firstName = "firstName";
    public static final String lastName = "lastName";
    public static final String birthDay = "birthDay";
    public static final String createdDateTime = "createdDateTime";
    public static final String customerCisList = "customerCisList";

    @Autowired
    protected MongoClient mongoClient;

    @Autowired
    protected Vertx vertx;

    public CustomerProfileRepositoryImpl(MongoClient mongoClient, Vertx vertx) {
        super(mongoClient, vertx);
        this.mongoClient = mongoClient;
        this.vertx = vertx;
    }

    @Override
    public void setDBCollectionName() {
        this.dbCollectionName = collectionName;
    }

    public Uni<Object[]> getCustomerProfileWithMounteBank() {
        return super.webClient.get( "/getDocument")
                .send()
                .onItem()
                .transform(resp -> {
                    if (resp.statusCode() == 200) {
                        return resp.bodyAsJson(Object[].class);
                    } else {
                        throw new RuntimeException("");
                    }
                });
    }

    public List<CustomerProfile> findCustomerProfileById(String id) {
        List<CustomerProfile> customerProfileList = new ArrayList<>();
        Document query = new Document();
        query.append(_id, new ObjectId(id));

        try (MongoCursor<CustomerProfile> cursor = super.getCollection(CustomerProfile.class).find(query).cursor()) {
            this.mapCustomerProfileList(cursor, customerProfileList);
        } catch (Exception exception) {
            System.err.println("Error!! : " + exception);
        }

        return customerProfileList;
    }

    public List<CustomerProfile> getCustomerProfileWithLimit(int limit) {
        List<CustomerProfile> customerProfileList = new ArrayList<>();

        try (MongoCursor<CustomerProfile> cursor = super.getCollection(CustomerProfile.class).find().limit(limit).cursor()) {
            this.mapCustomerProfileList(cursor, customerProfileList);
        } catch (Exception exception) {
            System.err.println("Error!! : " + exception);
        }

        return customerProfileList;
    }

    public String add(CustomerProfile customerProfile) {
        return String.valueOf(super.getCollection(CustomerProfile.class).insertOne(customerProfile).getInsertedId());
    }

    public List<String> addManyProfile(List<CustomerProfile> customerProfileList) {
        List<String> insertedIds = new ArrayList<>();

        for (CustomerProfile customerProfile : customerProfileList) {
            customerProfile.setCreatedDateTime(LocalDate.now());
        }

        Map<Integer, BsonValue> integerBsonValueMap = super.getCollection(CustomerProfile.class)
                .insertMany(customerProfileList).getInsertedIds();

        for (Map.Entry<Integer, BsonValue> insertedId : integerBsonValueMap.entrySet()) {
            insertedIds.add(String.valueOf(insertedId.getValue().asObjectId().getValue()));
        }

        return insertedIds;
    }

    public List<CustomerProfile> deleteManyProfile(List<CustomerProfile> customerProfileList) {
        List<CustomerProfile> deleteProfileList = new ArrayList<>();
        for (CustomerProfile customerProfile : customerProfileList) {
            deleteProfileList.add(super.getCollection(CustomerProfile.class)
                    .findOneAndDelete(this.validateAndAppendQueryToDocument(customerProfile)));
        }

        return deleteProfileList;
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

    private void mapCustomerProfileList(MongoCursor<CustomerProfile> cursor, List<CustomerProfile> customerProfileList) {
        CustomerProfile customerProfile;
        while (cursor.hasNext()) {
            CustomerProfile document = cursor.next();
            customerProfile = new CustomerProfile();
            customerProfile.setCustomerId(document.getCustomerId());
            customerProfile.setTitleName(document.getTitleName());
            customerProfile.setFirstName(document.getFirstName());
            customerProfile.setLastName(document.getLastName());
            customerProfile.setBirthDay(document.getBirthDay());
            customerProfile.setCreatedDateTime(document.getCreatedDateTime());
            customerProfile.setCustomerCisList(document.getCustomerCisList());
            customerProfileList.add(customerProfile);
        }
    }
}
