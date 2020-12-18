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
			Joueur j1 = new Joueur();
			Joueur j2 = new Joueur();
			while(partie){
				//PLACEMENT DES BATEAUX
				while(j1.getBoatListAplacer().size() !=0 || j2.getBoatListAplacer().size() !=0 ){
					if(j1.getBoatListAplacer().size() !=0){
						try {
						out1.println(ThreadBataille.printGrid(j1.getOwnGrid()));
						out1.println("Veuillez rentrer une position pour placer le bateau : " + j1.getBoatListAplacer().get(0).toString());
						String p1 = in1.readLine();
						out1.println("Veuillez rentrer une direction pour placer le bateau (true pour horizontal / false pour vertical (choix par défaut)) :");
						Boolean d1 = Boolean.parseBoolean(in1.readLine());
						if(j1.setBoat(p1,j1.getBoatListAplacer().get(0).getLength(), d1)) j1.DeleteBoatAplacer();
						
						}
						catch(Exception e) {
							out1.println("Données invalides bateau non placé");
						}
					}
					if(j2.getBoatListAplacer().size() !=0){
						try {
							out2.println(ThreadBataille.printGrid(j2.getOwnGrid()));
							out2.println("Veuillez rentrer une position pour placer le bateau : " + j2.getBoatListAplacer().get(0).toString());
							String p2 = in2.readLine();
							out2.println("Veuillez rentrer une direction pour placer le bateau (true pour horizontal / false pour vertical (choix par défaut)) :");
							Boolean d2 = Boolean.parseBoolean(in2.readLine());
							if(j2.setBoat(p2,j2.getBoatListAplacer().get(0).getLength(), d2)) j2.DeleteBoatAplacer();
							
							}
							catch(Exception e) {
								out2.println("Données invalides bateau non placé");
							}
					}
				}
				//PARTIE
				out1.println("Ma grille !!");
				out1.println(ThreadBataille.printGrid(j1.getOwnGrid()));
				out1.println("Grille des tirs!!");
				out1.println(ThreadBataille.printGrid(j1.getGuessGrid()));
				out2.println("Ma grille !!");
				out2.println(ThreadBataille.printGrid(j2.getOwnGrid()));
				out2.println("Grille des tirs!!");
				out2.println(ThreadBataille.printGrid(j2.getGuessGrid()));
				
				if(tour) { //Tour du joueur 1
					tour = !tour;//Changement de joueur
					out1.println("Entrez une position à tester");
					String posTir1 = in1.readLine();
					boolean[] shoot1 = j2.getShoot(posTir1);
					while(!(shoot1[0])) {
						out1.println("Position invalide entrez à nouveau une position à tester : ");
						posTir1 = in1.readLine();
						shoot1 = j2.getShoot(posTir1);
					}
					j1.shoot(posTir1, shoot1[1]);
					if (shoot1[1]) {
						if(shoot1[2]) out1.println("Touché Coulé !!!");
						else out1.println("Touché !!!");
					}
					else  out1.println("Manqué");
					if(j2.isLoose()) {
						partie = false;
						out1.println("Partie terminé Vous avez gagné");
						out2.println("Partie terminé Vous avez perdu");
					}	
				}
				else {
					tour = !tour;//Changement de joueur
					out2.println("Entrez une position à tester");
					String posTir2 = in2.readLine();
					boolean[] shoot2 = j1.getShoot(posTir2);
					while(!(shoot2[0])) {
						out2.println("Position invalide entrez à nouveau une position à tester : ");
						posTir2 = in2.readLine();
						shoot2 = j1.getShoot(posTir2);
					}
					j2.shoot(posTir2, shoot2[1]);
					if (shoot2[1]) {
						if(shoot2[2]) out2.println("Touché Coulé !!!");
						else out2.println("Touché !!!");
					}
					else  out2.println("Manqué");
					if(j1.isLoose()) {
						partie = false;
						out2.println("Partie terminé Vous avez gagné");
						out1.println("Partie terminé Vous avez perdu");
					}	
				}
			}
		}catch (Exception e) {
			System.out.println("Connexion interrompu");
		}
	}
	public static String printGrid(char[][] grid) {
		char[] letters = {'A','B','C','D','E','F','G','H','I','J'};
		char[] figures = {'X','0','1','2','3','4','5','6','7','8','9'};
		String message = "";
		String Newligne=System.getProperty("line.separator");// \n ne fonctionne pas 
		for (int i = 0; i < grid.length+1; i++) {
			message += Newligne;
			if(i == 0) {
				for (int j = 0; j < figures.length; j++) {
					message += (figures[j] + " ");
				}
				
			}
			else {
				for (int j = 0; j < grid[0].length+1; j++) {
					if(j == 0) {
						message += (letters[i-1] + " ");
					}
					else {
						if (grid[i-1][j-1] == 0) message += ("_"+ " ");
						else if(grid[i-1][j-1] == 1) message += ("B"+ " ");
						else if(grid[i-1][j-1] == 2) message += ("O" + " ");
						else message += ("X" + " ");
					}
				}
			}
			
		}
		return message;
	}

}
	
	
	
	
	

