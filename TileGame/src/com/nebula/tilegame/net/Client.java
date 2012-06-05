package com.nebula.tilegame.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

public class Client {
	
	private String UUID;

	private Socket connection;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public static void main(String[] args) {
		Client connection = new Client("127.0.0.1", 4000);
	}
	
	public Client(String ip, int port) {
		try {
			connection = new Socket(ip, port);
			
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			
			send("[CONNECTED]");
			
			connect();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void connect() {
		String line;
		try {
			while((line = reader.readLine()) != null) {
				System.out.println("client got: " + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String data) {
		if (writer != null) {
			try {
				writer.write(data);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
