package com.kmeans.test;

public class DeadLoop {
	public static void main(String[] args) {
		int start = Integer.MAX_VALUE-1;
		for(int i=start; i<=start+1; i++){
			System.out.println("=");
		}
	}
}
