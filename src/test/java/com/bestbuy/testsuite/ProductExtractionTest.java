package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductExtractionTest {
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
    // Extract the limit
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is :" + limit);
    }

    // Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("The total is: " + total);
    }

    // Extract the name of 5th product
    @Test
    public void nameOfFifthProduct() {
        String fifthProductName = response.extract().path("data[4].name");
        System.out.println("The name of 5Th product :" + fifthProductName);
    }

    @Test
    //Extract the names of all the product
    public void extractNameOfAllProduct() {
        List<String> productNameList = response.extract().path("data.name");
        System.out.println("The names of All product : " + productNameList);
    }

    @Test
    //Extract the productId of all the products
    public void extractProductIdOfAll() {
        List<Integer> productIdForAll = response.extract().path("data.id");
        System.out.println("All store ids are :" + productIdForAll);
    }

    @Test
    //Print the size of the data list
    public void sizeOfDataList() {
        List<?> dataSize = response.extract().path("data.length");
        System.out.println("Size of data list :" + dataSize.size());
    }

    @Test
    //Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    public void getValueForProductEnergizer() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: " + values);
    }
    @Test
    //Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    public void getModelOfProductEnergizer(){
        List<HashMap<String, ?>>  model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("model of the product :" + model);
    }
    @Test
    //Get all the categories of 8th product
    public void getAllCategoriesOf8thProduct(){
        List<String> categoriesList = response.extract().path("data[7].categories");
        System.out.println("8th Product categories List :" + categoriesList);
    }
    @Test
    //Get categories of the store where product id = 150115
    public void getCategoriesForProduct150115(){
        List<HashMap<String,?>> categoriesList = response.extract().path("data.findAll{it.id == '150115'}.categories");
        System.out.println("categories of the store product id 150115 :" + categoriesList);
    }
    @Test
    // Get all the descriptions of all the product
    public void getAllDescriptionOfProduct(){
        List<String> descriptionList = response.extract().path("data.description");
        System.out.println("All product descriptions are :" + descriptionList);
    }
    @Test
    //Get id of all the all categories of all the products
    public void getIdOfAllProductCategories(){
        List<String> categoriesList = response.extract().path("data.categories.id");
        System.out.println("All categories of all products are :" + categoriesList);
    }
    @Test
    //Find the product names Where type = HardGood
    public void findTheProductHardGood(){
        List<String> hardGoodProductList = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("list of product where type HardGood :" + hardGoodProductList);
    }
    @Test
    // Find the Total number of categories for the product where product name = Duracell - AA
    //1.5V CopperTop Batteries (4-Pack)

    public void totalNumberOfCategories(){
        List<HashMap<String,?>> duracellCategoriesList = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("Total number of categories for Duracell :" + duracellCategoriesList);
    }
    @Test
    //Find the createdAt for all products whose price < 5.49
    public void productPriceCreatedAt(){
        List<?> createdAtList = response.extract().path("data.findAll{it.price < 5.49}.categories.createdAt");

        System.out.println("createdAt for product price < 5.49 :" + createdAtList);
    }
    @Test
    // Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)
    public void findCategoriesForEnergizer(){
      List<String> categoriesList = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("categories list for Energizer: " + categoriesList);
    }
    @Test
    //Find the manufacturer of all the products
    public void manufacturerOfAllProducts(){
        List<String> manufactureList = response.extract().path("data.manufacturer");
        System.out.println("manufacturer od all product :" + manufactureList);
    }
    @Test
    // Find the image of products whose manufacturer is = Energizer
    public void imageOfProductForEnergizer(){
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("image of product is :" + image);
    }

    @Test
    //Find the createdAt for all categories products whose price > 5.99
    public void findCreatedAtForProduct(){
        List<?> createdAtList = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("createdAt for product price > 5.99 :" + createdAtList);
    }
    @Test
    // Find the url of all the products
    public void findUrlOfAllProduct(){
        List<String> allUrl = response.extract().path("data.url");
        System.out.println("All products Url :" + allUrl);
    }







}
