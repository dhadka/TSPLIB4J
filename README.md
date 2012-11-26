A Java library for TSPLIB
===

### About TSPLIB4J:

[TSPLIB](http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/) is a collection of [traveling salesman](http://en.wikipedia.org/wiki/Travelling_salesman_problem), [vehicle routing](http://en.wikipedia.org/wiki/Vehicle_routing_problem), and [Hamiltonian cycle](http://en.wikipedia.org/wiki/Hamiltonian_path_problem) problem instances and a file format for storing instance data.  TSPLIB4J is a Java library for reading and processing TSPLIB instance data.  TSPLIB4J is licensed under the MIT license.

Prerequisites:

  - Java 1.5 or later
  - [JUnit 4](http://www.junit.org/) to run test cases
  
### Setup

Download any or all of the [TSPLIB problem instances](http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/) problem instances.  It is recommended you follow the directory structure used in this project.  Create a data/ directory, and inside this directly create a folder for each problem instance.  The resulting structure should look like:

    data/atsp
    data/hcp
    data/sop
    data/tsp
    data/vrp

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
