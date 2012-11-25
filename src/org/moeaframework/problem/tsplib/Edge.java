package org.moeaframework.problem.tsplib;

public class Edge {
	
	private final int id1;
	
	private final int id2;
	
	public Edge(int id1, int id2) {
		super();
		this.id1 = id1;
		this.id2 = id2;
	}

	public int getId1() {
		return id1;
	}

	public int getId2() {
		return id2;
	}

}
