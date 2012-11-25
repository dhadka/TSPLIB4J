package org.moeaframework.problem.tsplib;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) throws IOException {
		Map<String, Integer> optimalResults = new LinkedHashMap<String, Integer>();
		optimalResults.put("a280", 2579);
		optimalResults.put("ali535", 202339);
		optimalResults.put("att48", 10628);
		optimalResults.put("att532", 27686);
		optimalResults.put("bayg29", 1610);
		optimalResults.put("bays29", 2020);
		optimalResults.put("berlin52", 7542);
		optimalResults.put("bier127", 118282);
		optimalResults.put("brazil58", 25395);
		optimalResults.put("brd14051", 469385);
		optimalResults.put("brg180", 1950);
		optimalResults.put("burma14", 3323);
		optimalResults.put("ch130", 6110);
		optimalResults.put("ch150", 6528);
		optimalResults.put("d198", 15780);
		optimalResults.put("d493", 35002);
		optimalResults.put("d657", 48912);
		optimalResults.put("d1291", 50801);
		optimalResults.put("d1655", 62128);
		optimalResults.put("d2103", 80450);
		optimalResults.put("d15112", 1573084);
		optimalResults.put("d18512", 645238);
		optimalResults.put("dantzig42", 699);
		optimalResults.put("dsj1000", 18660188);
		optimalResults.put("eil51", 426);
		optimalResults.put("eil76", 538);
		optimalResults.put("eil101", 629);
		optimalResults.put("fl417", 11861);
		optimalResults.put("fl1400", 20127);
		optimalResults.put("fl1577", 22249);
		optimalResults.put("fl3795", 28772);
		optimalResults.put("fnl4461", 182566);
		optimalResults.put("fri26", 937);
		optimalResults.put("gil262", 2378);
		optimalResults.put("gr17", 2085);
		optimalResults.put("gr21", 2707);
		optimalResults.put("gr24", 1272);
		optimalResults.put("gr48", 5046);
		optimalResults.put("gr96", 55209);
		optimalResults.put("gr120", 6942);
		optimalResults.put("gr137", 69853);
		optimalResults.put("gr202", 40160);
		optimalResults.put("gr229", 134602);
		optimalResults.put("gr431", 171414);
		optimalResults.put("gr666", 294358);
		optimalResults.put("hk48", 11461);
		optimalResults.put("kroA100", 21282);
		optimalResults.put("kroB100", 22141);
		optimalResults.put("kroC100", 20749);
		optimalResults.put("kroD100", 21294);
		optimalResults.put("kroE100", 22068);
		optimalResults.put("kroA150", 26524);
		optimalResults.put("kroB150", 26130);
		optimalResults.put("kroA200", 29368);
		optimalResults.put("kroB200", 29437);
		optimalResults.put("lin105", 14379);
		optimalResults.put("lin318", 42029);
		optimalResults.put("linhp318", 41345);
		optimalResults.put("nrw1379", 56638);
		optimalResults.put("p654", 34643);
		optimalResults.put("pa561", 2763);
		optimalResults.put("pcb442", 50778);
		optimalResults.put("pcb1173", 56892);
		optimalResults.put("pcb3038", 137694);
		optimalResults.put("pla7397", 23260728);
		optimalResults.put("pla33810", 66048945);
		optimalResults.put("pla85900", 142382641);
		optimalResults.put("pr76", 108159);
		optimalResults.put("pr107", 44303);
		optimalResults.put("pr124", 59030);
		optimalResults.put("pr136", 96772);
		optimalResults.put("pr144", 58537);
		optimalResults.put("pr152", 73682);
		optimalResults.put("pr226", 80369);
		optimalResults.put("pr264", 49135);
		optimalResults.put("pr299", 48191);
		optimalResults.put("pr439", 107217);
		optimalResults.put("pr1002", 259045);
		optimalResults.put("pr2392", 378032);
		optimalResults.put("rat99", 1211);
		optimalResults.put("rat195", 2323);
		optimalResults.put("rat575", 6773);
		optimalResults.put("rat783", 8806);
		optimalResults.put("rd100", 7910);
		optimalResults.put("rd400", 15281);
		optimalResults.put("rl1304", 252948);
		optimalResults.put("rl1323", 270199);
		optimalResults.put("rl1889", 316536);
		optimalResults.put("rl5915", 565530);
		optimalResults.put("rl5934", 556045);
		optimalResults.put("rl11849", 923288);
		optimalResults.put("si175", 21407);
		optimalResults.put("si535", 48450);
		optimalResults.put("si1032", 92650);
		optimalResults.put("st70", 675);
		optimalResults.put("swiss42", 1273);
		optimalResults.put("ts225", 126643);
		optimalResults.put("tsp225", 3916);
		optimalResults.put("u159", 42080);
		optimalResults.put("u574", 36905);
		optimalResults.put("u724", 41910);
		optimalResults.put("u1060", 224094);
		optimalResults.put("u1432", 152970);
		optimalResults.put("u1817", 57201);
		optimalResults.put("u2152", 64253);
		optimalResults.put("u2319", 234256);
		optimalResults.put("ulysses16", 6859);
		optimalResults.put("ulysses22", 7013);
		optimalResults.put("usa13509", 19982859);
		optimalResults.put("vm1084", 239297);
		optimalResults.put("vm1748", 336556);
		
		for (String key : optimalResults.keySet()) {
			File directory = new File("./data/tsp/");
			File instanceData = new File(directory, key + ".tsp");
			File optimalTour = new File(directory, key + ".opt.tour");
			
			if (instanceData.exists() && optimalTour.exists()) {
				TSPProblem problem = new TSPProblem();
				problem.load(instanceData);
				problem.addTour(optimalTour);
				
				for (Tour tour : problem.getTours()) {
					System.out.println(key + " " + tour.distance(problem.getDistanceTable()) + " " + optimalResults.get(key));
				}
			} else {
				System.err.println(key + " missing instance data or optimal tour");
			}
		}
	}

}
