package server;

import java.util.Arrays;

public class Boat {
	private int[][] positions; //Achanger surement pas possible
	private boolean direction; //True pour Horizontal False pour Vertical
	private int touch = 0;
	private boolean sunk = false;
	private int length;
	private String name;


	public Boat(int[] gridPos,boolean direction, int length){
		//Créer un bâteau pour placer sur la grille
		this.length = length;
		this.direction = direction;
		this.name = nameAboat(length);
		setBoatPositions(gridPos);
	}

	public Boat(int length){
		this.length = length;
		this.name = nameAboat(length);

	}


	public String nameAboat(int length){
		String name = "";
		switch(length){
			case 2 : 
				name = "Torpilleur";
				break;
			case 3 :
				name = "Contre-Torpilleur";
				break;
			case 4 :
				name = "Croiseurs";
				break;
			case 5 :
				name = "Porte-Avion";
				break;
			default :
				name = "null";
		}
		return name;
	}

	public void setBoatPositions(int[] gridPos){ // A mettre en static ???
		
		if(this.direction){ //Horizontal
				for(int i = 0; i< this.length ; i++){
					this.positions[i] = new int[]{gridPos[0], i + gridPos[1]};
			}
		}
		else{
			for(int i = 0; i< this.length ; i++){
				this.positions[i] = new int[]{i + gridPos[0], gridPos[1]};
			}
		}
	}
	
	
	
	public String toString(){
		//return le nom et la taille du bateau pour afficher au client
		return this.name + " : " + this.length;
	}


	public boolean isTouch(int[] gridPos){ //Pas sécurisé si il touche un endroit déjà touché 
		for(int[] position : positions){
			if(Arrays.equals(position, gridPos)){
				this.touch ++;
				isSunk();
				return true;
				//break; //peut on brak ici comme on a pas besoin d'aller plus loin ?
			}
		}
		return false;
	}
	public void isSunk(){
		this.sunk = (this.length == this.touch);
	}

	
	
	//GETTER AND SETTER
	public int[][] getPositions() {
		return positions;
	}

	public void setPositions(int[][] positions) {
		this.positions = positions;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public int getTouch() {
		return touch;
	}

	public void setTouch(int touch) {
		this.touch = touch;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}

	













}
