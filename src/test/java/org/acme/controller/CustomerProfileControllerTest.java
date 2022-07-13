package org.acme.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CustomerProfileControllerTest {

    @Test
    @DisplayName("Test get customer profile by id")
    public void testGetCustomerProfileById() {

        String url = "/getCustomerProfileById/62c98be8d5b44345b82803c1";

        String response = RestAssured.given().accept(ContentType.JSON).request()
                .contentType(ContentType.JSON)
                .when().get(url).then()
                .statusCode(200).extract().response().getBody().asString();

        System.out.println("Response : " + response);
    }

}

