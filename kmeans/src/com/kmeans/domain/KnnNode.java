package com.kmeans.domain;

public class KnnNode {
	
	public KnnNode(int index, double distance, int belongTo){
		this.index = index;
		this.distance = distance;
		this.belongTo = belongTo;
	}
	
	private int index;	//与之计算距离到数组编号;
	private double distance;	//与样本节点距离计算
	private int belongTo;	//样本数据所属类型
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(int belongTo) {
		this.belongTo = belongTo;
	}
	
	
}
