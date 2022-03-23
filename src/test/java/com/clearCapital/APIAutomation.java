package com.clearCapital;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class APIAutomation {

    @BeforeAll
    public static void setUp(){
        baseURI = "https://datausa.io";
        basePath = "/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void getRequest() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .queryParam("drilldowns", "State")
                .queryParam("measures", "Population")
                .queryParam("year", "latest").
        when()
                .get("/data").
        then()
                .assertThat()
                .statusCode(200)
                .extract().jsonPath();
        List<String> states = jsonPath.getList("data.State");
        List<String> years = jsonPath.getList("data.Year");

        // Validate that the year is the same in all elements of the data array
        for (int i = 0; i < years.size(); i++) {
            for (int j = i+1; j < years.size(); j++) {
                assertEquals(years.get(i), years.get(j));
            }
        }

        // Validate that each state name is unique in data array
        for (int i = 0; i < states.size(); i++) {
            for (int j = i+1; j < states.size(); j++) {
                assertNotEquals(states.get(i), states.get(j));
            }
        }
    }
}
