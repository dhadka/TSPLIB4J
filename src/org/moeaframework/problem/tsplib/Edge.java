package org.moeaframework.problem.tsplib;

/**
 * An edge between two nodes.
 */
public class Edge {
	
	/**
	 * The identifier of the first node.
	 */
	private final int id1;
	
	/**
	 * The identifier of the second node.
	 */
	private final int id2;
	
	/**
	 * Constructs an edge between two nodes.
	 * 
	 * @param id1 the identifier of the first node
	 * @param id2 the identifier of the second node
	 */
	public Edge(int id1, int id2) {
		super();
		this.id1 = id1;
		this.id2 = id2;
	}

	/**
	 * Returns the identifier of the first node.
	 * 
	 * @return the identifier of the first node
	 */
	public int getId1() {
		return id1;
	}

	/**
	 * Returns the identifier of the second node.
	 * 
	 * @return the identifier of the second node
	 */
	public int getId2() {
		return id2;
	}

}
