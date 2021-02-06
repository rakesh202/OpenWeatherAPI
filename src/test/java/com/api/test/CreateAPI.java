package com.api.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.pojo.Stations;
import com.test.automation.utills.Constant;
import com.test.automation.utills.ExcelUtil;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class CreateAPI {
	
	
	String baseURL="http://api.openweathermap.org";
	String basePath="/data/3.0/stations";
	String strAPIKey="20777be1904520adb8248cdedd71d7fe";

	
	
	@DataProvider
	public Object[][] getStationData(){
		Object statioData[][]=ExcelUtil.getTestData("Sheet1");
		return statioData;
	}
	
	
	
	@Test(dataProvider="getStationData")
	public void createStationAPI(String external_id, String name, String latitude, String longitude, String altitude){
		List<String> objList=new ArrayList<String>();
		
	Stations objStation =new Stations(external_id, name,Float.parseFloat(latitude),Float.parseFloat(longitude), NumberUtils.toInt(altitude));
	io.restassured.response.Response response =RestClient.doPost(baseURL,basePath,"","JSON",true,strAPIKey,objStation );
	System.out.println(response.getStatusCode());
	System.out.println(response.prettyPrint());
	
	JsonPath objJS=RestClient.getJsonPath(response);
	System.out.println(objJS.getString("ID"));
	
	 objList.add(objJS.getString("ID"));
	 System.err.println(Arrays.toString(objList.toArray()));
	
	
	
	
	
	}
	
	
	
	
	
	
	
	

}
