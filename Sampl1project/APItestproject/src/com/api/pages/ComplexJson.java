package com.api.pages;

import com.payload.pages.Payload1;

import io.restassured.path.json.JsonPath;

public class ComplexJson {
	
 public static void main(String[] args)
{
	JsonPath jp=new JsonPath(Payload1.coursePrice());
	//Print No of courses returned by API
	int count=jp.getInt("courses.size()");
	System.out.println(count);
	//Print Purchase Amount
	int amount=jp.getInt("dashboard.purchaseAmount");
	System.out.println(amount);
	//Print Title of the first course
	String title1=jp.get("courses[2].title");
	System.out.println(title1);
	//Print All course titles and their respective Prices
	for(int i=0;i<count;i++)
	{
		String allcourses=jp.get("courses["+i+"].title");
		System.out.println(jp.get("courses["+i+"].price").toString());
		System.out.println(allcourses);
	}
	//Print no of copies sold by RPA Course
	System.out.println("Print no of copies sold by RPA Course");
	for(int i=0;i<count;i++)
	{
		String allcourses=jp.get("courses["+i+"].title");
		if(allcourses.equalsIgnoreCase("RPA"))
		{
			int copy=jp.get("courses["+i+"].copies");
			System.out.println(copy);
			break;  // to stop the next iteration
		}
	}

}

}
