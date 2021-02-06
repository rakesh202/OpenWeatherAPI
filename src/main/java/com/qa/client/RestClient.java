package com.qa.client;

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Response;
import com.test.automation.utills.Util;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
//import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RestClient {
	
	/*******************************************************************************
	 * @Function Name : doGet
	 * @Description : Get Method API 
	 * @Author Name : Rakesh
	 ********************************************************************************/
	public static io.restassured.response.Response doGet(String strBaseURL, String strBasePath, String strId,
			String strcontentType, boolean blnlog, String strAPIKey) {
		RestClient.setBaseURL(strBaseURL);
		RequestSpecification request = RestClient.createRequest(strcontentType, strId, blnlog, strAPIKey);
		return RestClient.getResponse("GET", request, strBasePath);
	}

	/*******************************************************************************
	 * @Function Name : doPost
	 * @Description : Post Method API 
	 * @Author Name : Rakesh
	 ********************************************************************************/
	public static io.restassured.response.Response doPost(String strBaseURL, String strBasePath, String strId,
			String strcontentType, boolean blnlog, String strAPIKey, Object obj) {
		RestClient.setBaseURL(strBaseURL);
		RequestSpecification request = RestClient.createRequest(strcontentType, strId, blnlog, strAPIKey);
		String strJsonPayLoad = Util.getSerializedJson(obj);
		request.body(strJsonPayLoad);
		return RestClient.getResponse("POST", request, strBasePath);
	}
	
	/*******************************************************************************
	 * @Function Name : doDelete
	 * @Description : Delete Method API 
	 * @Author Name : Rakesh
	 ********************************************************************************/
	public static io.restassured.response.Response doDelete(String strBaseURL, String strBasePath, String strId,
			String strcontentType, boolean blnlog, String strAPIKey) {
		RestClient.setBaseURL(strBaseURL);
		RequestSpecification request = RestClient.createRequest(strcontentType, strId, blnlog, strAPIKey);
		return RestClient.getResponse("DELETE", request, strBasePath);

	}

	/*******************************************************************************
	 * @Function Name : setBaseURL
	 * @Description :
	 * @param baseURI  
	 * @Author Name : Rakesh
	 ********************************************************************************/
	
	public static void setBaseURL(String baseURI) {
		RestAssured.baseURI = baseURI;

	}
	
	
	/*******************************************************************************
	 * @Function Name : createRequest
	 * @Description :
	 * @param contentType
	 * @param strId
	 * @param log
	 * @param strAPI
	 * @return
	 * @Author Name : Rakesh
	 ********************************************************************************/
	public static RequestSpecification createRequest(String contentType,String strId,boolean log,String strAPI){
		RequestSpecification request;
		if(log){
			request=RestAssured.given().log().all();	
		}
		else{
		request=RestAssured.given();
		}
		
		if(!strId.isEmpty()){
			request.queryParam("id", strId);
		}
		
		if(!strAPI.equals("")){
			request.queryParam("APPID", strAPI);	
		}
		
		
		if(contentType.equalsIgnoreCase("JSON")){
			request.contentType(ContentType.JSON);
		}
		else if(contentType.equalsIgnoreCase("XML")){
			request.contentType(ContentType.XML);
		}
		else if(contentType.equalsIgnoreCase("TEXT"))
		{
			request.contentType(ContentType.TEXT);
		}
		return request;
		
	}
	
	
	/*******************************************************************************
	 * @Function Name : getResponse
	 * @Description :
	 * @param httpMetod
	 * @param request
	 * @param basepath
	 * @return 
	 * @Author Name : Rakesh
	 ********************************************************************************/
	
	public static io.restassured.response.Response getResponse(String httpMetod, RequestSpecification request, String basepath){
		return exceuteAPI( httpMetod,  request,  basepath);
	}
	
	
	
	/*******************************************************************************
	 * @Function Name : exceuteAPI
	 * @Description :
	 * @param httpMetod
	 * @param request
	 * @param basepath
	 * @return
	 * @Author Name : Rakesh
	 ********************************************************************************/
	private static io.restassured.response.Response exceuteAPI(String httpMetod, RequestSpecification request, String basepath) {
		io.restassured.response.Response response = null;
		switch (httpMetod) {
		case "GET":
			response=request.get(basepath);
			break;
		case "POST":
			response=request.post(basepath);
			break;
		case "DELETE":
			response=request.delete(basepath);
			break;

		default:
			break;
		}
		
		return response;
	}
	
	/*******************************************************************************
	 * @Function Name : getJsonPath
	 * @Description :
	 * @param response
	 * @return
	 * @Author Name : Rakesh
	 ********************************************************************************/

	public static JsonPath getJsonPath(io.restassured.response.Response response){
	
		return response.jsonPath();
		
	}
	
	/*******************************************************************************
	 * @Function Name : getStatusCode
	 * @Description :
	 * @param response
	 * @return
	 * @Author Name : Rakesh
	 ********************************************************************************/

	public static int getStatusCode(io.restassured.response.Response response) {

		int strStatusCode = response.getStatusCode();
		System.out.println(strStatusCode);
		return response.getStatusCode();
	}

	/*******************************************************************************
	 * @Function Name : getPrettyResponsePrint
	 * @Description :
	 *@param response
	 * @Author Name : Rakesh
	 ********************************************************************************/

	public static void getPrettyResponsePrint(io.restassured.response.Response response) {
		System.err.println("*************Response String in Pretty Format**********");
		response.prettyPrint();
	}	

}
