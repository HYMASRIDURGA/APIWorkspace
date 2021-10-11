package com.api.pages;

import org.testng.annotations.Test;

import com.payload.pages.Payload1;
import com.payload.pages.Reusablemethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson1 {
	@Test
	public void addBook()
	{
		//add book by passing data as an argument in body itself
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("Content-Type","application/json").body(Payload1.addBook("testbook123","1414"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		 JsonPath js=Reusablemethods.code(response);
		 String id=js.get("ID");
		 System.out.println(id);
		 
		 // delete the created book
		 
		 String response1=given().log().all().header("Content-Type","application/json").body(Payload1.deleteBook("testbook123","1414"))
				  .when().delete("Library/DeleteBook.php")
				  .then().assertThat().statusCode(200)
				  .extract().response().asString();
				  JsonPath js1=Reusablemethods.code(response1);
				  String message=js1.get("msg");
				  System.out.println(message);
				  
				  
		
		
		
	}

}
