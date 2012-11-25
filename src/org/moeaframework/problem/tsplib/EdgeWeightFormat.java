package org.moeaframework.problem.tsplib;

public enum EdgeWeightFormat {
	
	/**
	 * Weights are given by a function.
	 */
	FUNCTION,
	
	/**
	 * Weights are given by a full matrix.
	 */
	FULL_MATRIX,
	
	/**
	 * Row-wise upper triangular matrix (excluding diagonal).
	 */
	UPPER_ROW,
	
	/**
	 * Row-wise lower triangular matrix (excluding diagonal).
	 */
	LOWER_ROW,
	
	/**
	 * Row-wise upper triangular matrix.
	 */
	UPPER_DIAG_ROW,
	
	/**
	 * Row-wise lower triangular matrix.
	 */
	LOWER_DIAG_ROW,
	
	/**
	 * Column-wise upper triangular matrix (without diagonal).
	 */
	UPPER_COL,
	
	/**
	 * Column-wise lower triangular matrix (without diagonal).
	 */
	LOWER_COL,
	
	/**
	 * Column-wise upper triangular matrix.
	 */
	UPPER_DIAG_COL,
	
	/**
	 * Column-wise lower triangular matrix.
	 */
	LOWER_DIAG_COL

}
