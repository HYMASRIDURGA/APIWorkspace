package com.payload.pages;

import io.restassured.path.json.JsonPath;

public class Reusablemethods {
	
	public static JsonPath code(String response)
	{
		JsonPath js1=new JsonPath(response);
		return js1;
	}

}
