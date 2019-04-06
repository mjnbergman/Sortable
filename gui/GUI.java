package com.gui;

import com.applicationlogic.FileHandler;
import com.sortingauxiliary.Sortable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * 
 * @author Maiko Bergman
 * @date 24-3-2019
 * 
 * This class represents and contains all the GUI components of the application.
 * It contains methods for building and presenting the GUI, and for
 * manipulating with the @code(VisualizationPanel) which is the graphical
 * object that the visualizations are drawn to.
 * 
 *
 */
public class GUI {
	
	private VisualizationPanel vp;
	private FileHandler fw;
	private boolean newData = false;
	private ArrayList<String> curData;
	
	public GUI() {
		this.fw = new FileHandler();
		this.constuctGUI();
	}
	
	/**
	 * Construct the graphical user interface of the program and
	 * presents it to the user
	 */
	private void constuctGUI() {
		JFrame jf = new JFrame("The Sorting Visualization Machine");
		vp = new VisualizationPanel();
		
		jf.add(vp);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem openFileMenuItem = new JMenuItem("Open Dataset");
		JMenuItem exitAppMenuItem = new JMenuItem("Exit");
		
		fileMenu.add(openFileMenuItem);
		fileMenu.add(exitAppMenuItem);
		
		menuBar.add(fileMenu);
		
		openFileMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            curData = fw.openDataSet(file);
		            newData = true;
		            System.out.println("New data set!");
		        } else {
		            
		        }
				
			}
			
		});
		
		jf.setJMenuBar(menuBar);
		
		jf.setSize(800, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		
		jf.setVisible(true);
		
		vp.setPaneSize(jf.getContentPane().getSize());
		
		jf.repaint();
	}
	
	public ArrayList<String> getData(){
		this.newData = false;
		return this.curData;
	}
	public boolean hasData() {
		return this.newData;
	}
	
	public void setData(ArrayList<? extends Sortable> datapoints) {
		this.vp.updateData(datapoints);
	}
	
	public void repaint() {
		this.vp.repaint();
	}

}
