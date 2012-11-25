package org.moeaframework.problem.tsplib;

public class EuclideanDistance extends DistanceFunction {
	
	public EuclideanDistance() {
		super();
	}
	
	@Override
	public double distance(int length, double[] position1, double[] position2) {
		double result = 0.0;

		for (int i = 0; i < length; i++) {
			result += Math.pow(position1[i] - position2[i], 2.0);
		}

		return Math.round(Math.sqrt(result));
	}

}
