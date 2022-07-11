package org.acme.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(database = "campaignManagementDB" ,collection="customerProfile")
public class CustomerProfile {

    @BsonId
    @BsonProperty("_id")
    private ObjectId customerId;                       // used by MongoDB for the _id field

    private String titleName;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private LocalDate createdDateTime;

    private List<CustomerCis> customerCisList;

    public String getFullName() {
        return this.titleName + this.firstName + " " + this.lastName;
    }

}
