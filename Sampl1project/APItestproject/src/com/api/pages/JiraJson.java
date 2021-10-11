package com.api.pages;

import static io.restassured.RestAssured.*;


import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="http://localhost:8080";
		
		//Login to JIRA
		SessionFilter session=new SessionFilter();
		String response=given().relaxedHTTPSValidation().header("Content-Type","application/json").body("{ \"username\": \"hymasri888\", \"password\": \"6th@October\"}")
				.log().all().filter(session).when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		
		// Add Comment
		String comment="this is a new comment";
		String createdcomment=given().pathParams("key","10005").log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \""+comment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}")
		.filter(session).when().post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js1=new JsonPath(createdcomment);
		String actualid=js1.get("id");
		
		//Add Attachment
		given().log().all().pathParam("key", "10005").filter(session).header("X-Atlassian-Token","no-check").header("Content-Type","multipart/form-data")
		.multiPart("file",new File("testattachment.txt"))
		.when().post("/rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		//get API
		
		String getresponse=given().log().all().filter(session).pathParam("key","10005").queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{key}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(getresponse);
		JsonPath js2=new JsonPath(getresponse);
		int count=js2.getInt("fields.comment.comments.size()");
		for(int i=0;i<count;i++)
		{
			String commentid1=js2.get("fields.comment.comments["+i+"].id").toString();
			
			if(commentid1.equalsIgnoreCase(actualid))
			{
				String actualcomment=js2.get("fields.comment.comments["+i+"].body");
				System.out.println(actualcomment);
				Assert.assertEquals(actualcomment,comment);
			}
			
		}
	

	}

}
