package com.api.test;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.test.automation.utills.Constant;

import io.restassured.path.json.JsonPath;



public class apiGetAPI {
	
	
	String baseURL="http://api.openweathermap.org";
	String basePath="/data/3.0/stations";
	String strAPIKey="20777be1904520adb8248cdedd71d7fe";

	
	@Test
	public void getAPIWithAPIId (){
		io.restassured.response.Response response =RestClient.doGet(baseURL,basePath,"","JSON",true,strAPIKey );
		Assert.assertEquals(RestClient.getStatusCode(response),Constant.HTTP_STATUS_CODE_200);
		RestClient.getPrettyResponsePrint(response);
		JsonPath objJS=RestClient.getJsonPath(response);
	}
	

}
