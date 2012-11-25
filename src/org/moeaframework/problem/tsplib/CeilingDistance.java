package org.moeaframework.problem.tsplib;

/**
 * The Euclidean distance function with distances rounded up to the nearest
 * integer.
 */
public class CeilingDistance extends DistanceFunction {
	
	/**
	 * Constructs a new Euclidean distance function with distances rounded up to
	 * the nearest integer.
	 */
	public CeilingDistance() {
		super();
	}
	
	@Override
	public double distance(int length, double[] position1, double[] position2) {
		double result = 0.0;
		
		for (int i = 0; i < length; i++) {
			result += Math.pow(position1[i] - position2[i], 2.0);
		}
		
		return Math.ceil(Math.sqrt(result));
	}

}
