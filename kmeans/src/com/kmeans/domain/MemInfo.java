package com.kmeans.domain;

public class MemInfo {
	private String userName;
	private double cpuUsed;
	private double memUsed;
	private String command;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getCpuUsed() {
		return cpuUsed;
	}
	public void setCpuUsed(double cpuUsed) {
		this.cpuUsed = cpuUsed;
	}
	public double getMemUsed() {
		return memUsed;
	}
	public void setMemUsed(double memUsed) {
		this.memUsed = memUsed;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	
	
	
}
