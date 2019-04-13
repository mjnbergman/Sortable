package com.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.applicationlogic.SortingApplication;
import com.sortingauxiliary.Sortable;

/**
 * 
 * @author Maiko Bergman
 * @since 13-04-2019
 * 
 * Composite container class for an entire group of visualization components. Together they allow a user
 * to visualize a dataset, and also to edit the way it is visualized through GUI components. (So all of
 * the edit features are made available through this {@code(VisualizationGroup)}). It is necessary to allow
 * multiple datasets to be visualized at the same time (multi-panel support).
 *
 */
public class VisualizationGroup extends JPanel{
	
	private VisualizationPanel vp;
	private SpringLayout layout;
	private JPanel optionsPanel;
	private JSlider speedSlider;
	private JButton restartButton;
	
	private String curAlgo;
	private boolean algorithmChanged = false;
	
	public VisualizationGroup() {
		
	}

	
	public void buildGUI() {
		this.layout = new SpringLayout();
		this.setLayout(layout);
		
		this.optionsPanel = new JPanel();
		this.optionsPanel.setLayout(new BoxLayout(this.optionsPanel, BoxLayout.Y_AXIS));
		
		this.vp = new VisualizationPanel();
		
		JLabel sliderLabel = new JLabel("Step time in milliseconds: ");
		
		this.speedSlider = new JSlider(JSlider.HORIZONTAL, GUI.SPEED_MIN, GUI.SPEED_MAX, GUI.SPEED_INIT);
		
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
					vp.setNewAlgo(curAlgo, null);
				}
				
			}
			
		});
		
		JLabel algoSelectLabel = new JLabel("Select a sorting algorithm: ");
		
		this.restartButton = new JButton("Restart");
		
		this.restartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetVisualization();
			}
			
		});
		
		this.optionsPanel.add(sliderLabel);
		this.optionsPanel.add(speedSlider);
		this.optionsPanel.add(algoSelectLabel);
		this.optionsPanel.add(algoSelectBox);
		this.optionsPanel.add(restartButton);
		
		this.optionsPanel.setPreferredSize(new Dimension((int)(0.25 * (GUI.FRAME_WIDTH/GUI.PANEL_AMOUNT)), 600));
		
		this.vp.setPreferredSize(new Dimension((int)(0.75 * (GUI.FRAME_WIDTH/GUI.PANEL_AMOUNT)), 600));
		
		this.vp.setNewAlgo(curAlgo, null);
		
		this.add(vp);
		
		this.layout.putConstraint(SpringLayout.WEST, vp, 0, SpringLayout.WEST, this);
		this.layout.putConstraint(SpringLayout.NORTH, vp, 0, SpringLayout.NORTH, this);
		
		
		this.add(optionsPanel);
	
		
		this.layout.putConstraint(SpringLayout.WEST, optionsPanel, 0, SpringLayout.EAST, vp);
		this.layout.putConstraint(SpringLayout.NORTH, optionsPanel, 0, SpringLayout.NORTH, this);
		
	}
	
	public void resize() {
		this.optionsPanel.setPreferredSize(new Dimension((int)(0.95 * 0.25 * (GUI.FRAME_WIDTH/GUI.PANEL_AMOUNT)), 600));
		this.vp.setPreferredSize(new Dimension((int)(0.95 * 0.75 * (GUI.FRAME_WIDTH/GUI.PANEL_AMOUNT)), 600));
		//this.setPreferredSize(new Dimension(GUI.FRAME_WIDTH/GUI.PANEL_AMOUNT, 600));
		// this.revalidate();
	}
	
	public void updateSliderValue() {
		this.speedSlider.setValue(SortingApplication.TIME_STEP);
	}
	public void resetVisualization() {
		this.vp.setNewAlgo(null, null);
	}
	public void attemptPlayback() {
		this.vp.playback();
	}
	public void setPaneSize(Dimension d) {
		this.vp.setPaneSize(d);
	}
	public void updateData(ArrayList<? extends Sortable> datapoints) {
		this.vp.updateData(datapoints);
	}
	public void setDone(boolean done) {
		this.vp.setDone(done);
	}
	public void setNewData(ArrayList<? extends Sortable> datapoints) {
		this.vp.setNewAlgo(null, datapoints);
	}
}
