package com.kmeans.util;

import com.kmeans.domain.VMInstance;

public class Distance {

	private int dest;
	private int start;
	private double dist;

	public Distance() {

	}

	public Distance(int start, int dest, double dist) {
		this.start = start;
		this.dest = dest;
		this.dist = dist;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	

}
