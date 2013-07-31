package com.kmeans.domain;

public class OsInfo {
	private double[] loadAvag;
	private double[] cpuInfo;
	private double[] memInfo;
	
	public OsInfo(double[] loadAvag, double[] cpuInfo,double[] memInfo){
		this.loadAvag = loadAvag;
		this.cpuInfo = cpuInfo;
		this.memInfo = memInfo;
	}
	
	public double[] getLoadAvag() {
		return loadAvag;
	}
	public void setLoadAvag(double[] loadAvag) {
		this.loadAvag = loadAvag;
	}
	public double[] getCpuInfo() {
		return cpuInfo;
	}
	public void setCpuInfo(double[] cpuInfo) {
		this.cpuInfo = cpuInfo;
	}
	public double[] getMemInfo() {
		return memInfo;
	}
	public void setMemInfo(double[] memInfo) {
		this.memInfo = memInfo;
	}
	
}
