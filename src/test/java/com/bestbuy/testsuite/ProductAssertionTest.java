package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    @Test
    //Verify the if the total is equal to 51957
    public void verifyTotal() {
        response.body("total", equalTo(51957));
    }

    @Test
    //Verify the if the stores of limit is equal to 10
    public void verifyStoreLimit() {
        response.body("limit", equalTo(10));
    }

    @Test
    //Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack)
    public void singleNameInArrayList() {
        response.body("data.name", hasItem("Duracell - AA Batteries (8-Pack)"));
    }

    @Test
    //Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
    //Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    public void checkMultipleName() {
        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));
    }

    @Test
    // Verify the productId=150115 inside categories of the third name is “Household Batteries”
    public void verifyProductId() {
        // response.body("data.findAll{it.id =='150115'}",hasItem(hasEntry("name","Household Batteries")));
        response.body("data[3].categories.findAll{it.name=='Household Batteries'}", hasItem(hasEntry("name", "Household Batteries")));
    }

    @Test
    //Verify the categories second name = “Housewares” of productID = 333179
    public void categoriesSecondName() {
        response.body("data[8].categories.findAll{it.name=='Housewares'}", hasItem(hasEntry("name", "Housewares")));
    }

    @Test
    //Verify the price = 4.99 of forth product
    public void verifyPriceFourthProduct() {
        response.body("data[3].price", equalTo(4.99f));
    }

    @Test
    //  Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
    public void verifyProductName() {
        response.body("data[5].name",equalTo("Duracell - D Batteries (4-Pack)"));
    }
    @Test
    //Verify the ProductId = 333179 for the 9th product
    public void verifyProductId33179(){
        response.body("data[8].id" ,equalTo("333179"));
    }
    @Test
    //Verify the prodctId = 346575 have 5 categories
    public void verifyProductId346575(){
    response.body("data[9].categories.size",equalTo("5"));
    }





}