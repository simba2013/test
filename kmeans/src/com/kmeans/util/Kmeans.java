package com.kmeans.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.kmeans.domain.LogInfo;
import com.kmeans.domain.NetworkInfo;
import com.kmeans.domain.OsInfo;
import com.kmeans.domain.VMInstance;

public class Kmeans {
	public ArrayList<VMInstance> allVMInstances = null;
	public int totalNumber = 0;
	public int k = 0;
	public ArrayList<ArrayList> lists = null;

	public Kmeans() {
		lists = new Tools().prepare();
		// 数据标准化
//		allVMInstances = new Tools().dataFormating(lists.get(0));
		 allVMInstances = lists.get(0);
		totalNumber = allVMInstances.size();
		k = 55;
	}

	// choose the cluster center automatically
	public Set<Integer> firstCenter() {
		Set<Integer> centers = new HashSet<Integer>();
		Random ran = new Random();
		int roll = ran.nextInt(totalNumber);
		while (centers.size() < k) {
			roll = ran.nextInt(totalNumber);
			centers.add(roll);
		}
		return centers;
	}

	/**
	 * 选取第一、第二个中心点
	 * 
	 * @return
	 */
	public ArrayList<VMInstance> centersChoosing() {
		ArrayList<VMInstance> centers = new ArrayList<VMInstance>();
		// 1.随机选取第一个点
		// TODO 考虑是否修改，去掉random随机因素
		Random ran = new Random();
		// int firstCenter = ran.nextInt(totalNumber);
		int firstCenter = 0;
		centers.add(allVMInstances.get(firstCenter));
		allVMInstances.remove(firstCenter);
		totalNumber--;

		// 2.选取距离中心点1距离最远的中心点2
		// 计算所有点到中心点1的距离的最大值
		double max = 0;
		double distance;
		int token = 0;
		for (int i = 0; i < allVMInstances.size(); i++) {
			distance = new Tools()
					.euDist(centers.get(0), allVMInstances.get(i));
			// 2.2取得每个点到中心点距离的最小值，记录index
			if (max < distance) {
				max = distance;
				// 2.2取最大距离值的点
				token = i;
			}
		}
		// 2.3将距离最大的index作为第二中心点，放入center中
		centers.add(allVMInstances.get(token));
		// 2.4 删除该点
		allVMInstances.remove(token);
		// 总数减1
		totalNumber--;

		// 3根据max（min1（），min2（），...，minN（））求得其他中心点。
		// 3.1确定min1（），min2（），...，minN（）
		for (int j = 2; j < k; j++) {
			ArrayList<Double> minDists = new ArrayList<Double>();
			for (int m = 0; m < allVMInstances.size(); m++) {
				VMInstance vmS = allVMInstances.get(m);
				double min = new Tools().euDist(vmS, centers.get(0));
				for (int n = 1; n < centers.size(); n++) {
					VMInstance vmC = centers.get(n);
					double dist = new Tools().euDist(vmS, vmC);
					if (min > dist) {
						min = dist;
					}
				}
				minDists.add(min);
			}
			// 3.2 选出max(min())
			double maxMin = 0;
			for (int i = 0; i < minDists.size(); i++) {
				if (maxMin < minDists.get(i)) {
					maxMin = minDists.get(i);
					token = i;
				}
			}
			centers.add(allVMInstances.get(token));
			allVMInstances.remove(token);
			totalNumber--;
		}
		return centers;
	}

	// initial the cluster info
	public ArrayList<Cluster> init(ArrayList<VMInstance> centers) {
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		Iterator<VMInstance> iter = centers.iterator();
		while (iter.hasNext()) {
			Cluster c = new Cluster();
			c.setCenter(iter.next().getId());
			clusters.add(c);
		}
		return clusters;
	}

	/**
	 * Calculate the distance from vminstance to center
	 * 
	 * @param center
	 * @param clusters
	 * @return
	 */
	public ArrayList<Cluster> clustering(ArrayList<VMInstance> center,
			ArrayList<Cluster> clusters) {
//		// 0622：将删除的center点重新加入到vmInstances中
		for (Iterator iter = center.iterator(); iter.hasNext();) {
			VMInstance vmCenter = (VMInstance) iter.next();
			allVMInstances.add(vmCenter);
			totalNumber++;
		}

		ArrayList<Distance> distances = new ArrayList<Distance>();
		VMInstance source = null;
		VMInstance destination = null;

		int idS = 0;
		int idD = 0;

		boolean flag = false;

		for (int i = 0; i < totalNumber; i++) {
			distances.clear();
			for (int j = 0; j < center.size(); j++) {
				source = allVMInstances.get(i);
				destination = center.get(j);
				distances.add(new Distance(i, destination.getId(), Tools
						.euDist(source, destination)));
			}
			double min = distances.get(0).getDist();
			int minid = 0;
			for (int k = 0; k < distances.size(); k++) {
				if (min > distances.get(k).getDist()) {
					min = distances.get(k).getDist();
					idS = distances.get(k).getStart();
					idD = distances.get(k).getDest();
					minid = k;
				} else {
					idS = distances.get(minid).getStart();
					idD = distances.get(minid).getDest();
				}
			}

			for (int n = 0; n < clusters.size(); n++) {
				if (clusters.get(n).getCenter() == idD) {
					clusters.get(n).addInstance(findCenterById(idD, center));
					clusters.get(n).addInstance(allVMInstances.get(idS));
					break;
				}
			}
		}
		return clusters;
	}

	
	public VMInstance findCenterById(int id, ArrayList<VMInstance> center){
		VMInstance vmInstance = null;
		for (Iterator iterator = center.iterator(); iterator.hasNext();) {
			VMInstance vm = (VMInstance) iterator.next();
			if (vm.getId() == id) {
				vmInstance = vm;
			}
		}
		return vmInstance;
	}
	
	
	public ArrayList<VMInstance> updateCenter() {
		ArrayList<VMInstance> centers = new ArrayList<VMInstance>();
		for (int i = 0; i < k; i++) {
			VMInstance vm = new VMInstance();
			vm.setId(i);
			centers.add(vm);
		}
		return centers;
	}

	public int findIndexById(int id, ArrayList<VMInstance> centers) {
		for (int i = 0; i < centers.size(); i++) {
			if (id == centers.get(i).getId()) {
				return i;
			}
		}
		return Integer.MAX_VALUE;
	}

	public ArrayList<Cluster> updateCluster(ArrayList<Cluster> cluster,
			ArrayList<VMInstance> centers) {
		ArrayList<Cluster> result = new ArrayList<Cluster>();
		int newId = totalNumber+1;
		for (int j = 0; j < k; j++) {
			ArrayList<VMInstance> ps = cluster.get(j).getInstances();
			ps.add(centers.get(j));
			int size = ps.size();
			double sumCpu1 = 0, sumCpu2 = 0, sumCpu3 = 0, sumLoad1 = 0, sumLoad2 = 0, sumLoad3 = 0, sumMem1 = 0, sumMem2 = 0, sumMem3 = 0, sumNetTotal = 0, sumNetMb = 0, sumCpuU = 0, sumMemU = 0;
			int sumSshTimes = 0;
			ArrayList<String> logNames = new ArrayList<String>();
			ArrayList<Integer> logNamesNum = new ArrayList<Integer>();
			ArrayList<String> logIps = new ArrayList<String>();
			ArrayList<Integer> logIpsNum = new ArrayList<Integer>();

			for (int k1 = 0; k1 < size; k1++) {
				sumCpu1 += ps.get(k1).getOsInfo().getCpuInfo()[0];
				sumCpu2 += ps.get(k1).getOsInfo().getCpuInfo()[1];
				sumCpu3 += ps.get(k1).getOsInfo().getCpuInfo()[2];
				sumLoad1 += ps.get(k1).getOsInfo().getLoadAvag()[0];
				sumLoad2 += ps.get(k1).getOsInfo().getLoadAvag()[1];
				sumLoad3 += ps.get(k1).getOsInfo().getLoadAvag()[2];
				sumMem1 += ps.get(k1).getOsInfo().getMemInfo()[0];
				sumMem2 += ps.get(k1).getOsInfo().getMemInfo()[1];
				sumMem3 += ps.get(k1).getOsInfo().getMemInfo()[2];
				sumNetTotal += ps.get(k1).getNetworkInfo().getTotal();
				sumNetMb += ps.get(k1).getNetworkInfo().getMbPerSec();
				sumSshTimes += ps.get(k1).getLogInfo().getSshTimes();
				String logName = ps.get(k1).getLogInfo().getUserName();
				String logIp = ps.get(k1).getLogInfo().getSshIp();

				if (!logNames.contains(logName)) {
					logNames.add(logName);
					int i = logNames.indexOf(logName);
					logNamesNum.add(0);
					logNamesNum.set(i, 1);
				} else {
					int nameNum = logNamesNum.get(logNames.indexOf(logName));
					logNamesNum.set(logNames.indexOf(logName), nameNum + 1);
				}
				if (!logIps.contains(logIp)) {
					logIps.add(logIp);
					logIpsNum.add(0);
					logIpsNum.set(logIps.indexOf(logIp), 1);
				} else {
					int ipNum = logIpsNum.get(logIps.indexOf(logIp));
					logIpsNum.set(logIps.indexOf(logIp), ipNum + 1);
				}
			}
			Cluster newCluster = new Cluster();
			newCluster.setCenter(j);
			double[] loadAvag = new double[3];
			loadAvag[0] = sumCpu1 / size;
			loadAvag[1] = sumCpu2 / size;
			loadAvag[2] = sumCpu3 / size;
			double[] cpuInfo = new double[3];
			cpuInfo[0] = sumLoad1 / size;
			cpuInfo[1] = sumLoad2 / size;
			cpuInfo[2] = sumLoad3 / size;
			double[] memInfo = new double[3];
			memInfo[0] = sumMem1 / size;
			memInfo[1] = sumMem2 / size;
			memInfo[2] = sumMem3 / size;
			OsInfo osInfo = new OsInfo(loadAvag, cpuInfo, memInfo);

			double[] netInfo = new double[2];
			netInfo[0] = sumNetTotal / size;
			netInfo[1] = sumNetMb / size;
			NetworkInfo networkInfo = new NetworkInfo();
			networkInfo.setTotal(netInfo[0]);
			networkInfo.setMbPerSec(netInfo[1]);

			double[] sshTimes = new double[1];
			sshTimes[0] = sumSshTimes / size;
			LogInfo logInfo = new LogInfo();
			logInfo.setSshTimes(sshTimes[0]);
			// 更新kmodes部分的距离

			logInfo.setSshIp(new Tools().maxTime(logIps, logIpsNum));
			logInfo.setUserName(new Tools().maxTime(logNames, logNamesNum));

			newCluster.addInstance(new VMInstance(newId++, osInfo, networkInfo,
					logInfo));
			result.add(newCluster);
		}
		return result;
	}

	public ArrayList<Cluster> updateClustering(ArrayList<Cluster> update,
			ArrayList<Cluster> clusters) {
		ArrayList<Distance> distances = new ArrayList<Distance>();
		VMInstance source = null;
		VMInstance destination = null;
		int idS = 0;
		int idD = 0;
		boolean flag = false;
		for (int i = 0; i < totalNumber; i++) {
			distances.clear();
			for (int j = 0; j < update.size(); j++) {
				flag = true;
				source = allVMInstances.get(i);
				destination = update.get(j).getInstances().get(0);
				distances.add(new Distance(i, update.get(j).getCenter(), Tools
						.euDist(source, destination)));
			}
			if (flag == true) {
				double min = distances.get(0).getDist();
				int mid = 0;
				for (int k = 1; k < distances.size(); k++) {
					if (min > distances.get(k).getDist()) {
						min = distances.get(k).getDist();
						idS = distances.get(k).getStart();
						idD = distances.get(k).getDest();
						mid = k;
					} else {
						idS = distances.get(mid).getStart();
						idD = distances.get(mid).getDest();
					}
				}
				for (int n = 0; n < clusters.size(); n++) {
					if (clusters.get(n).getCenter() == idD) {
						clusters.get(n).addInstance(allVMInstances.get(idS));
						break;
					}
				}
			}
		}
		return clusters;
	}

	public boolean isEquals(ArrayList<Cluster> temp, ArrayList<Cluster> result) {
		boolean flag = false;
		if (temp.size() != result.size()) {
			return flag;
		}
		for (Cluster tem : temp) {
			for (Cluster res : result) {
				if (tem.getCenter() == res.getCenter()) {
					flag = true;
				}
			}
			if (flag == false) {
				return false;
			} else {
				flag = false;
			}
		}
		flag = true;
		return flag;
	}

	public ArrayList<ArrayList> getResult() {
		ArrayList lastList = new ArrayList();
		ArrayList<Cluster> result = new ArrayList<Cluster>();
		ArrayList<Cluster> temp = new ArrayList<Cluster>();
		boolean flag = false;
		ArrayList<VMInstance> center = centersChoosing();
		// 第一次聚类
		ArrayList<Cluster> up = null;
		result = clustering(center, init(center));
		do {
			// 获得第一次聚类之后各个类粗的中间值点
			up = updateCluster(result, center);
			// 准备好k个类簇，用于后面放置实例
			ArrayList<Cluster> cluster = init(updateCenter());
			// 重新聚类
			temp = updateClustering(up, cluster);
			flag = isEquals(temp, result);
			result = temp;
		} while (!flag);
		lastList.add(up);
		lastList.add(result);
		// 存放分好到类
		for (int i = 0; i < result.size(); i++) {
			ArrayList<VMInstance> vmInstances = result.get(i).getInstances();
			int centerNum = result.get(i).getCenter();
			for (Iterator iter = vmInstances.iterator(); iter.hasNext();) {
				VMInstance vmInstance = (VMInstance) iter.next();

				// 2013-06-09 设置类别中心为类别编号
				vmInstance.setCenter(centerNum);

				
				// 打印每个类别中的数据
				System.out.println(vmInstance);
			}
			System.out
					.println("********************************************************");
		}
		return lastList;
	}
}
