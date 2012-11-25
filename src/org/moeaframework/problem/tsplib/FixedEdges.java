package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FixedEdges {
	
	private final List<Edge> edges;
	
	public FixedEdges() {
		super();
		
		edges = new ArrayList<Edge>();
	}
	
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
