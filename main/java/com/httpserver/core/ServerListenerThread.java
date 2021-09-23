package com.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.LoggerFactory;

import com.httpserver.HttpServer;

public class ServerListenerThread extends Thread {
	
	private static org.slf4j.Logger LOGGER= LoggerFactory.getLogger(ServerListenerThread.class);

	private int port;
	private String webRoot;
	private ServerSocket serverSocket;
	
	
	public ServerListenerThread(int port, String webRoot) throws IOException {
		
		this.port = port;
		this.webRoot = webRoot;
		this.serverSocket= new ServerSocket(this.port);
	}


	@Override  //one thread to accept conenections 
	public void run() {
		
		try {
			 
			while (serverSocket.isBound() && !serverSocket.isClosed()) {
			
			//waiting for connection one time using serversocket
			Socket socket = serverSocket.accept();
			LOGGER.info("connection accepted : "+ socket.getInetAddress());
			
			//spawn new worker thread
			HttpConnectionWorker httpWorker = new HttpConnectionWorker(socket);
			//run concurrent
			httpWorker.start();
			}
			
			
			//serverSocket.close();   //handled close
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(serverSocket !=null) {
				
				try {serverSocket.close();}
					 catch (IOException e) {}
				
				
			}
			
		}
		
	}
	
	
}
