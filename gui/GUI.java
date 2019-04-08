package com.gui;

import com.applicationlogic.FileHandler;
import com.applicationlogic.SortingApplication;
import com.sortingauxiliary.Sortable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


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
	private boolean algorithmChanged = false;
	private String curAlgo;
	private ArrayList<String> curData;
	
	private final int FRAME_WIDTH = 1100;
	
	private final int SPEED_MIN = 0;
	private final int SPEED_MAX = 5000;
	private final int SPEED_INIT = 1000;
	
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
		jf.setSize(FRAME_WIDTH, 600);
		
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
		
		JPanel topContainerPanel = new JPanel();
		
		SpringLayout masterLayout = new SpringLayout();
		
		topContainerPanel.setLayout(masterLayout);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		
		JLabel sliderLabel = new JLabel("Step time in milliseconds: ");
		
	//	JPanel l1Panel = new JPanel(new BorderLayout(0, 0));
		//l1Panel.add(sliderLabel, BorderLayout.CENTER);
	//	sliderLabel.setHorizontalAlignment(SwingConstants.LEFT);
	//	l1Panel.
		
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		
		speedSlider.setMajorTickSpacing(2500);
		speedSlider.setMinorTickSpacing(100);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		
		speedSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int newTime = speedSlider.getValue();
				SortingApplication.TIME_STEP = newTime;
			}
			
		});
		
		String[] sortingAlgorithms = {"InsertionSort", "MergeSort"};
		
		JComboBox algoSelectBox = new JComboBox(sortingAlgorithms) {
			
            /** 
             * @inherited <p>
             */
            @Override
            public Dimension getMaximumSize() {
                Dimension max = super.getMaximumSize();
                max.height = getPreferredSize().height;
                return max;
            }
		};
		
		algoSelectBox.setSelectedIndex(0);
		curAlgo = sortingAlgorithms[0];
		
		algoSelectBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!curAlgo.equals((String)algoSelectBox.getSelectedItem())) {
					curAlgo = (String)algoSelectBox.getSelectedItem();
					algorithmChanged = true;
				}
				
			}
			
		});
		
		JLabel algoSelectLabel = new JLabel("Select a sorting algorithm: ");
		
		optionsPanel.add(sliderLabel);
		optionsPanel.add(speedSlider);
		optionsPanel.add(algoSelectLabel);
		optionsPanel.add(algoSelectBox);
		
		optionsPanel.setPreferredSize(new Dimension((int)(0.24 * FRAME_WIDTH), 600));
		
	//	algoSelectBox.setSize(new Dimension(100, 100));
	
	//	algoSelectBox.setMaximumSize(new Dimension(optionsPanel.getSize().width, 100));
		
		
		vp = new VisualizationPanel();
		
		vp.setPreferredSize(new Dimension((int)(0.75 * FRAME_WIDTH), 600));
		
		topContainerPanel.add(vp);
		
		masterLayout.putConstraint(SpringLayout.WEST, vp, 0, SpringLayout.WEST, topContainerPanel);
		masterLayout.putConstraint(SpringLayout.NORTH, vp, 0, SpringLayout.NORTH, topContainerPanel);
		
		
		topContainerPanel.add(optionsPanel);
	
		
		masterLayout.putConstraint(SpringLayout.WEST, optionsPanel, 0, SpringLayout.EAST, vp);
		masterLayout.putConstraint(SpringLayout.NORTH, optionsPanel, 0, SpringLayout.NORTH, topContainerPanel);
		
		
		topContainerPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 600));
		
		jf.getContentPane().add(topContainerPanel);
		
		
		
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		
		jf.setVisible(true);
		
		vp.setPaneSize(new Dimension((int)(jf.getContentPane().getSize().width * 0.75), jf.getContentPane().getSize().height));
		
		
		
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
	
	public void setDone(boolean done) {
		this.vp.setDone(done);
	}
	
	public boolean algorithmChanged() {
		return this.algorithmChanged;
	}
	
	public void validateChangeReceived() {
		this.algorithmChanged = false;
	}
	
	public String getCurrentAlgorithm() {
		return this.curAlgo;
	}

}
