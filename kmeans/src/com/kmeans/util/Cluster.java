package com.kmeans.util;

import java.util.ArrayList;
import java.util.List;

import com.kmeans.domain.VMInstance;

public class Cluster {
	private int center;
	private ArrayList<VMInstance> instances = new ArrayList<VMInstance>();
	public int getCenter() {
		return center;
	}
	public void setCenter(int id) {
		this.center = id;
	}
	
	
	public ArrayList<VMInstance> getInstances() {
		return instances;
	}
	public void setInstances(ArrayList<VMInstance> instances) {
		this.instances = instances;
	}
	public void addInstance(VMInstance vm){
		if(!instances.contains(vm)){
			instances.add(vm);
		}
	}
	
}
