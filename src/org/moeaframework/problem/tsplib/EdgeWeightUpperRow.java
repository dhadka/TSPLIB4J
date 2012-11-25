package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.IOException;

public class EdgeWeightUpperRow {
	
	private final int dimension;
	
	private final double[][] matrix;
	
	public EdgeWeightUpperRow(int dimension) {
		super();
		this.dimension = dimension;
		
		matrix = new double[dimension][dimension];
	}
	
	public void load(BufferedReader reader) throws IOException {
		for (int i = 0; i < dimension; i++) {
			String line = reader.readLine().trim();
			String[] tokens = line.split("\\s+");
			
			for (int j = 0; j < dimension-i; j++) {
				matrix[i][j] = Double.parseDouble(tokens[j]);
				matrix[j][i] = matrix[i][j];
			}
		}
	}

}
