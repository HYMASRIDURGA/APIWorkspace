package com.api.pages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import com.payload.pages.Payload1;
import com.payload.pages.Reusablemethods;

public class AddPlace {
	
	public static void main(String[] args) throws IOException {
		
		//given - all the inputs
		//when - submitting API - resource and http method
		//then - validating the response
		
		//add place
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String res=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Documents\\REQ"))))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println(res);
		JsonPath js=Reusablemethods.code(res);
		String place=js.getString("place_id");
		System.out.println(place);
		
		// update the place
		
		String newaddress="satya bhaskara heights, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		System.out.println(newaddress);
		
		//get place
		
		String getresponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",place)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js1=Reusablemethods.code(getresponse);
		String actualaddress=js1.getString("address");
		System.out.println(actualaddress);
		Assert.assertEquals(actualaddress,newaddress);
		
	}

}
