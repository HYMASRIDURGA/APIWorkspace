package com.api.pages;

import org.testng.annotations.Test;

import com.payload.pages.Payload1;

import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourses() {
		//Verify if Sum of all Course prices matches with Purchase Amount
		int sum=0;
		JsonPath js=new JsonPath(Payload1.coursePrice());
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{

			int prices=js.get("courses["+i+"].price");
			int copy=js.get("courses["+i+"].copies");
			int multiplication=prices * copy;
			System.out.println(multiplication);
			sum=sum+multiplication;	
		
		}
		System.out.println(sum);
	}
}
