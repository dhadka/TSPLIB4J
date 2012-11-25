package org.moeaframework.problem.tsplib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TSPProblem {
	
	private String name;
	
	private DataType dataType;
	
	private String comment;
	
	private int dimension;
	
	private int capacity;
	
	private EdgeWeightType edgeWeightType;
	
	private EdgeWeightFormat edgeWeightFormat;
	
	private EdgeDataFormat edgeDataFormat;
	
	private NodeCoordType nodeCoordinateType;
	
	private DisplayDataType displayDataType;
	
	private DistanceTable distanceTable;
	
	private NodeCoordinates displayData;
	
	private FixedEdges fixedEdges;
	
	private List<Tour> tours;
	
	public TSPProblem() {
		super();
		
		tours = new ArrayList<Tour>();
	}
	
	public void load(File file) throws IOException {
		BufferedReader reader = null;
		String line = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				
				if (line.equals("NODE_COORD_SECTION")) {
					if (nodeCoordinateType == null) {
						nodeCoordinateType = edgeWeightType.getNodeCoordType();
					}
					
					distanceTable = new NodeCoordinates(dimension, edgeWeightType);
					distanceTable.load(reader);
				} else if (line.equals("EDGE_WEIGHT_SECTION")) {
					distanceTable = new EdgeWeightMatrix(dimension, edgeWeightFormat);
					distanceTable.load(reader);
				} else if (line.equals("DISPLAY_DATA_SECTION")) {
					displayData = new NodeCoordinates(dimension, NodeCoordType.TWOD_COORDS, null);
					displayData.load(reader);
				} else if (line.equals("TOUR_SECTION") || line.equals("-1")) {
					Tour tour = new Tour();
					tour.load(reader);
					tours.add(tour);
				} else if (line.equals("FIXED_EDGES_SECTION")) {
					fixedEdges = new FixedEdges();
					fixedEdges.load(reader);
				} else if (line.equals("EOF")) {
					break;
				} else {
					String[] tokens = line.split(":");
					String key = tokens[0].trim();
					String value = tokens[1].trim();
					
					if (key.equals("NAME")) {
						name = value;
					} else if (key.equals("COMMENT")) {
						comment = value;
					} else if (key.equals("TYPE")) {
						dataType = DataType.valueOf(value);
					} else if (key.equals("DIMENSION")) {
						dimension = Integer.parseInt(value);
					} else if (key.equals("CAPACITY")) {
						capacity = Integer.parseInt(value);
					} else if (key.equals("EDGE_WEIGHT_TYPE")) {
						edgeWeightType = EdgeWeightType.valueOf(value);
					} else if (key.equals("EDGE_WEIGHT_FORMAT")) {
						edgeWeightFormat = EdgeWeightFormat.valueOf(value);
					} else if (key.equals("EDGE_DATA_FORMAT")) {
						edgeDataFormat = EdgeDataFormat.valueOf(value);
					} else if (key.equals("NODE_COORD_FORMAT")) {
						nodeCoordinateType = NodeCoordType.valueOf(value);
					} else if (key.equals("DISPLAY_DATA_TYPE")) {
						displayDataType = DisplayDataType.valueOf(value);
					}
				}
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		// fill in default settings
		if (nodeCoordinateType == null) {
			nodeCoordinateType = NodeCoordType.NO_COORDS;
		}
		
		if (displayDataType == null) {
			if (NodeCoordType.NO_COORDS.equals(nodeCoordinateType)) {
				displayDataType = DisplayDataType.NO_DISPLAY;
			} else if (displayData != null) {
				displayDataType = DisplayDataType.TWOD_DISPLAY;
			} else {
				displayDataType = DisplayDataType.COORD_DISPLAY;
			} 
		}
	}
	
	public void addTour(Tour tour) {
		tours.add(tour);
	}
	
	public void addTour(File file) throws IOException {
		TSPProblem problem = new TSPProblem();
		problem.load(file);
		
		if (problem.getDataType().equals(DataType.TOUR)) {
			tours.addAll(problem.getTours());
		} else {
			throw new IllegalArgumentException("not a tour file");
		}
	}
	
	public String getName() {
		return name;
	}

	public DataType getDataType() {
		return dataType;
	}

	public String getComment() {
		return comment;
	}

	public int getDimension() {
		return dimension;
	}

	public int getCapacity() {
		return capacity;
	}

	public EdgeWeightType getEdgeWeightType() {
		return edgeWeightType;
	}

	public EdgeWeightFormat getEdgeWeightFormat() {
		return edgeWeightFormat;
	}

	public EdgeDataFormat getEdgeDataFormat() {
		return edgeDataFormat;
	}

	public NodeCoordType getNodeCoordinateType() {
		return nodeCoordinateType;
	}

	public DisplayDataType getDisplayDataType() {
		return displayDataType;
	}

	public DistanceTable getDistanceTable() {
		return distanceTable;
	}

	public NodeCoordinates getDisplayData() {
		return displayData;
	}

	public FixedEdges getFixedEdges() {
		return fixedEdges;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public static void main(String[] args) throws IOException {
		TSPProblem problem = new TSPProblem();
		problem.load(new File("./data/tsp/gr666.tsp"));
		problem.addTour(Tour.createCanonicalTour(problem.getDimension()));
		//problem.addTour(new File("./data/tsp/gr666.opt.tour"));
		
		for (Tour tour : problem.getTours()) {
			System.out.println(tour.distance(problem.getDistanceTable()));
		}
		
		//System.out.println(problem.getDistanceTable());
	}

}
