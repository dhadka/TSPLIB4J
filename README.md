A Java library for TSPLIB
===

### About TSPLIB4J:

[TSPLIB](http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/) is a collection of [Traveling Salesman Problem](http://en.wikipedia.org/wiki/Travelling_salesman_problem) instances and a file format for storing TSP instance data.  TSPLIB4J is a Java library for reading TSPLIB instance data.  TSPLIB4J is licensed under the MIT license.

Prerequisites:

  - Java 1.5 or later
  - [TSPLIB problem instances](http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/)


### Traveling Salesman Problem:

    TSPProblem problem = new TSPProblem(new File("./data/tsp/pcb442.tsp"));
    problem.addTour(new File("./data/tsp/pcb442.opt.tour"));
    		
    for (Tour tour : problem.getTours()) {
    	System.out.println(tour.distance(problem));
    }
    
### Hamiltonian Cycle Problem:

    TSPProblem problem = new TSPProblem(new File("./data/hcp/alb1000.hcp"));
    problem.addTour(new File("./data/hcp/alb1000.opt.tour"));
    
    for (Tour tour : problem.getTours()) {
    	System.out.println(tour.isHamiltonianCycle(problem));
    }


Other Open Source Libraries
---
  - [MOEA Framework](http://www.moeaframework.org)
  - [DGantt](http://sourceforge.net/projects/dgantt/)
