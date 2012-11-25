package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tour {
	
	private final List<Integer> nodes;
	
	public Tour() {
		super();
		
		nodes = new ArrayList<Integer>();
	}
	
	public void load(BufferedReader reader) throws IOException {
		String line = null;
		
		outer: while ((line = reader.readLine()) != null) {
			String[] tokens = line.trim().split("\\s+");
			
			for (int i = 0; i < tokens.length; i++) {
				int id = Integer.parseInt(tokens[i]);
				
				if (id == -1) {
					break outer;
				} else {
					nodes.add(id);
				}
			}
		}
	}
	
	public int size() {
		return nodes.size();
	}
	
	public int get(int index) {
		return nodes.get(index);
	}
	
	public double distance(DistanceTable nodeDistanceTable) {
		double result = 0.0;
		
		for (int i = 0; i < nodes.size(); i++) {
			result += nodeDistanceTable.getDistanceBetween(nodes.get(i), nodes.get((i+1) % nodes.size()));
		}
		
		return result;
	}
	
	public static Tour createCanonicalTour(int dimension) {
		Tour tour = new Tour();
		
		for (int i = 1; i <= dimension; i++) {
			tour.nodes.add(i);
		}
		
		return tour;
	}

}
