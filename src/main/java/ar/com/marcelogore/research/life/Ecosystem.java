package ar.com.marcelogore.research.life;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ecosystem {

	private JFrame window;
	private JPanel[][] panels;
	private boolean[][] currentGen;
	private boolean[][] nextGen;
	
	public Ecosystem(int x, int y) {
		
		this.window = new JFrame("Ecosystem");
		this.window.setLayout(new GridLayout(x, y));
		this.currentGen = new boolean[x][y];
		this.nextGen = new boolean[x][y];
		this.panels = new JPanel[x][y];
		this.loadGeneration();
		this.loadPanels();
		
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.pack();
		this.window.setVisible(true);
		this.window.setResizable(false);
	}
	
	public void loadPanels() {
		
		for (int i = 0; i < this.panels.length; i++) {
			
			for (int j = 0; j < this.panels[i].length; j++) {
				
				this.panels[i][j] = new JPanel();
				this.panels[i][j].setSize(10, 10);
				this.panels[i][j].setBackground(this.nextGen[i][j] ? Color.BLACK : Color.WHITE);
				this.window.add(this.panels[i][j]);
			}
		}
	}

	public void refreshPanels() {
		
		for (int i = 0; i < this.panels.length; i++) {
			
			for (int j = 0; j < this.panels[i].length; j++) {
				
				this.panels[i][j].setBackground(this.currentGen[i][j] ? Color.BLACK : Color.WHITE);
			}
		}
	}

	public void loadGeneration() {
		
		for (int i = 0; i < this.currentGen.length; i++) {
			
			for (int j = 0; j < this.currentGen[i].length; j++) {
				
				this.currentGen[i][j] = Math.random() < 0.5;
			}
		}
	}
	
	public void nextGeneration() {
		
		for (int i = 0; i < this.currentGen.length; i++) {
			
			for (int j = 0; j < this.currentGen[i].length; j++) {
				
				if (this.currentGen[i][j] && numberOfNeighbours(i, j) < 2) {
					
					this.nextGen[i][j] = false;
					
				} else if (this.currentGen[i][j] && numberOfNeighbours(i, j) <= 3) {
					
					this.nextGen[i][j] = true;
					
				} else if (this.currentGen[i][j] && numberOfNeighbours(i, j) > 3) {
					
					this.nextGen[i][j] = false;
					
				} else if (!this.currentGen[i][j] && numberOfNeighbours(i, j) == 3) {
					
					this.nextGen[i][j] = true;
				}
			}
		}
		
		copyNextToCurrent();
	}

	private void copyNextToCurrent() {

		for (int i = 0; i < this.nextGen.length; i++) {
			
			for (int j = 0; j < this.nextGen[i].length; j++) {
				
				this.currentGen[i][j] = this.nextGen[i][j];
			}
		}
	}

	private int numberOfNeighbours(int i, int j) {
		
		int neighbours = 0;
		
		int previousCol = j - 1 >= 0 ? j - 1 : this.currentGen[i].length - 1;
		int previousRow = i - 1 >= 0 ? i - 1 : this.currentGen.length - 1;
		int nextCol = j + 1 < this.currentGen[i].length ? j + 1 : 0;
		int nextRow = i + 1 < this.currentGen.length ? i + 1 : 0;
		
		if (this.currentGen[previousRow][previousCol]) {
			neighbours++;
		}
		if (this.currentGen[previousRow][j]) {
			neighbours++;
		}
		if (this.currentGen[previousRow][nextCol]) {
			neighbours++;
		}
		if (this.currentGen[i][nextCol]) {
			neighbours++;
		}
		if (this.currentGen[nextRow][nextCol]) {
			neighbours++;
		}
		if (this.currentGen[nextRow][j]) {
			neighbours++;
		}
		if (this.currentGen[nextRow][previousCol]) {
			neighbours++;
		}
		if (this.currentGen[i][previousCol]) {
			neighbours++;
		}
		
		return neighbours;
	}
	
	public void evolve() {
		
		this.nextGeneration();
		this.refreshPanels();
		this.window.paintAll(this.window.getGraphics());
	}
}
