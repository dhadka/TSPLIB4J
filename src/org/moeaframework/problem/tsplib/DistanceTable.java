package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class DistanceTable {
	
	public abstract int[] listNodes();
	
	public abstract int[] getNeighborsOf(int id);
	
	public abstract double getDistanceBetween(int id1, int id2);
	
	public abstract void load(BufferedReader reader) throws IOException;

}
