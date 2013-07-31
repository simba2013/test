package com.kmeans.util;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.kmeans.domain.KnnNode;
import com.kmeans.domain.VMInstance;

public class Knn {
	/**
	 * 设置优先级队列到比较函数，距离越大，优先级越高
	 */
	private Comparator<KnnNode> comparator = new Comparator<KnnNode>() {
		public int compare(KnnNode k1, KnnNode k2){
			if(k1.getDistance() >= k2.getDistance())
				return 1;
			else {
				return 0;
			}
		}
	};
	
	/**
	 * 得到k个范围为max的随机数组
	 * @param k
	 * @param max
	 * @return
	 */
	public ArrayList<Integer> getRandNum(int k, int max){
		ArrayList<Integer> randNums = new ArrayList<Integer>(k);
		for(int i=0; i<k; i++){
			int rand = (int)(max * Math.random());
			if(!randNums.contains(rand)){
				randNums.add(rand);
			}else {
				i--;
			}
		}
		return randNums;
	}
	
	/**
	 * 计算测试样本和训练样本之间到距离
	 * @param vm1	测试样本
	 * @param vm2	训练样本
	 * @return
	 */
	public 	double calDistance(VMInstance vm1, VMInstance vm2){
		return Tools.euDist(vm1, vm2);
	}
	
	/**
	 * 计算测试样本和k个学习样本之间的最短距离
	 * @param datas	样本集
	 * @param testData	测试数据
	 * @param k
	 * @return
	 */
	public String knnMethod(ArrayList<VMInstance> datas, VMInstance testData,int k){
		PriorityQueue<KnnNode> pq = new PriorityQueue<KnnNode>(k, comparator);
		ArrayList<Integer> randoms = getRandNum(k, datas.size());
		for(int i=0; i<k; i++){
			int index = randoms.get(i);
			VMInstance currData = datas.get(index);
			// 获得样本集类别
			int belongTo = currData.getCenter();
			KnnNode knnNode = new KnnNode(index, calDistance(testData, currData), belongTo);
			pq.add(knnNode);
		}
		
		for(int i=0; i<datas.size(); i++){
			VMInstance vmTemple = datas.get(i);
			double distance = calDistance(testData, vmTemple);
			KnnNode kNode = pq.peek();
			if(distance < kNode.getDistance()){
				pq.remove();
				// 获得样本集类别
				pq.add(new KnnNode(i, distance, vmTemple.getCenter()));
			}
		}
		
		return getMostClass(pq);
	}
	
	/**
	 * 获取k个样本中所属类别数目最多的名称
	 * @param pq
	 * @return
	 */
	public String getMostClass(PriorityQueue<KnnNode> pq){
		Map<Integer , Integer> classCounts = new HashMap<Integer, Integer>();
		for(int i=0; i<pq.size(); i++){
			KnnNode knnNode = pq.poll();
			int belongTo = knnNode.getBelongTo();
			if(classCounts.containsKey(belongTo)){
				classCounts.put(belongTo, classCounts.get(belongTo)+1);
			}else {
				classCounts.put(belongTo, 1);
			}
		}
		
		int maxIndex = 1;
		int maxCount = 0;
		Object[] clazzes = classCounts.keySet().toArray();
		for(int i=0; i<clazzes.length; i++){
			if(classCounts.get(clazzes[i]) >maxCount){
				maxIndex = i;
				maxCount = classCounts.get(clazzes[i]);
			}
		}
		return clazzes[maxIndex].toString();
	}
	
		
}
