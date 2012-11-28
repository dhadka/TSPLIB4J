package org.moeaframework.problem.tsplib;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.moeaframework.core.Algorithm;
import org.moeaframework.core.EvolutionaryAlgorithm;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import org.moeaframework.core.spi.AlgorithmFactory;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;

/**
 * Demonstration of optimizing a TSP problem using the MOEA Framework
 * optimization library (http://www.moeaframework.org/).
 */
public class TSPOptimization {
	
	private static final Color lightGray = new Color(128, 128, 128, 64);
	
	public static Tour toTour(Solution solution) {
		int[] permutation = EncodingUtils.getPermutation(
				solution.getVariable(0));
		
		// increment values since TSP nodes start at 1
		for (int i = 0; i < permutation.length; i++) {
			permutation[i]++;
		}
		
		return Tour.fromArray(permutation);
	}
	
	public static class TSPProblem extends AbstractProblem {

		private final TSPInstance instance;
		
		public TSPProblem(TSPInstance instance) {
			super(1, 1);
			this.instance = instance;
		}

		@Override
		public void evaluate(Solution solution) {
			solution.setObjective(0, toTour(solution).distance(instance));
		}

		@Override
		public Solution newSolution() {
			Solution solution = new Solution(1, 1);
			
			solution.setVariable(0, EncodingUtils.newPermutation(
					instance.getDimension()));
			
			return solution;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// create the TSP problem instance and display panel
		TSPInstance instance = new TSPInstance(
				new File("./data/tsp/pr76.tsp"));
		
		TSPPanel panel = new TSPPanel(instance);
		panel.setAutoRepaint(false);
		
		// create other components on the display
		StringBuilder progress = new StringBuilder();
		JTextArea progressText = new JTextArea();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(panel);
		splitPane.setBottomComponent(new JScrollPane(progressText));
		splitPane.setDividerLocation(300);
		splitPane.setResizeWeight(1.0);
		
		// display the panel on a window
		JFrame frame = new JFrame(instance.getName());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// create the optimization problem and evolutionary algorithm
		Problem problem = new TSPProblem(instance);
		
		Properties properties = new Properties();
		properties.setProperty("swap.rate", "0.7");
		properties.setProperty("insertion.rate", "0.9");
		properties.setProperty("pmx.rate", "0.4");
		
		Algorithm algorithm = AlgorithmFactory.getInstance().getAlgorithm(
				"NSGAII", properties, problem);
		
		int iteration = 0;
		
		// now run the evolutionary algorithm
		while (frame.isVisible()) {
			algorithm.step();
			iteration++;
			
			// clear existing tours in display
			panel.clearTours();

			// display population with light gray lines
			if (algorithm instanceof EvolutionaryAlgorithm) {
				EvolutionaryAlgorithm ea = (EvolutionaryAlgorithm)algorithm;
				
				for (Solution solution : ea.getPopulation()) {
					panel.displayTour(toTour(solution), lightGray);
				}
			}
			
			// display current optimal solutions with red line
			Tour best = toTour(algorithm.getResult().get(0));
			panel.displayTour(best, Color.RED, new BasicStroke(2.0f));
			progress.insert(0, "Iteration " + iteration + ": " +
					best.distance(instance) + "\n");
			progressText.setText(progress.toString());
			
			// repaint the TSP display
			panel.repaint();
		}
	}

}
