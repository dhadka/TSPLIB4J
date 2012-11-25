package org.moeaframework.problem.tsplib;

public class GeographicalDistance extends DistanceFunction {
	
	public GeographicalDistance() {
		super();
	}
	
	public double toGeographical(double x) {
		double deg = Math.round(x);
		double min = x - deg;
		return Math.PI * (deg + 5.0 * min / 3.0) / 180.0;
	}
	
	@Override
	public double distance(int length, double[] position1, double[] position2) {
		if (length != 2) {
			throw new IllegalArgumentException("nodes must be 2D");
		}
		
		double latitude1 = toGeographical(position1[0]);
		double latitude2 = toGeographical(position2[0]);
		double longitude1 = toGeographical(position1[1]);
		double longitude2 = toGeographical(position2[1]);
		double radius = 6378.388;
		double q1 = Math.cos(longitude1 - longitude2);
		double q2 = Math.cos(latitude1 - latitude2);
		double q3 = Math.cos(latitude1 + latitude2);
		
		return Math.floor(radius * Math.acos(0.5 * ((1.0 + q1)*q2 - (1.0 - q1)*q3)) + 1.0);
	}

}
