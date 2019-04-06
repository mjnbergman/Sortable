package com.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class VisualizationPanel extends JPanel{

	private Dimension dimension;
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, dimension.width, dimension.height);
	}
	
	public void setPaneSize(Dimension d) {
		this.dimension = d;
	}
}
