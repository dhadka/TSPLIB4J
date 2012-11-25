A Java library for TSPLIB
===

### About TSPLIB:

[TSPLIB](http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/) is a collection of [Traveling Salesman Problem](http://en.wikipedia.org/wiki/Travelling_salesman_problem) instances and a file format for storing TSP instance data.

Prerequisites:

    * Java 1.5 or later


### Reading TSP Instance Data:

TSPProblem problem = new TSPProblem();
problem.load(new File("./data/tsp/pcb442.tsp"));
problem.addTour(new File("./data/tsp/pcb442.opt.tour"));
		
for (Tour tour : problem.getTours()) {
	System.out.println(tour.distance(problem.getDistanceTable()));
}


Other Open Source Libraries
---
  - [MOEA Framework](http://www.moeaframework.org)
  - [DGantt](http://sourceforge.net/projects/dgantt/)
