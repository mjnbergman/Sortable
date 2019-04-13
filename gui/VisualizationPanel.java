package com.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.applicationlogic.SortingManager;
import com.sortingauxiliary.*;

import javax.swing.JPanel;

/**
 * 
 * @author Maiko Bergman
 * @date 06-04-2019
 * 
 * The @code(VisualizationPanel) is a custom JPanel that the graphical representation of the dataset
 * is drawn to. It is continuously repainted at an interval defined in the @code(SortingApplication).
 * Furthermore, it receives new data through the @code(GUI) and will draw this to the screen, always
 * filling its complete width with data rectangles, up to 3/4ths of its height.
 * Automatically scales these data rectangles as well and performs all work related to the graphics.
 *
 */
public class VisualizationPanel extends JPanel{

	private Dimension dimension;
	private ArrayList<? extends Sortable> currentData;
	private ArrayList<? extends Sortable> originalDataset;
	private int RECT_WIDTH = 50;
	private final int PADDING_Y = 0;
	private final int PADDING_X = 0;
	private boolean doneSorting = false;
	private String currentAlgo;
	
	private SortingManager sm;
	
	private int maxValue;
	
	private int playbackIndex = 0;
	
	public VisualizationPanel() {
		this.currentData = new ArrayList<>();
		this.originalDataset = new ArrayList<>();
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
		
		if(this.currentData == null || this.currentData.size() == 0) {
			return -1;
		}
		
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
		if(!doneSorting) {
			g2d.setColor(Color.RED);
		}else {
			g2d.setColor(Color.GREEN);
		}
		
		Sortable s = this.currentData.get(index);
		int x = RECT_WIDTH * index + PADDING_X;
		int y = this.dimension.height;
		int blockHeight = (int)((dimension.height * (3/4f)) * s.getValue().floatValue()/this.currentData.get(maxValue).getValue().floatValue());
	//	System.out.println("Drawing: " + s.getValue().floatValue() + " with max: " + this.currentData.get(maxValue).getValue().floatValue());
		g2d.fillRect(x, y - blockHeight, RECT_WIDTH, blockHeight);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(x, y - blockHeight, RECT_WIDTH, blockHeight);
	}
	
	public void updateData(ArrayList<? extends Sortable> currentData) {
		
		if(currentData.size() == 0) {
			System.out.println("What THA FAKKKK!!!");
			return;
		}
		
		this.currentData = currentData;
		this.maxValue = getMax();
		this.RECT_WIDTH = this.dimension.width/this.currentData.size();
	}
	
	public void setPaneSize(Dimension d) {
		this.setSize(d);
		this.dimension = this.getSize();	
	}
	
	public void setDone(boolean done) {
		this.doneSorting = done;
	}
	
	public void playback() {
		
		if(this.sm == null || this.sm.isRunning() || this.sm.getDataset().size() == 0) {
			return;
		}
		
		System.out.println("Attempting playback at index: " + this.playbackIndex);
		
		this.updateData(new ArrayList<>(this.sm.getDataset().get(this.playbackIndex)));
		
		if(this.playbackIndex + 1 < this.sm.getDataset().size()) {
			this.playbackIndex++;
		}else {
			this.setDone(true);
		}
	}

	
	public void setNewAlgo(String algo, ArrayList<? extends Sortable> data) {
		if(data == null) {
			data = new ArrayList<>(this.originalDataset);
			System.out.println("Setting original dataset...");
		}else {
			this.originalDataset = new ArrayList<>(data);
		}
		if(algo == null) {
			algo = this.currentAlgo;
		}
		
		if(this.sm != null && this.sm.isRunning()) {
			this.sm.stop();
		}
		
		this.setDone(false);
		
		this.sm = new SortingManager(algo, data);
		this.currentAlgo = algo;
		this.playbackIndex = 0;
		this.sm.executeAlgorithm();
	}
}
