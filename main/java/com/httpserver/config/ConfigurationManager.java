package com.httpserver.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.httpserver.util.Json;

public class ConfigurationManager {
	
	//singleton instance to return 
	private static ConfigurationManager myConfigManager;
	
	private static Configuration myCurrentConfig;
	
	//construct
	private ConfigurationManager() {}
	
	//first creation
	public static ConfigurationManager getConfigInstance() {
		if(myConfigManager==null) {
			myConfigManager= new ConfigurationManager();
		}
		return myConfigManager;
	}
	
	//loader txt file -> to json
	public void loadConfigFile (String path)   {
		FileReader fr = null;
		
		try {
		 fr = new FileReader(path);
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
		}
		
		
		StringBuffer sb = new StringBuffer();
		int i=0;
		try {
			while((i = fr.read()) != -1) {
				sb.append((char)i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonNode conf;
		try {
			
			conf = Json.parse(sb.toString());
			//log JSON
			//System.out.println(Json.generateJson(conf));  
		} catch (IOException e) {
			
			throw new RuntimeException("error parsin json");
		}
		try {
			myCurrentConfig = Json.fromJson(conf, Configuration.class);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	//return config
	public Configuration getCurrentConfig () {
		if(myCurrentConfig== null) {
			throw new RuntimeException("no config setup");
		}
		return myCurrentConfig;
	}
	
	
}
