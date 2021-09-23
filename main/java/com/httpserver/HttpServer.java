package com.httpserver;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.httpserver.config.Configuration;
import com.httpserver.config.ConfigurationManager;
import com.httpserver.core.ServerListenerThread;



//driver class  80 http   443 https
public class HttpServer {
	
	private static Logger LOGGER= LoggerFactory.getLogger(HttpServer.class);

	public static void main (String [] args) {
		LOGGER.info("server starting" );
		ConfigurationManager.getConfigInstance().loadConfigFile("src/resources/http.json");
		Configuration conf = ConfigurationManager.getConfigInstance().getCurrentConfig();
		
		LOGGER.info("getting conf from conf object:");
		LOGGER.info(conf.getPort() +": "+conf.getWebroot());
		//1. handled tcp conection
		
		try {
			ServerListenerThread myServerListener = new ServerListenerThread(conf.getPort(), conf.getWebroot());
			myServerListener.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
