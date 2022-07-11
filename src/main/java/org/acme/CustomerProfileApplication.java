package org.acme;

import com.github.javafaker.Faker;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.acme.entity.AddressCis;
import org.acme.entity.CustomerCis;
import org.acme.entity.CustomerProfile;
import org.acme.entity.InvestmentLimitCis;
import org.acme.repository.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@QuarkusMain
public class CustomerProfileApplication {

    public static void main(String... args) {
        Quarkus.run(args);
    }

//    public static void main(String... args) {
//        Quarkus.run(MyApp.class, args);
//    }

    public static class MyApp implements QuarkusApplication {

        @Autowired
        CustomerProfileRepository customerProfileRepository;

        @Override
        public int run(String... args) throws Exception {
            System.out.println("hello world : QuarkusApplication");
            Date date = new Date();
            List<CustomerProfile> customerProfileList;
            for (int a = 0; a < 400; a++) {
                customerProfileList = new ArrayList<>();
                for (int i = 0; i < 1000; i++) {
                    CustomerProfile customerProfile = new CustomerProfile();
                    Faker faker = new Faker();
                    String titleName = faker.name().prefix();
                    String firstName = faker.name().firstName();
                    String lastName = faker.name().lastName();
                    Date birthDate = faker.date().birthday();
                    LocalDate createTime = LocalDate.now();
                    customerProfile.setTitleName(titleName);
                    customerProfile.setFirstName(firstName);
                    customerProfile.setLastName(lastName);
                    customerProfile.setBirthDay(birthDate);
                    customerProfile.setCreatedDateTime(createTime);

                    System.out.println(customerProfile.toString()); // to string

                    List<CustomerCis> customerCisList = new ArrayList<>();
                    for (int j = 0; j < 2; j++) {
                        CustomerCis customerCis = new CustomerCis();
                        customerCis.setCisId(j);
                        List<AddressCis> addressCisList = new ArrayList<>();
                        InvestmentLimitCis investmentLimitCis = new InvestmentLimitCis();
                        for (int k = 0; k < 2; k++) {
                            AddressCis addressCis = new AddressCis();
                            addressCis.setCountry(faker.address().country());
                            addressCis.setAddressName(faker.address().fullAddress());
                            addressCis.setZipCode(faker.address().zipCode());
                            addressCisList.add(addressCis);
                        }
                        customerCis.setAddressCisList(addressCisList);
                        investmentLimitCis.setOverLimit(BigDecimal.valueOf(faker.number().numberBetween(1000, 10000000)));
                        investmentLimitCis.setCreatedBy(faker.name().fullName());
                        createTime = LocalDate.now();
                        investmentLimitCis.setCreateDate(createTime);
                        customerCis.setInvestmentLimitCis(investmentLimitCis);
                        customerCisList.add(customerCis);
                    }
                    customerProfile.setCustomerCisList(customerCisList);

                    customerProfileList.add(customerProfile);
                }

                customerProfileRepository.addManyProfile(customerProfileList);
            }

            System.out.println("Insert finished : QuarkusApplication");

            Quarkus.waitForExit();
            return 0;
        }
    }


}
