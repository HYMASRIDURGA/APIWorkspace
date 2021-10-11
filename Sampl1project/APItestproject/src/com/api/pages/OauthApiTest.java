package com.api.pages;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.pojo.pages.Api;
import com.pojo.pages.GetCourses;
import com.pojo.pages.WebAutomation;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
public class OauthApiTest {
	
	public static void main(String[] args) {
		
		String[] expectedcoursetitle= {"Selenium Webdriver Java","Cypress","Protractor"};
		
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjCjOtJ6uWbHedZhN_CAiKjCoOyEYAtjrIn_KOtz6Fz9lK4FfeDmxlBOoMr8pCFGA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		String partialcode=url.split("code")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
		
		
		String accesstokenresponse=given().urlEncodingEnabled(false).queryParam("code",code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		 JsonPath js=new JsonPath(accesstokenresponse);
		 String accesstoken=js.getString("access_token");
		 System.out.println(accesstoken);
		 
		
		 GetCourses gc=given().queryParam("access_token","ya29.a0ARrdaM9lUN191oxK70OaKrZDpegOna0CjeRK1CKChQnNauO2ouAjEnCgl1xhXDMjFLehAKhSSvor-AUGUw7OK0FOpTztKVwdCg7vH_LxZ0xXVMl-ZhTlKgk5lxsEaC74c-sb2C-NJlT-sFLiDAoB7ff0_nQB1Q")
	.expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		 
		 System.out.println(gc.getInstructor());
		 System.out.println(gc.getLinkedIn());
		 System.out.println(gc.getExpertise());
		 System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		 
		 List<Api> s=gc.getCourses().getApi();
		
		for(int i=0;i<s.size();i++)
		{
			if(s.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
				System.out.println(s.get(i).getPrice());	
		}
		System.out.println(gc.getCourses().getMobile().get(0).getPrice());
		
		ArrayList<String> actual=new ArrayList<String>();
		List<WebAutomation> s1=gc.getCourses().getWebAutomation();
		for(int i=0;i<s1.size();i++)
		{
			actual.add(s1.get(i).getCourseTitle());
		}
		List<String> expected=Arrays.asList(expectedcoursetitle);
		Assert.assertTrue(actual.equals(expected));
		
	
		 
	//System.out.println(actualresponse);
	
		
	}

}
