package server;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DebugGraphics;

public class Joueur {
	private char[][] ownGrid = new char[10][10];
	private char[][] guessGrid = new char[10][10];
	private ArrayList<Boat> boatList = new ArrayList<Boat>();
	private ArrayList<Boat> boatListAplacer = new ArrayList<Boat>();
	
	

	public Joueur() {
		this.ownGrid = emptyGrid();
		this.guessGrid = emptyGrid();
		this.boatAplacer();
	}
	static public char[][] emptyGrid(){
		//r√©initialise les grilles
		/*
			0 : Case vide
			1 : B√¢teau (visible)
			2 : Tir (null)
			3 : Tir (touch√©)
		*/
		char[][] grid ={
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0}};
		return grid;
	}
	public void boatAplacer() {//Initialise la liste des bateau ‡ placer par le joueur
		this.boatListAplacer.add(new Boat(5)); //Porte avion
		this.boatListAplacer.add(new Boat(4)); //Croiseur
		this.boatListAplacer.add(new Boat(3)); //Contre-torpilleur
		this.boatListAplacer.add(new Boat(3)); //Contre-torpilleur
		this.boatListAplacer.add(new Boat(2)); //Torpilleur
	}
	
	public void DeleteBoatAplacer() {
		this.boatListAplacer.remove(0);
	}
	public void resetGrid(){
		this.ownGrid = emptyGrid();
		this.guessGrid = emptyGrid();
	}

	
	public boolean setBoat(String pos, int length, boolean direction) {
		int[] gridPos = gridPosition(pos);
		if(isBoatValid(pos, length, direction)){	
			boatList.add(new Boat(gridPos,direction, length));
			for(int[] position : boatList.get(boatList.size()-1).getPositions()){
				this.ownGrid[position[0]][position[1]] = 1 ;
			}
			return true;
		}
		return false;
		

	}

	public boolean isBoatValid(String pos, int length, boolean direction){
		int[] gridPos = gridPosition(pos);
		if(direction){
			if (gridPos[1] <= ownGrid.length - length) {
				for(Boat boat : this.boatList) {
					for(int[] position : boat.getPositions()){
						if(Arrays.equals(position, gridPos)){
							return false;
						}
					}
				}
			return true;
			}
		}
		else{
			if (gridPos[0] <= ownGrid.length - length) {
				for(Boat boat : this.boatList) {
					for(int[] position : boat.getPositions()){
						if(Arrays.equals(position, gridPos)){
							return false;
						}
					}
				}
			return true;
			}
		}
		return false;
	}
	public boolean[] getShoot(String pos) { //Retourne un bool {isValid, isTouch, isSunk}
		int[] gridPos = gridPosition(pos);
		if (gridPos == null) {// si pos non valide
			return new boolean[]{false,false,false};
		}
		if ((this.ownGrid[gridPos[0]][gridPos[1]] != 2 && this.ownGrid[gridPos[0]][gridPos[1]] != 3)) {//Si pos valide et non touchÈ ou coulÈ.
			for(Boat boat : boatList) {
				if (boat.isTouch(gridPos)) {//Si touchÈ
					this.ownGrid[gridPos[0]][gridPos[1]] = 3;
					if(boat.getSunk()) {//Si coulÈ
						return new boolean[]{true, true, true};
					}
					
					else {// Si non coulÈ
						return new boolean[]{true, true, false};
					}
				}
			}
			this.ownGrid[gridPos[0]][gridPos[1]] = 2;
			return new boolean[]{true, false,false};//pos valide mais pas touchÈ
		}
		
		
		return new boolean[]{false,false,false};
	}
	
	public void shoot(String pos, boolean touch) {
		int[] gridPos = gridPosition(pos);
		if (touch) this.guessGrid[gridPos[0]][gridPos[1]] = 3;
		else this.guessGrid[gridPos[0]][gridPos[1]] = 2;
	}
	
	public boolean isLoose() {
		for(Boat boat : boatList) {
			if(!boat.getSunk())return false;
		}
		return true;
	}
	static public int[] gridPosition(String pos) {
		char[] letters = {'A','B','C','D','E','F','G','H','I','J'};
		char[] figures = {'0','1','2','3','4','5','6','7','8','9'};
		if (pos.length() == 2){
			for(int i =0; i<letters.length; i++) {
				if(pos.charAt(0) == letters[i]) {
					for(int j = 0; j< figures.length; j++){
						if(pos.charAt(1) == figures[j]){
							return new int[] {i, j};
						}
					}
				}
			}
		}
		return null;
	}
	
	public char[][] getOwnGrid() {
		return ownGrid;
	}
	public void setOwnGrid(char[][] ownGrid) {
		this.ownGrid = ownGrid;
	}
	public char[][] getGuessGrid() {
		return guessGrid;
	}
	public void setGuessGrid(char[][] guessGrid) {
		this.guessGrid = guessGrid;
	}
	public ArrayList<Boat> getBoatList() {
		return boatList;
	}
	public void setBoatList(ArrayList<Boat> boatList) {
		this.boatList = boatList;
	}
	public ArrayList<Boat> getBoatListAplacer() {
		return boatListAplacer;
	}
	public void setBoatListAplacer(ArrayList<Boat> boatListAplacer) {
		this.boatListAplacer = boatListAplacer;
	}
	
}
