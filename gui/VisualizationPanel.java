package com.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.sortingauxiliary.*;

import javax.swing.JPanel;

public class VisualizationPanel extends JPanel{

	private Dimension dimension;
	private ArrayList<? extends Sortable> currentData;
	private final int RECT_WIDTH = 50;
	private final int PADDING_Y = 0;
	private final int PADDING_X = 0;
	
	private int maxValue;
	
	public VisualizationPanel() {
		this.currentData = new ArrayList<>();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, dimension.width, dimension.height);
		
		for(int i = 0; i < currentData.size(); i++) {
			drawVisualizationRectangle(g2d, i);
		}
		
	}
	
	private int getMax() {
		int max = 0;
		Sortable maxVal = this.currentData.get(0);
		
		for(int i = 1; i < currentData.size(); i++) {
			if(this.currentData.get(i).compareTo(maxVal) == 1) {
				maxVal = this.currentData.get(i);
				max = i;
				System.out.println("Found a new max: " + max);
			}
		}
		
		return max;
	}
	
	private void drawVisualizationRectangle(Graphics2D g2d, int index) {
		g2d.setColor(Color.RED);
		Sortable s = this.currentData.get(index);
		int x = RECT_WIDTH * index + PADDING_X;
		int y = this.dimension.height;
		int blockHeight = (int)(dimension.height * s.getValue().floatValue()/this.currentData.get(maxValue).getValue().floatValue());
		System.out.println("Drawing: " + s.getValue().floatValue() + " with max: " + this.currentData.get(maxValue).getValue().floatValue());
		g2d.fillRect(x, y - blockHeight, RECT_WIDTH, blockHeight);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(x, y - blockHeight, RECT_WIDTH, blockHeight);
	}
	
	public void updateData(ArrayList<? extends Sortable> currentData) {
		this.currentData = currentData;
		this.maxValue = getMax();
	}
	
	public void setPaneSize(Dimension d) {
		this.dimension = d;
	}
}
