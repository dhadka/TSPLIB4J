/* Copyright 2012 David Hadka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
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
