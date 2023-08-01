package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores" )
                .then().statusCode(200);
    }
    // Verify the if the total is equal to 1561
    @Test
    public void verifyTotalEqualTo1561(){
        response.body("total",equalTo(1561));
    }
    //Verify the if the stores of limit is equal to 10
    @Test
    public void verifyTheLimitEqualTo10(){
        response.body("limit",equalTo(10));
    }
    //Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void checkSingleNameInArrayList(){
        response.body("data.name",hasItem("Inver Grove Heights"));
    }
    //Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void checkMultipleNameInArrayList(){
        response.body("data.name",hasItems("Roseville","Burnsville","Maplewood"));
    }
    //verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void storeIdOfThirdStoreOfSecondServices(){
        response.body("data[2].services[1]",hasKey("id"));

    }
    //Check hash map values ‘createdAt’ inside store services map where store name = Roseville
    @Test
    public void checkHashMapValue(){
        response.body("data[2]",hasKey("createdAt"));
    }
    //Verify the state = MN of forth store
    @Test
    public void verifyStateMN(){
      //  response.body("data[3]",hasKey("state"));
        response.body("data.findAll{it.state == 'MN'}",hasItem(hasEntry("state","MN")));
     //   response.body("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}",hasItem(hasEntry("manufacturer","Energizer")));
    }
    //Verify the store name = Rochester of 9th store
    @Test
    public void verifyStoreNameRochester(){
    //response.body("data[8].name",hasValue("Rochester"));
        response.body("data.findAll{it.name == 'Rochester'}",hasItem(hasEntry("name","Rochester")));
    }
    //Verify the storeId = 11 for the 6th store
    @Test
    public void verifyStoreId11(){
        response.body("data[5].services.findAll{it.storeId == '11'}", hasItem(hasEntry("storeId","11")));
    }
    //Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyTheServiceId(){
         response.body("data[6].services[3].storeservices",hasItems(hasEntry("serviceId","4")));
    }


}
