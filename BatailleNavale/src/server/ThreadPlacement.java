package server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ThreadPlacement extends Thread{
	BufferedReader in;
	PrintWriter out;
	Joueur j;
	public ThreadPlacement(Joueur j, BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		this.j = j;
	}
	
	public void run() {
		while(j.getBoatListAplacer().size() !=0) {
			try {
				out.println(ThreadBataille.printGrid(j.getOwnGrid()));
				out.println("Veuillez rentrer une position pour placer le bateau : " + j.getBoatListAplacer().get(0).toString());
				String p = in.readLine();
				out.println("Veuillez rentrer une direction pour placer le bateau (true pour horizontal / false pour vertical (choix par défaut)) :");
				Boolean d = Boolean.parseBoolean(in.readLine());
				if(j.setBoat(p,j.getBoatListAplacer().get(0).getLength(),d)) j.DeleteBoatAplacer();
				
				}
				catch(Exception e) {
					out.println("Données invalides bateau non placé");
				}
		}
		out.println("Fin du placement");
	}
}
