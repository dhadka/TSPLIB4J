package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NodeCoordinates extends DistanceTable {
	
	private final int dimension;
	
	private final NodeCoordType type;
	
	private final DistanceFunction distanceFunction;

	private final Map<Integer, Node> nodes;
	
	public NodeCoordinates(int dimension, EdgeWeightType edgeWeightType) {
		this(dimension, edgeWeightType.getNodeCoordType(), edgeWeightType.getDistanceFunction());
	}
	
	public NodeCoordinates(int dimension, NodeCoordType type, DistanceFunction distanceFunction) {
		super();
		this.dimension = dimension;
		this.type = type;
		this.distanceFunction = distanceFunction;
		
		nodes = new HashMap<Integer, Node>();
	}
	
	@Override
	public void load(BufferedReader reader) throws IOException {
		for (int i = 0; i < dimension; i++) {
			String line = reader.readLine();
			String[] tokens = line.trim().split("\\s+");

			if (tokens.length != type.getLength() + 1) {
				throw new IOException("invalid number of tokens for node entry");
			}

			double[] position = new double[type.getLength()];
			int id = Integer.parseInt(tokens[0]);

			for (int j = 0; j < type.getLength(); j++) {
				position[j] = Double.parseDouble(tokens[j+1]);
			}

			add(new Node(id, position));
		}
	}
	
	public void add(Node node) {
		nodes.put(node.getId(), node);
	}
	
	public Node get(int id) {
		return nodes.get(id);
	}
	
	public void remove(int id) {
		nodes.remove(id);
	}
	
	public void clear() {
		nodes.clear();
	}
	
	public int size() {
		return nodes.size();
	}
	
	@Override
	public int[] listNodes() {
		int index = 0;
		int[] result = new int[dimension];
		
		for (Node node : nodes.values()) {
			result[index++] = node.getId();
		}
		
		return result;
	}

	@Override
	public int[] getNeighborsOf(int id) {
		int index = 0;
		int[] neighbors = new int[dimension-1];
		
		for (Node node : nodes.values()) {
			if (node.getId() != id) {
				neighbors[index++] = node.getId();
			}
		}
		
		return neighbors;
	}

	@Override
	public double getDistanceBetween(int id1, int id2) {
		return distanceFunction.distance(get(id1), get(id2));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Node node : nodes.values()) {
			sb.append(node.toString());
			sb.append('\n');
		}
		
		return sb.toString();
	}

}
