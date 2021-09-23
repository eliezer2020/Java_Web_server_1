package com.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.LoggerFactory;

public class HttpConnectionWorker extends Thread{
	private Socket socket;
	private static org.slf4j.Logger LOGGER= LoggerFactory.getLogger(ServerListenerThread.class);
	InputStream inputSocket= null;
	 OutputStream outputSocket=null;
	
	public HttpConnectionWorker(Socket socket) {
		this.socket=socket;
		
	}
	
	@Override
	public void run () {
		try {
			
			
			//obj to read request
		 inputSocket = socket.getInputStream();
			//obj to write to socket
		 outputSocket = socket.getOutputStream();
		
		 String html="<html><head><title>my HTTP server</title>"
					+ "</head><body><h1>Gerson PJ: serving from java http server</h1>"
					+ "</body></html>";
			
			
			String CRLF ="\n\r";  //13,10 ascii
			
			//include html
			String response = "HTTP/1.1 200 OK"+CRLF+   //httpversion responseCode responseMessage
					"Content-Lenght: "+ html.getBytes().length+CRLF+CRLF+  
					html+
					CRLF+CRLF;
			
			System.out.println(response);
			
outputSocket.write(response.getBytes());
System.out.println(response);	
			//finish connection
inputSocket.close();
outputSocket.close();
	socket.close();
	LOGGER.info("connection completed");
		
		} catch (IOException e) {
			LOGGER.error("problem: "+ e.getMessage());
		}

		
		
		
		
	}
		

}
