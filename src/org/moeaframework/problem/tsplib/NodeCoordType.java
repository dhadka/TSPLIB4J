package org.moeaframework.problem.tsplib;

public enum NodeCoordType {
	
	/**
	 * Nodes are specified by coordinates in 2-D.
	 */
	TWOD_COORDS(2),
	
	/**
	 * Nodes are specified by coordinates in 3-D.
	 */
	THREED_COORDS(3),
	
	/**
	 * Nodes do not have associated coordinates.
	 */
	NO_COORDS(-1);
	
	private final int length;
	
	private NodeCoordType(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}

}
