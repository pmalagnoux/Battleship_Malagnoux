package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadBataille extends Thread {
	BufferedReader in1;
	PrintWriter out1;
	BufferedReader in2;
	PrintWriter out2;

	
	public ThreadBataille(Socket client1, Socket client2) {
		try {

		this.in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
		this.in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
		this.out1 = new PrintWriter(client1.getOutputStream(), true);
		this.out2 = new PrintWriter(client2.getOutputStream(), true);
	
		}
		catch(Exception e) {}
	}
	
	public void run() {
		//Partie
		//A déplacer dans Partie
		try {
			boolean partie = true;
			boolean tour = true; //true J1 commence false J2 commence
			boolean placement = true; //true si le joueur doit placer un bâteau
			while(partie){
				while(tour) {
					String message=in1.readLine();
					if (message.equals("quit")){
						partie = false;
						tour = false;
					}
					if (placement && message.length() == 2) {
						if(Joueur.isValid(message)) {
							
						}
					}
				} 
				
				while(!tour) {
				
				}
			}
		}catch (Exception e) {}
	}
}
	
	
	
	
	

