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
		this.positions = new int[length][2];
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

	public void setBoatPositions(int[] gridPos){
		
		if(this.direction){ //Horizontal
				for(int i = 0; i< this.length ; i++){
					this.positions[i][0] = gridPos[0];
					this.positions[i][1] = i + gridPos[1];
			}
		}
		else{
			for(int i = 0; i< this.length ; i++){
				this.positions[i][0] = gridPos[0] + i;
				this.positions[i][1] = gridPos[1];
				
			}
		}
	}
	
	
	
	public String toString(){
		//return le nom et la taille du bateau pour afficher au client
		return this.name + " : " + this.length;
	}


	public boolean isTouch(int[] gridPos){ 
		for(int[] position : positions){
			if(Arrays.equals(position, gridPos)){
				this.touch ++;
				isSunk();
				return true;
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
	public boolean getSunk() {
		return this.sunk;
	}
	public static void main(String[] args) {
		Boat a = new Boat(new int[]{2,2}, true, 5);
		for(int[]pos : a.getPositions()) {
			System.out.println(pos[0] + " " + pos[1]);
		}
		Boat b = new Boat(new int[]{2,2}, false, 5);
		for(int[]pos : b.getPositions()) {
			System.out.println(pos[0] + " " + pos[1]);
		}
		System.out.println(a.isTouch(new int[] {2,5}));
		System.out.println(a.isTouch(new int[] {2,6}));
	}
}
