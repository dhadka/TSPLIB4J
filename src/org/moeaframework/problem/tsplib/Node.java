package org.moeaframework.problem.tsplib;

/**
 * Represents a node (i.e., city) or arbitrary dimension.
 */
public class Node {
	
	/**
	 * The identifier of this node.
	 */
	private final int id;
	
	/**
	 * The position of this node.
	 */
	private final double[] position;
	
	/**
	 * Constructs a new node with the specified identifier and position.
	 * 
	 * @param id the identifier of this node
	 * @param position the position of this node
	 */
	public Node(int id, double... position) {
		super();
		this.id = id;
		this.position = position;
	}

	/**
	 * Returns the identifier of this node.
	 * 
	 * @return the identifier of this node
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the position of this node.
	 * 
	 * @return the position of this node
	 */
	public double[] getPosition() {
		return position;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		
		for (int i = 0; i < position.length; i++) {
			sb.append(' ');
			sb.append(position[i]);
		}
		
		return sb.toString();
	}

}
