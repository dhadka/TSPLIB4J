package org.moeaframework.problem.tsplib;

import java.io.File;
import java.io.IOException;

public class TestHCP {
	
	public static void main(String[] args) throws IOException {
		String[] instances = {
				"alb1000",
				"alb2000",
				"alb3000a",
				"alb3000b",
				"alb3000c",
				"alb3000d",
				"alb3000e",
				"alb4000",
				"alb5000"
		};
		
		for (String instance : instances) {
			File directory = new File("./data/hcp/");
			File instanceData = new File(directory, instance + ".hcp");
			File optimalTour = new File(directory, instance + ".opt.tour");
			
			if (instanceData.exists() && optimalTour.exists()) {
				TSPProblem problem = new TSPProblem(instanceData);
				problem.addTour(optimalTour);
				
				for (Tour tour : problem.getTours()) {
					System.out.print(instance);
					System.out.print(' ');
					System.out.print(tour.isHamiltonianCycle(problem));
					System.out.print(' ');
					System.out.print(tour.containsFixedEdges(problem));
					System.out.println();
				}
			}
		}
	}

}
