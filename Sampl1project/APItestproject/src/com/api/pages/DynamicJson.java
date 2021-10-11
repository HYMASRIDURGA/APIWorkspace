package com.api.pages;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.payload.pages.Payload1;
import com.payload.pages.Reusablemethods;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class DynamicJson {
	@Test(dataProvider="Booksdata",enabled=false)
	public void addBook(String isbn,String aisle)
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		
		//add book
		String response=given().log().all().header("Content-Type","application/json").body(Payload1.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		 JsonPath js=Reusablemethods.code(response);
		  String id1=js.get("ID");
		  System.out.println(id1);
		  
	}
		  
	      //delete book
	   @Test(dataProvider="Booksdata",enabled=true)    
	   //Based on enabled value add or delete will work // here delete will work
		  public void deletebook(String isbn,String aisle )
		  {
		  RestAssured.baseURI="http://216.10.245.166";
		  String response1=given().log().all().header("Content-Type","application/json").body(Payload1.deleteBook(isbn,aisle))
		  .when().delete("Library/DeleteBook.php")
		  .then().log().all().assertThat().statusCode(200)
		  .extract().response().asString();
		  JsonPath js1=Reusablemethods.code(response1);
		  String message=js1.get("msg");
		  System.out.println(message); 	
		  }
	
   @DataProvider(name="Booksdata")
   public Object[][] data()
   {
	   //array - collection of element  //Two dimensional array - collection of arrays
	 return new Object[][] { {"teste11","011"},{"teset22","022"},{"teset33","033"} };
   }
    
	

}
