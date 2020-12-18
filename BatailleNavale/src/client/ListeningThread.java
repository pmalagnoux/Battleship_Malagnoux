package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListeningThread extends Thread{
	BufferedReader in;
	
	public ListeningThread(Socket s) throws IOException {
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	public void run(){
		try {
			
		while (true) {
					
					System.out.println(in.readLine());
			
			
		}
		}catch (IOException e) {};
	}
	
	public void printGrid(char[][] grid) {
		char[] letters = {'X','A','B','C','D','E','F','G','H','I','J'};
		char[] figures = {'0','1','2','3','4','5','6','7','8','9'};
		for (int i = 0; i < grid.length+1; i++) {
			System.out.println();
			if(i == 0) {
				for (int j = 0; j < letters.length; j++) {
					System.out.print(letters[j] + " ");
				}
				
			}
			else {
				for (int j = 0; j < grid[0].length+1; j++) {
					if(j == 0) {
						System.out.print(figures[i-1] + " ");
					}
					else {
						if (grid[i-1][j-1] == 0) System.out.print("_"+ " ");
						else if(grid[i-1][j-1] == 1) System.out.print("B"+ " ");
						else if(grid[i-1][j-1] == 2) System.out.print("O" + " ");
						else System.out.print("X" + " ");
					}
				}
			}
		}
	}
}