package com.api.test;

import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.test.automation.utills.Constant;

import io.restassured.response.Response;
import org.testng.Assert;

public class apiWithOutAPIKeyTest {
	String baseURL="http://api.openweathermap.org";
	String basePath="/data/3.0/stations";
	String strAPIKey="20777be1904520adb8248cdedd71d7fe";
	
	@Test
	public void getAPIWithAPIId(){
		
	//	RestClient.doGet(baseURL,basePath,"");
		
		io.restassured.response.Response response =RestClient.doGet(baseURL,basePath,"","JSON",true,"" );
		Assert.assertEquals(RestClient.getStatusCode(response),Constant.HTTP_STATUS_CODE_401);
		RestClient.getPrettyResponsePrint(response);
	}
	
	

}
