package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The edges that are required to appear in each solution to the TSPLIB problem
 * instance.
 */
public class FixedEdges {
	
	/**
	 * The edges that must appear in each solution.
	 */
	private final List<Edge> edges;
	
	/**
	 * Constructs a new, empty list of edges that are required to appear in
	 * each solution.
	 */
	public FixedEdges() {
		super();
		
		edges = new ArrayList<Edge>();
	}
	
	/**
	 * Loads the edges that are required to appear in each solution from the
	 * specified reader.
	 * 
	 * @param reader the reader containing the edges
	 * @throws IOException if an I/O error occurred while reading the edges
	 */
	public void load(BufferedReader reader) throws IOException {
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			
			if (line.equals("-1")) {
				break;
			} else {
				String[] tokens = line.trim().split("\\s+");
				int id1 = Integer.parseInt(tokens[0]);
				int id2 = Integer.parseInt(tokens[1]);
				edges.add(new Edge(id1, id2));
			}
		}
	}

}
