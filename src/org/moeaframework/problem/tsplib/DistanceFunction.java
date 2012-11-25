package org.moeaframework.problem.tsplib;

public abstract class DistanceFunction {

	public double distance(Node node1, Node node2) {
		double[] position1 = node1.getPosition();
		double[] position2 = node2.getPosition();
		
		if (position1.length != position2.length) {
			throw new IllegalArgumentException("nodes are not the same dimension");
		}
		
		return distance(position1.length, position1, position2);
	}
	
	public abstract double distance(int length, double[] position1, double[] position2);
	
}
