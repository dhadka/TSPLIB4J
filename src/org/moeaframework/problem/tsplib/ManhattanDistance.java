package org.moeaframework.problem.tsplib;

public class ManhattanDistance extends DistanceFunction {
	
	public ManhattanDistance() {
		super();
	}
	
	@Override
	public double distance(int length, double[] position1, double[] position2) {
		double result = 0.0;
		
		for (int i = 0; i < length; i++) {
			result += Math.abs(position1[i] - position2[i]);
		}

		return Math.round(result);
	}

}
