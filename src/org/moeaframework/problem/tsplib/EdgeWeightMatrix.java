package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class EdgeWeightMatrix extends DistanceTable {
	
	private final int dimension;
	
	private final EdgeWeightFormat format;
	
	private final double[][] matrix;
	
	public EdgeWeightMatrix(int dimension, EdgeWeightFormat format) {
		super();
		this.dimension = dimension;
		this.format = format;
		
		matrix = new double[dimension][dimension];
	}
	
	private void readNextLine(BufferedReader reader, Queue<Double> entries) throws IOException {
		String line = reader.readLine();
		
		if (line == null) {
			throw new EOFException("unexpectedly reached EOF");
		}
		
		String[] tokens = line.trim().split("\\s+");
		
		for (int i = 0; i < tokens.length; i++) {
			entries.offer(Double.parseDouble(tokens[i]));
		}
	}
	
	@Override
	public void load(BufferedReader reader) throws IOException {
		int offset = 0;
		
		if (EdgeWeightFormat.UPPER_ROW.equals(format) ||
				EdgeWeightFormat.LOWER_ROW.equals(format) ||
				EdgeWeightFormat.UPPER_COL.equals(format) ||
				EdgeWeightFormat.LOWER_COL.equals(format)) {
			offset = 1;
		}
		
		Queue<Double> entries = new LinkedList<Double>();
		
		for (int i = 0; i < dimension - offset; i++) {
			if (EdgeWeightFormat.FULL_MATRIX.equals(format)) {
				for (int j = 0; j < dimension; j++) {
					if (entries.isEmpty()) {
						readNextLine(reader, entries);
					}
					
					matrix[i][j] = entries.poll();
				}
			} else {
				if (EdgeWeightFormat.UPPER_ROW.equals(format) ||
						EdgeWeightFormat.UPPER_COL.equals(format) ||
						EdgeWeightFormat.UPPER_DIAG_COL.equals(format) ||
						EdgeWeightFormat.UPPER_DIAG_ROW.equals(format)) {
					for (int j = 0; j < dimension - offset - i; j++) {
						if (entries.isEmpty()) {
							readNextLine(reader, entries);
						}

						matrix[i][j+i+1] = entries.poll();
						matrix[j+i+1][i] = matrix[i][j+i+1];
					}
				} else {
					for (int j = 0; j < i + 1 - offset; j++) {
						if (entries.isEmpty()) {
							readNextLine(reader, entries);
						}
						
						matrix[i][j] = entries.poll();
						matrix[j][i] = matrix[i][j];
					}
				}
			}
		}
		
		// sanity check
		if (!entries.isEmpty()) {
			throw new IOException("edge weight matrix is longer than expected");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (j > 0) {
					sb.append(' ');
				}
				
				sb.append(matrix[i][j]);
			}
			
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
	@Override
	public int[] listNodes() {
		int[] nodes = new int[dimension];
		
		for (int i = 1; i <= dimension; i++) {
			nodes[i-1] = i;
		}
		
		return nodes;
	}

	@Override
	public int[] getNeighborsOf(int id) {
		int index = 0;
		int[] neighbors = new int[dimension-1];
		
		for (int i = 1; i <= dimension; i++) {
			if (i != id) {
				neighbors[index++] = i;
			}
		}
		
		return neighbors;
	}

	@Override
	public double getDistanceBetween(int id1, int id2) {
		return matrix[id1-1][id2-1];
	}

}
