package org.moeaframework.problem.tsplib;

/**
 * Implementation of the 2-opt heuristic for the traveling salesman problem.
 * The 2-opt heuristic searches for any two edges in a tour that can be
 * rearranged to produce a shorter tour.  For example, a tour with any edges
 * that intersect can be shortened by removing the intersection.
 */
public class TSP2OptHeuristic {
	
	/**
	 * The traveling salesman problem instance.
	 */
	private final TSPInstance instance;

	/**
	 * Constructs a new 2-opt heuristic for the specified traveling salesman
	 * problem instance.
	 * 
	 * @param instance the traveling salesman problem instance
	 */
	public TSP2OptHeuristic(TSPInstance instance) {
		super();
		this.instance = instance;
	}
	
	/**
	 * Applies the 2-opt heuristic to the specified tour.
	 * 
	 * @param tour the tour that is modified by the 2-opt heuristic
	 */
	public void apply(Tour tour) {
		int[] tourAsArray = tour.toArray();
		apply(tourAsArray);
		tour.fromArray(tourAsArray);
	}
	
	/**
	 * Applies the 2-opt heuristic to the specified tour.
	 * 
	 * @param tour the tour that is modified by the 2-opt heuristic
	 */
	public void apply(int[] tour) {
		DistanceTable distanceTable = instance.getDistanceTable();
		int n = tour.length;
		boolean modified = true;
		
		while (modified) {
			modified = false;
			
			for (int i = 0; i < n; i++) {
				for (int j = i+2; j < n; j++) {
					double d1 = distanceTable.getDistanceBetween(tour[i], tour[(i+1) % n]) +
							distanceTable.getDistanceBetween(tour[j], tour[(j+1) % n]);
					double d2 = distanceTable.getDistanceBetween(tour[i], tour[j]) +
							distanceTable.getDistanceBetween(tour[(i+1) % n], tour[(j+1) % n]);
					
					if (d2 < d1) {
						for (int k = 0; k < (j - i) / 2; k++) {
							int temp = tour[i+1+k];
							tour[i+1+k] = tour[j-k];
							tour[j-k] = temp;
						}
						
						modified = true;
					}
				}
			}
		}
	}

}
