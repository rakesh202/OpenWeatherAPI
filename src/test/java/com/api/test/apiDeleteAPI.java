package com.api.test;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.test.automation.utills.Constant;

import io.restassured.path.json.JsonPath;

public class apiDeleteAPI {
	
	String baseURL="http://api.openweathermap.org";
	String basePath="/data/3.0/stations";
	String strAPIKey="20777be1904520adb8248cdedd71d7fe";
	
	
@Test
	
	public void getDeleteAPI(){
		io.restassured.response.Response response =RestClient.doGet(baseURL,basePath,"","JSON",true,strAPIKey );
		Assert.assertEquals(RestClient.getStatusCode(response),Constant.HTTP_STATUS_CODE_200);
		RestClient.getPrettyResponsePrint(response);
		JsonPath objJS=RestClient.getJsonPath(response);
		//System.out.println(objJS.getString("ID"));
		//System.out.println(objJS.get().toString());
		ArrayList result=objJS.get();
		//System.err.println(result.size());
		
		for(int i=0;i<result.size();i++){
			
			Map<Object,Object> objMap=(Map<Object,Object>)result.get(i);
			
			for(Map.Entry<Object,Object> objEntry:objMap.entrySet()){
				if(objEntry.getKey().toString().equalsIgnoreCase("id")){
				System.err.println(objEntry.getValue());
				
				io.restassured.response.Response objResponse =RestClient.doDelete(baseURL,basePath+"/"+objEntry.getValue().toString(),"","JSON",true,strAPIKey );
				Assert.assertEquals(RestClient.getStatusCode(objResponse),Constant.HTTP_STATUS_CODE_204);
				RestClient.getPrettyResponsePrint(objResponse);
				
				
				io.restassured.response.Response objResponse1 =RestClient.doDelete(baseURL,basePath+"/"+objEntry.getValue().toString(),"","JSON",true,strAPIKey );
				Assert.assertEquals(RestClient.getStatusCode(objResponse1),Constant.HTTP_STATUS_CODE_404);
				RestClient.getPrettyResponsePrint(objResponse1);
				
				
				
				}
			}
			
			
			
			
		}
		
		
		//System.err.println(result.get(0));
		
		
		
		}
	

}
