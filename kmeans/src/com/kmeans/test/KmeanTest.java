package com.kmeans.test;

import java.util.ArrayList;
import java.util.Iterator;

import com.kmeans.domain.VMInstance;
import com.kmeans.util.Cluster;
import com.kmeans.util.Kmeans;
import com.kmeans.util.Knn;
import com.kmeans.util.Tools;

public class KmeanTest {
	public static void main(String[] args) {
		Tools tools = new Tools();

		// 1 调用kmeans算法聚类
		Kmeans kmeans = new Kmeans();
		ArrayList<VMInstance> datas = kmeans.allVMInstances;
		ArrayList<ArrayList> list = kmeans.getResult();
		// 2 得到分好到类别，计算每个类的半径，以及所有类半径到平均值
		ArrayList<Cluster> result = list.get(1);
		ArrayList<Cluster> centerClusters = list.get(0);
		double max = 0;
		double sumMax = 0;
		for (int i = 0; i < result.size(); i++) {
			ArrayList<VMInstance> vmInstances = result.get(i).getInstances();
			
			VMInstance center = centerClusters.get(i).getInstances().get(0);
			
			for (Iterator iterator = vmInstances.iterator(); iterator.hasNext();) {
				VMInstance vmInstance = (VMInstance) iterator.next();
				double distance = tools.euDist(vmInstance, center);
				// 计算簇内最大距离
				if (max < distance) {
					max = distance;
				}
			}
			sumMax += max;
		}
		System.out.println(sumMax/result.size());

		 //3 读取测试数据，根据上面kmeans算法得到到聚类进行分析
		 VMInstance testData = tools.testKNNReader();
		 
		 //4 计算距离，设置center，打印
		 Knn knn = new Knn();
		 String claszz = knn.knnMethod(datas, testData, 15);
		 testData.setCenter(Integer.parseInt(claszz));
		 System.out.println("============================================================================================================");
		 System.out.println(testData);

	}
}
