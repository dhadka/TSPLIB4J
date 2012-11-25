package org.moeaframework.problem.tsplib;

public class PseudoEuclideanDistance extends DistanceFunction {
	
	public PseudoEuclideanDistance() {
		super();
	}
	
	@Override
	public double distance(int length, double[] position1, double[] position2) {
		if (length != 2) {
			throw new IllegalArgumentException("nodes must be 2D");
		}
		
		double xd = position1[0] - position2[0];
		double yd = position1[1] - position2[1];
		double r = Math.sqrt((Math.pow(xd, 2.0) + Math.pow(yd, 2.0)) / 10.0);
		int t = (int)(r);

		if (t < r) {
			return t + 1.0;
		} else {
			return t;
		}
	}

}
