package org.moeaframework.problem.tsplib;

public class Node {
	
	private final int id;
	
	private final double[] position;
	
	public Node(int id, double... position) {
		super();
		this.id = id;
		this.position = position;
	}

	public int getId() {
		return id;
	}

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
