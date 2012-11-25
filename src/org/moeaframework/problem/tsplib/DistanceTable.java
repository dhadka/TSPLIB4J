package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * A distance table provides a lookup of the distances between the nodes in a
 * TSPLIB problem instance.
 */
public abstract class DistanceTable {
	
	/**
	 * Returns the identifiers of all nodes in this distance table.
	 * 
	 * @return the identifiers of all nodes in this distance table
	 */
	public abstract int[] listNodes();
	
	/**
	 * Returns the identifiers of all neighbors of the specified node.  A
	 * neighbor must have a direct edge between itself and the specified
	 * node.
	 * 
	 * @param id the identifier of the node whose neighbors are enumerated
	 * @return the identifiers of all neighbors of the specified node
	 * @throws IllegalArgumentException if no node exists with the specified
	 *         identifier
	 */
	public abstract int[] getNeighborsOf(int id);
	
	/**
	 * Returns the distance between the two specified nodes.
	 * 
	 * @param id1 the identifier of the first node
	 * @param id2 the identifier of the second node
	 * @return the distance between the two specified nodes
	 * @throws IllegalArgumentException if there is no direct edge between the
	 *         two nodes, or if no node exists with the specified identifier
	 */
	public abstract double getDistanceBetween(int id1, int id2);
	
	/**
	 * Loads the distance table from the specified reader.
	 * 
	 * @param reader the reader containing the distance table
	 * @throws IOException if an I/O error occurred while reading the distance
	 *         table
	 */
	public abstract void load(BufferedReader reader) throws IOException;

}
