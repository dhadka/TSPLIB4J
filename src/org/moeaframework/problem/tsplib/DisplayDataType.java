package org.moeaframework.problem.tsplib;

/**
 * Enumeration of ways a graphical display can be generated from the data.
 */
public enum DisplayDataType {
	
	/**
	 * The display is generated from the node coordinates.
	 */
	COORD_DISPLAY,
	
	/**
	 * Explicit coordinates in 2-D are given.
	 */
	TWOD_DISPLAY,
	
	/**
	 * No graphical display is available.
	 */
	NO_DISPLAY

}
