package com.httpserver.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {

	public static ObjectMapper myObjectMapper = defaultMapper();

	public static ObjectMapper defaultMapper() {
		ObjectMapper om = new ObjectMapper();
		// not crash on json error
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return om;
	}
	
	
	public  static JsonNode parse(String rawJson) throws IOException {
		return myObjectMapper.readTree(rawJson);
	}
	
	public static <A> A fromJson (JsonNode node, Class<A> aClass) throws JsonProcessingException {
		return myObjectMapper.treeToValue(node, aClass);
	}
	
	public static JsonNode toJson (Object obj) {
		return myObjectMapper.valueToTree(obj);
	}
	
	//generates pretty json
	public static String generateJson (Object o) throws JsonProcessingException {
		ObjectWriter objWriter = myObjectMapper.writer()
				.with(SerializationFeature.INDENT_OUTPUT);
		
		return objWriter.writeValueAsString(o);
	}
	
	public static String stringify (JsonNode node) throws JsonProcessingException {
		return generateJson(node);
	}		
		
	

}
