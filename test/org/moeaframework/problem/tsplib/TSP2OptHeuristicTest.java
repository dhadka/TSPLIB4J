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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the {@link TSP2OptHeuristic} class.
 */
public class TSP2OptHeuristicTest {
	
	/**
	 * Saves the string to a temporary file.
	 * 
	 * @param string the contents of the temporary file
	 * @return the temporary file containing the specified string contents
	 * @throws IOException if an I/O error occurred while creating or writing
	 *         the temporary file
	 */
	private File save(String string) throws IOException {
		File file = File.createTempFile("tsp", ".tsp");
		file.deleteOnExit();
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(new FileWriter(file));
			writer.write(string);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		
		return file;
	}
	
	/**
	 * Tests the boundary case of 3 nodes; no change to the tour should occur.
	 * 
	 * @throws IOException should not occur
	 */
	@Test
	public void test3Node() throws IOException {
		File file = save(
				"NAME: Test 3 Node\n" +
				"TYPE: TSP\n" +
				"DIMENSION: 3\n" +
				"EDGE_WEIGHT_TYPE: EUC_2D\n" +
				"NODE_COORD_SECTION\n" +
				"1  0  0\n" +
				"2  0 10\n" +
				"3 10 10\n" +
				"EOF");
		
		TSPInstance instance = new TSPInstance(file);
		Tour tour = Tour.createCanonicalTour(3);
		
		TSP2OptHeuristic heuristic = new TSP2OptHeuristic(instance);
		heuristic.apply(tour);
		
		Assert.assertTrue(tour.isEquivalent(Tour.createTour(1, 2, 3)));
	}
	
	/**
	 * Tests if a simple tour with intersecting edges is correctly improved by
	 * the 2-opt heuristic.  Because TSPLIB rounds to integers, the node
	 * coordinates must be spaced sufficiently far apart so that the
	 * intersecting edges have a length longer than the non-intersecting edges.
	 * 
	 * @throws IOException should not occur
	 */
	@Test
	public void test4Node() throws IOException {
		File file = save(
				"NAME: Test 4 Node\n" +
				"TYPE: TSP\n" +
				"DIMENSION: 4\n" +
				"EDGE_WEIGHT_TYPE: EUC_2D\n" +
				"NODE_COORD_SECTION\n" +
				"1  0  0\n" +
				"2  0 10\n" +
				"3 10 10\n" +
				"4 10  0\n" +
				"EOF");
		
		TSPInstance instance = new TSPInstance(file);
		Tour tour = Tour.createTour(1, 3, 2, 4);

		TSP2OptHeuristic heuristic = new TSP2OptHeuristic(instance);
		heuristic.apply(tour);

		Assert.assertTrue(tour.isEquivalent(Tour.createTour(1, 2, 3, 4)));
	}

}
