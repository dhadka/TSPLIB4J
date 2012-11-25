package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the nodes (by their identifier) that are visited in a tour.
 */
public class Tour {
	
	/**
	 * The nodes that are visited in this tour.
	 */
	private final List<Integer> nodes;
	
	/**
	 * Constructs a new, empty tour.
	 */
	public Tour() {
		super();
		
		nodes = new ArrayList<Integer>();
	}
	
	/**
	 * Loads the contents of this tour from the given reader.
	 * 
	 * @param reader the reader that defines this tour
	 * @throws IOException if an I/O error occurred while reading the tour
	 */
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
	
	/**
	 * The number of nodes visited in this tour.
	 * 
	 * @return the number of nodes visited in this tour
	 */
	public int size() {
		return nodes.size();
	}
	
	/**
	 * Returns the identifier of the node visited at the specified index.
	 * 
	 * @param index the index
	 * @return the identifier of the node visisted at the specified index
	 * @throws IndexOutOfBoundsException if the given index was out of bounds
	 */
	public int get(int index) {
		return nodes.get(index);
	}
	
	/**
	 * Calculates and returns the total distance of this tour.  The total
	 * distance includes the distance from the last node back to the first node
	 * in the tour.
	 * 
	 * @param distanceTable the distance table that defines the distances
	 *        between nodes
	 * @return the total distance of this tour
	 */
	public double distance(DistanceTable distanceTable) {
		double result = 0.0;
		
		for (int i = 0; i < nodes.size(); i++) {
			result += distanceTable.getDistanceBetween(nodes.get(i),
					nodes.get((i+1) % nodes.size()));
		}
		
		return result;
	}
	
	/**
	 * Returns the canonical tour with the given length.  The canonical
	 * tour visits the nodes in order, i.e., {@code 1, 2, 3, ..., length}.
	 * 
	 * @param length the number of nodes in the resulting tour
	 * @return the canonical tour with the given length
	 */
	public static Tour createCanonicalTour(int length) {
		Tour tour = new Tour();
		
		for (int i = 1; i <= length; i++) {
			tour.nodes.add(i);
		}
		
		return tour;
	}

}
