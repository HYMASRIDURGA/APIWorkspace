package com.api.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import com.pojo.pages.SerializationPojo;
import com.pojo.pages.SerializationPojo2;

public class Serialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		SerializationPojo s=new SerializationPojo();
	    s.setAccuracy(10);
	    s.setAddress("satya bhaskara");
	    s.setLanguage("English");
	    SerializationPojo2 loc=new SerializationPojo2();
	    loc.setLat(32.4);
	    loc.setLng(43.6);
	    s.setLocation(loc);
	    s.setName("test123");
	    s.setPhone_number("85635252234");
	    List<String> l=new ArrayList<String>();
	    l.add("add1");
	    l.add("add2");
	    s.setTypes(l);
	    s.setWebsite("SerializationPojo");
		 Response res=given().log().all().queryParam("key", "qaclick123").body(s)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response();
		 String response=res.asString();
		 System.out.println(response);
		

	}

}
