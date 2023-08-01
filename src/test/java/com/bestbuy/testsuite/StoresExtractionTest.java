package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
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
    @Test
    //Extract the limit
    public void extractLimit(){
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is :" + limit);
    }
    //Extract the total
    @Test
    public void extractTotal(){
        int total = response.extract().path("total");
        System.out.println("The total is: " + total);
    }
    @Test
    //Extract the name of 5th store
        public void nameOfFifthStore(){
        String fifthStoreName = response.extract().path("data[4].name");
        System.out.println("The name of 5Th store :" + fifthStoreName);
    }
    @Test
    //Extract the names of all the store
    public void extractNameOfAllStore(){
        List<String> storeNameList = response.extract().path("data.name");
        System.out.println("The names of All store : " + storeNameList);
    }
    @Test
    //Extract the storeId of all the store
    public void extractStoreIdOfAll(){
        List<Integer> storeIdForAll = response.extract().path("data.id");
        System.out.println("All store ids are :" + storeIdForAll);
    }
    @Test
    // Print the size of the data list
    public void sizeOfDataList(){
        List<?> dataSize = response.extract().path("data.length");
        System.out.println("Size of data list :" + dataSize.size());
    }
    @Test
    //Get all the value of the store where store name = St Cloud
    public void getValueOfStoreStCloud(){
        List<HashMap<String,?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("value of store name St Cloud : " + values);
    }
    @Test
    //Get the address of the store where store name = Rochester
    public void getAddressRochester(){
    List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("address of store Rochester is : " + address);
    }
    @Test
    //Get all the services of 8th store
    public void getAllServices8thStore(){
        List<HashMap<String,?>> allServicesList = response.extract().path("data[7].services");
        System.out.println("All services of 8th Store :" + allServicesList);
    }
    @Test
    //Get storeservices of the store where service name = Windows Store
    public void getStoreServicesForWindowsStore(){
        List<HashMap<String,?>> storeServicesList = response.extract().path("data[0].services.findAll{it.name == 'Windows Store'}.storeservices");
        System.out.println("get storeservices for Window store :" + storeServicesList);
    }
    @Test
    //Get all the storeId of all the store
    public void getAllStoreIdOfAllStore(){
        List<Integer> storeIdForAll = response.extract().path("data.services.storeservices.storeId");
        System.out.println("All store ids are :" + storeIdForAll);
    }
    @Test
    //Get id of all the store
    public void getIdOfAllStore(){
        List<Integer> storeIdForAll = response.extract().path("data.id");
        System.out.println("All store ids are :" + storeIdForAll);
    }
    @Test
    //Find the store names Where state = ND
    public void findStoreNameForStateND(){
        List<String> storeNameList = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("Find all store name where state = ND :" + storeNameList);
    }
    @Test
    //Find the Total number of services for the store where store name = Rochester
    public void totalNumberOfServicesForRochester(){
        List<String> serviceList =  response.extract().path("data.findAll{it.name == 'Rochester'}.services[0]");
        System.out.println("total number of services are :" + serviceList.size());
    }
    @Test
    //Find the createdAt for all services whose name = “Windows Store”
    public void createdAtForWindowStore(){
        List<String> valueForCreatedAt = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("createdAt value for window store is :" + valueForCreatedAt);
    }
    @Test
    //Find the name of all services Where store name = “Fargo”
    public void nameOfServicesFargo(){
        List<String> fargoServiceList = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("All services at Fargo store :" + fargoServiceList);
    }
    @Test
    //Find the zip of all the store
    public void findZipOfAllStore(){
        List<Integer> zipOfAllStore = response.extract().path("data.zip");
        System.out.println("List of zip of all store :" + zipOfAllStore);
    }
    @Test
    //Find the zip of store name = Roseville
    public void zipOfStoreRoseville(){
      List<String> zipOfRoseville = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("zip of Roseville :" + zipOfRoseville);
    }
    @Test
    //Find the storeservices details of the service name = Magnolia Home Theater
    public void storeServiceDetail(){
        List<HashMap<String,?>>  serviceDetail = response.extract().path("data.services.finAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("Store service detail for Magnolia Home Theater : " + serviceDetail);
    }
     @Test
    //Find the lat of all the stores
    public void latOfAllStore(){
      List<Double> lat = response.extract().path("data.lat");
         System.out.println("lat of all stores are :" + lat);
     }





}
