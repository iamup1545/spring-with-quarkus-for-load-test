//package org.acme.repository;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import org.acme.dto.CustomerCisDto;
//import org.acme.dto.CustomerProfileDto;
//import org.acme.entity.CustomerCis;
//import org.acme.entity.CustomerProfile;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository("customerProfileMongoDBClient")
//public class CustomerProfileMongoDBClient {
//
//    @Autowired
//    private MongoClient mongoClient;
//
//    public List<CustomerProfileDto> list(int limit) {
//        List<CustomerProfileDto> list = new ArrayList<>();
//        CustomerProfileDto customerProfileDto;
//        CustomerCisDto customerCisDto;
//        try (MongoCursor<Document> cursor = this.getCollection().find().limit(limit).cursor()) {
////            cursor = (MongoCursor<Document>) getCollection().find().limit(limit);
//            while (cursor.hasNext()) {
//                Document document = cursor.next();
//                customerProfileDto = new CustomerProfileDto();
//                customerProfileDto.setCustomerId(String.valueOf(document.getObjectId("_id")));
//                customerProfileDto.setTitleName(document.getString("titleName"));
//                customerProfileDto.setFirstName(document.getString("firstName"));
//                customerProfileDto.setLastName(document.getString("lastName"));
//                customerProfileDto.setBirthDay(document.getDate("birthDay"));
////                customerProfileDto.setCreatedDateTime(document.getDate("createdDateTime"));
////                customerCisDto = new CustomerCisDto();
////                customerCisDto.setCisId();
////                customerProfileDto.setCustomerCisList(document("customerCisList"));
//                list.add(customerProfileDto);
//            }
//            System.out.println("Field customerCisList : " + list.size());
//
//        } catch (Exception exception) {
//            System.out.println("Error to get field customerCisList : " + exception);
//        }
//
//
//        return list;
//    }
//
//    private MongoCollection<Document> getCollection(){
//        return mongoClient.getDatabase("campaignManagementDB").getCollection("customerProfile");
//    }
//
//}
