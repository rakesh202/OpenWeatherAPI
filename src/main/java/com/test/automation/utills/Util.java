package com.test.automation.utills;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String getSerializedJson(Object obj){
		String jsonString=null;
		ObjectMapper objMapper=new ObjectMapper();
		try{
			jsonString=objMapper.writeValueAsString(obj);
		}
		catch(Exception e){
			e.printStackTrace();
			return jsonString;
		}
		
		return jsonString;
	}

}
