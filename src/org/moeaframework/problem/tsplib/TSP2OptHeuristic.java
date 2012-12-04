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
		DistanceTable distanceTable = instance.getDistanceTable();
		boolean modified = true;
		
		// tours with 3 or fewer nodes are already optimal
		if (tour.size() < 4) {
			return;
		}
		
		while (modified) {
			modified = false;
			
			for (int i = 0; i < tour.size(); i++) {
				for (int j = i+2; j < tour.size(); j++) {
					double d1 = distanceTable.getDistanceBetween(tour.get(i), tour.get(i+1)) +
							distanceTable.getDistanceBetween(tour.get(j), tour.get(j+1));
					double d2 = distanceTable.getDistanceBetween(tour.get(i), tour.get(j)) +
							distanceTable.getDistanceBetween(tour.get(i+1), tour.get(j+1));
					
					// if distance can be shortened, adjust the tour
					if (d2 < d1) {
						tour.reverse(i+1, j);
						modified = true;
					}
				}
			}
		}
	}

}
