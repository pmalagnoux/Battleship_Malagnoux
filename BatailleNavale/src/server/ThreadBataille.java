package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ThreadBataille extends Thread {
	BufferedReader in1;
	PrintWriter out1;
	BufferedReader in2;
	PrintWriter out2;
	Joueur j1;
	Joueur j2;
	
	public ThreadBataille(Socket client1, Socket client2) {
		try {

		this.in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
		this.in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
		this.out1 = new PrintWriter(client1.getOutputStream(), true);
		this.out2 = new PrintWriter(client2.getOutputStream(), true);
		this.j1 = new Joueur();
		this.j2 = new Joueur();
		}
		catch(Exception e) {}
	}
	
	public void run() {
		//Partie
		//A déplacer dans Partie
		try {
			boolean partie = true;
			boolean tour = new Random().nextBoolean(); //true J1 commence false J2 commence
			
			ThreadPlacement placementJoueur1 = new ThreadPlacement(j1, in1, out1);
			ThreadPlacement placementJoueur2 = new ThreadPlacement(j2, in2, out2);
			placementJoueur1.start();
			placementJoueur2.start();
			
			while(partie){
				if(!placementJoueur1.isAlive() && !placementJoueur2.isAlive()) {
					
					afficher(j1, out1); //Envoie des grilles aux Joueurs
					afficher(j2, out2);
					
					if(tour) {
						
						partie = tour(j1,j2,in1,out1,out2);
					}
					else {
						partie = tour(j2,j1,in2,out2,out1);
					}
					tour = !tour;//Changement de joueur
				}
			}
		}catch (Exception e) {
			System.out.println("Connexion interrompu");
		}
	}
	public boolean tour(Joueur Attaquant, Joueur Receveur, BufferedReader inA, PrintWriter outA, PrintWriter outR ){
		
		try{
			outA.println("Entrez une position à tester");
			String posTir = inA.readLine();
			boolean[] shoot = Receveur.getShoot(posTir);
			while(!(shoot[0])) {
				outA.println("Position invalide entrez à nouveau une position à tester : ");
				posTir = inA.readLine();
				shoot = Receveur.getShoot(posTir);
			}
			Attaquant.shoot(posTir, shoot[1]);
			if (shoot[1]) {
				if(shoot[2]) outA.println("Touché Coulé !!!");
				else outA.println("Touché !!!");
			}
			else  outA.println("Manqué");
			if(Receveur.isLoose()) {
				
				outA.println("Partie terminé Vous avez gagné");
				outR.println("Partie terminé Vous avez perdu");
				return false;
			}
		}
		catch(Exception e){
			
		}
		return true;
	}

	public void afficher(Joueur j, PrintWriter out) {
		out.println("Ma grille !!");
		out.println(ThreadBataille.printGrid(j.getOwnGrid()));
		out.println("Grille des tirs!!");
		out.println(ThreadBataille.printGrid(j.getGuessGrid()));

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
	
	
	
	
	

