package server;

import java.util.ArrayList;
import java.util.Arrays;

public class Joueur {
	private char[][] ownGrid = new char[10][10];
	private char[][] guessGrid = new char[10][10];
	private ArrayList<Boat> boatList = new ArrayList<Boat>();
	
	public Joueur() {
		this.ownGrid = emptyGrid();
		this.guessGrid = emptyGrid();
		
	}
	static public char[][] emptyGrid(){
		//réinitialise les grilles
		/*
			0 : Case vide
			1 : Bâteau (visible)
			2 : Tir (null)
			3 : Tir (touché)
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
	
	public void resetGrid(){
		this.ownGrid = emptyGrid();
		this.guessGrid = emptyGrid();
	}

	public void setBoat(String pos, int length, boolean direction) {
		int[] gridPos = gridPosition(pos);
		if(isBoatValid(pos, length, direction)){
			boatList.add(boatList.size(), new Boat(gridPos,direction, length));
		}

		

	}

	public boolean isBoatValid(String pos, int length, boolean direction){
		int[] gridPos = gridPosition(pos);
		if(direction){
			if (gridPos[1] < emptyGrid().length - length) {
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
			if (gridPos[0] < emptyGrid().length - length) {
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
	
	
}
