package org.acme.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCis {

    @BsonProperty("_id")
    private Integer cisId;        // for generate value

    private List<AddressCis> addressCisList;

    private InvestmentLimitCis investmentLimitCis;

}
