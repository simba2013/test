package com.kmeans.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.kmeans.domain.LogInfo;
import com.kmeans.domain.NetworkInfo;
import com.kmeans.domain.OsInfo;
import com.kmeans.domain.VMInstance;

public class Tools {

	/**
	 * 标准化sshTime
	 * 
	 * @param datas
	 */
	public void sshTimeFormating(ArrayList<VMInstance> datas) {
		// 1.对所有数据的每个属性进行最大、最小值判断
		double temp;
		double sshTimeMax = 0;
		double sshTimeMin;
		sshTimeMin = datas.get(0).getLogInfo().getSshTimes();
		for (int i = 0; i < datas.size(); i++) {
			temp = datas.get(i).getLogInfo().getSshTimes();
			if (sshTimeMin > temp) {
				sshTimeMin = temp;
			} else if (sshTimeMax < temp) {
				sshTimeMax = temp;
			}
		}
		// 2.对属性值进行0-1标准化
		double times;
		for (int j = 0; j < datas.size(); j++) {
			times = datas.get(j).getLogInfo().getSshTimes();
			if ((sshTimeMax - sshTimeMin) != 0) {
				datas.get(j)
						.getLogInfo()
						.setSshTimes(
								(times - sshTimeMin)
										/ (sshTimeMax - sshTimeMin));
			} else {
				datas.get(j).getLogInfo().setSshTimes(0);
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 标准化cpuInfo
	 * 
	 * @param datas
	 */
	public void cpuInfoFormating(ArrayList<VMInstance> datas) {
		// 1.对所有数据的每个属性进行最大、最小值判断
		for (int k = 0; k < 3; k++) {
			double temp;
			double maxValue = 0;
			double minValue;
			minValue = datas.get(0).getOsInfo().getCpuInfo()[k];
			for (int i = 0; i < datas.size(); i++) {
				temp = datas.get(i).getOsInfo().getCpuInfo()[k];
				if (minValue > temp) {
					minValue = temp;
				} else if (maxValue < temp) {
					maxValue = temp;
				}
			}
			// 2.对属性值进行0-1标准化
			double value;
			double res;
			for (int j = 0; j < datas.size(); j++) {
				value = datas.get(j).getOsInfo().getCpuInfo()[k];
				if ((maxValue - minValue) != 0) {
					res = ((value - minValue) / (maxValue - minValue));
					datas.get(j).getOsInfo().getCpuInfo()[k] = res;
				} else {
					datas.get(j).getOsInfo().getCpuInfo()[k] = 0;
				}
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 标准化loadAvag
	 * 
	 * @param datas
	 */
	public void loadAvagFormating(ArrayList<VMInstance> datas) {
		// 1.对所有数据的每个属性进行最大、最小值判断
		for (int k = 0; k < 3; k++) {
			double temp;
			double maxValue = 0;
			double minValue;
			minValue = datas.get(0).getOsInfo().getLoadAvag()[k];
			for (int i = 0; i < datas.size(); i++) {
				temp = datas.get(i).getOsInfo().getLoadAvag()[k];
				if (minValue > temp) {
					minValue = temp;
				} else if (maxValue < temp) {
					maxValue = temp;
				}
			}
			// 2.对属性值进行0-1标准化
			double value;
			for (int j = 0; j < datas.size(); j++) {
				value = datas.get(j).getOsInfo().getLoadAvag()[k];
				if ((maxValue - minValue) != 0) {
					datas.get(j).getOsInfo().getLoadAvag()[k] = ((value - minValue) / (maxValue - minValue));
				} else {
					datas.get(j).getOsInfo().getLoadAvag()[k] = 0;
				}
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 标准化memInfo
	 * 
	 * @param datas
	 */
	public void memInfoFormating(ArrayList<VMInstance> datas) {
		// 1.对所有数据的每个属性进行最大、最小值判断
		for (int k = 0; k < 3; k++) {
			double temp;
			double maxValue = 0;
			double minValue;
			minValue = datas.get(0).getOsInfo().getMemInfo()[k];
			for (int i = 0; i < datas.size(); i++) {
				temp = datas.get(i).getOsInfo().getMemInfo()[k];
				if (minValue > temp) {
					minValue = temp;
				} else if (maxValue < temp) {
					maxValue = temp;
				}
			}
			// 2.对属性值进行0-1标准化
			double value;
			for (int j = 0; j < datas.size(); j++) {
				value = datas.get(j).getOsInfo().getMemInfo()[k];
				if ((maxValue - minValue) != 0) {
					datas.get(j).getOsInfo().getMemInfo()[k] = ((value - minValue) / (maxValue - minValue));
				} else {
					datas.get(j).getOsInfo().getMemInfo()[k] = 0;
				}
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 格式化netotal
	 * 
	 * @param datas
	 */
	public void netTotalFormating(ArrayList<VMInstance> datas) {
		// 1.对所有数据的每个属性进行最大、最小值判断
		double temp;
		double maxValue = 0;
		double minValue;
		minValue = datas.get(0).getNetworkInfo().getTotal();
		for (int i = 0; i < datas.size(); i++) {
			temp = datas.get(i).getNetworkInfo().getTotal();
			if (minValue > temp) {
				minValue = temp;
			} else if (maxValue < temp) {
				maxValue = temp;
			}
		}
		// 2.对属性值进行0-1标准化
		double value;
		for (int j = 0; j < datas.size(); j++) {
			value = datas.get(j).getNetworkInfo().getTotal();
			if ((maxValue - minValue) != 0) {
				datas.get(j).getNetworkInfo()
						.setTotal((value - minValue) / (maxValue - minValue));
			} else {
				datas.get(j).getNetworkInfo().setTotal(0);
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * netMbperSec
	 * 
	 * @param datas
	 */
	public void netMbPerSecFormating(ArrayList<VMInstance> datas) {
		// 1.对所有数据的每个属性进行最大、最小值判断
		double temp;
		double maxValue = 0;
		double minValue;
		minValue = datas.get(0).getNetworkInfo().getMbPerSec();
		for (int i = 1; i < datas.size(); i++) {
			temp = datas.get(i).getNetworkInfo().getMbPerSec();
			if (minValue > temp) {
				minValue = temp;
			} else if (maxValue < temp) {
				maxValue = temp;
			}
		}
		// 2.对属性值进行0-1标准化
		double value;
		for (int j = 0; j < datas.size(); j++) {
			value = datas.get(j).getNetworkInfo().getMbPerSec();
			if ((maxValue - minValue) != 0) {
				double dataFormat = (value - minValue) / (maxValue - minValue);
				datas.get(j).getNetworkInfo().setMbPerSec(dataFormat);
			} else {
				datas.get(j).getNetworkInfo().setMbPerSec(0);
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 格式化所有数据 TODO minfoo.txt文档数据没有计算在内
	 * 
	 * @param datas
	 */
	public ArrayList<VMInstance> dataFormating(ArrayList<VMInstance> datas) {
		netMbPerSecFormating(datas);
		netTotalFormating(datas);
		memInfoFormating(datas);
		loadAvagFormating(datas);
		cpuInfoFormating(datas);
		sshTimeFormating(datas);
		return datas;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static double euDist(VMInstance vm1, VMInstance vm2) {
		double distance1 = (Double) Math.sqrt(StrictMath.pow(vm1.getOsInfo()
				.getCpuInfo()[1] - vm2.getOsInfo().getCpuInfo()[1], 2)
				+ StrictMath.pow(vm1.getOsInfo().getCpuInfo()[2]
						- vm2.getOsInfo().getCpuInfo()[2], 2)
				+ StrictMath.pow(vm1.getOsInfo().getCpuInfo()[0]
						- vm2.getOsInfo().getCpuInfo()[0], 2)
				+ StrictMath.pow(vm1.getOsInfo().getLoadAvag()[1]
						- vm2.getOsInfo().getLoadAvag()[1], 2)
				+ StrictMath.pow(vm1.getOsInfo().getLoadAvag()[2]
						- vm2.getOsInfo().getLoadAvag()[2], 2)
				+ StrictMath.pow(vm1.getOsInfo().getLoadAvag()[0]
						- vm2.getOsInfo().getLoadAvag()[0], 2)
				+ StrictMath.pow(vm1.getOsInfo().getMemInfo()[1]
						- vm2.getOsInfo().getMemInfo()[1], 2)
				+ StrictMath.pow(vm1.getOsInfo().getMemInfo()[2]
						- vm2.getOsInfo().getMemInfo()[2], 2)
				+ StrictMath.pow(vm1.getOsInfo().getMemInfo()[0]
						- vm2.getOsInfo().getMemInfo()[0], 2)
				+ StrictMath.pow(vm1.getNetworkInfo().getTotal()
						- vm2.getNetworkInfo().getTotal(), 2)
				+ StrictMath.pow(vm1.getNetworkInfo().getMbPerSec()
						- vm2.getNetworkInfo().getMbPerSec(), 2)
				+ StrictMath.pow(vm1.getLogInfo().getSshTimes()
						- vm2.getLogInfo().getSshTimes(), 2));
		// Integer[][] distance2 = new Integer[2][2];
		// //第一个2：代表vm1，vm2；第二个2：代表2个属性
		String ip1 = vm1.getLogInfo().getSshIp();
		String ip2 = vm2.getLogInfo().getSshIp();

		String name1 = vm1.getLogInfo().getUserName();
		String name2 = vm2.getLogInfo().getUserName();
		double distance2 = 0;
		if (!ip1.equals(ip2)) {
			distance2 += 1;
		}
		if (!name1.equals(name2)) {
			distance2 += 1;
		}

		double distance = distance1 + distance2;
		return distance;
	}

	public double[] intDataReader(String fileName) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(fileName));
		String line = null;
		double[] result = new double[3];
		int i = 0;
		while ((line = bf.readLine()) != null) {
			result[i] = Double.parseDouble(line);
			i++;
		}
		return result;
	}

	public ArrayList<String> stringDataReader(String fileName)
			throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(fileName));
		String line = null;
		ArrayList<String> result = new ArrayList<String>();
		while ((line = bf.readLine()) != null) {
			result.add(line);
		}
		return result;
	}

	public VMInstance testKNNReader() {
		VMInstance vmInstance = null;
		try {
			OsInfo osInfo = null;
			ArrayList<VMInstance> allVmInstances = new ArrayList<VMInstance>(); // 存储所有虚拟机实例
			ArrayList<String> logNames = new ArrayList<String>(); // 存储登录用户名
			ArrayList<Integer> logNamesNum = new ArrayList<Integer>(); // 存储登录用户名次数
			ArrayList<String> logIps = new ArrayList<String>(); // 存储ssh到ip地址
			ArrayList<Integer> logIpsNum = new ArrayList<Integer>(); // 存储ssh到ip地址次数

			// k确定测试集到数量
			for (int k = 50; k <= 50; k++) {
				double[] loadAvag = intDataReader("/mnt/backup/" + k
						+ "/cinfo1.txt");
				double[] cpuInfo = intDataReader("/mnt/backup/" + k
						+ "/cinfo2.txt");
				double[] memInfo = intDataReader("/mnt/backup/" + k
						+ "/cinfo3.txt");
				osInfo = new OsInfo(loadAvag, cpuInfo, memInfo);
				NetworkInfo networkInfo = new NetworkInfo();
				double[] netInfo = intDataReader("/mnt/backup/" + k
						+ "/net.txt");
				networkInfo.setTotal(netInfo[0]);
				networkInfo.setMbPerSec(netInfo[1]);

				// 设置ssh相关信息
				LogInfo logInfo = new LogInfo();
				double[] lInfo = intDataReader("/mnt/backup/" + k
						+ "/linfo.txt");
				double ssh = lInfo[0];
				logInfo.setSshTimes(ssh);
				ArrayList<String> log = stringDataReader("/mnt/backup/" + k
						+ "/log.txt");
				String logName = log.get(0);
				String logIp = log.get(1);
				logInfo.setUserName(logName);
				logInfo.setSshIp(logIp);
				// 保存各个logName、logIp以及其出现的次数
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

				vmInstance = new VMInstance(k, osInfo, networkInfo, logInfo);
			}
			// loadAvag2 = intDataReader("/mnt/backup/56/cinfo1.txt");
			// double[] cpuInfo2 = intDataReader("/mnt/backup/56/cinfo2.txt");
			// double[] memInfo2 = intDataReader("/mnt/backup/56/cinfo3.txt");
			// OsInfo osInfo2 = new OsInfo(loadAvag2, cpuInfo2, memInfo2);
			//
			// NetworkInfo networkInfo2 = new NetworkInfo();
			// double[] netInfo2 = intDataReader("/mnt/backup/56/net.txt");
			// networkInfo2.setTotal(netInfo2[0]);
			// networkInfo2.setMbPerSec(netInfo2[1]);
			//
			// LogInfo logInfo2 = new LogInfo();
			// double[] lInfo2 = intDataReader("/mnt/backup/56/linfo.txt");
			// double ssh2 = lInfo2[0];
			// logInfo2.setSshTimes(ssh2);
			// ArrayList<String> log2 =
			// stringDataReader("/mnt/backup/56/log.txt");
			// String logName2 = log2.get(0);
			// String logIp2 = log2.get(1);
			// logInfo2.setUserName(logName2);
			// logInfo2.setSshIp(logIp2);
			// vmInstance = new VMInstance(2, osInfo2, networkInfo2, logInfo2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vmInstance;
	}

	public ArrayList<ArrayList> prepare() {
		ArrayList<ArrayList> lists = new ArrayList<ArrayList>();
		try {
			ArrayList<VMInstance> allVmInstances = new ArrayList<VMInstance>(); // 存储所有虚拟机实例
			ArrayList<String> logNames = new ArrayList<String>(); // 存储登录用户名
			ArrayList<Integer> logNamesNum = new ArrayList<Integer>(); // 存储登录用户名次数
			ArrayList<String> logIps = new ArrayList<String>(); // 存储ssh到ip地址
			ArrayList<Integer> logIpsNum = new ArrayList<Integer>(); // 存储ssh到ip地址次数

			// for循环完成数据读取功能
			OsInfo osInfo = null;
			// 通过k值决定读取数据的数量
			for (int k = 1; k < 56; k++) {
				double[] loadAvag = intDataReader("/mnt/backup/" + k
						+ "/cinfo1.txt");
				double[] cpuInfo = intDataReader("/mnt/backup/" + k
						+ "/cinfo2.txt");
				double[] memInfo = intDataReader("/mnt/backup/" + k
						+ "/cinfo3.txt");
				osInfo = new OsInfo(loadAvag, cpuInfo, memInfo);

				NetworkInfo networkInfo = new NetworkInfo();
				double[] netInfo = intDataReader("/mnt/backup/" + k
						+ "/net.txt");
				networkInfo.setTotal(netInfo[0]);
				networkInfo.setMbPerSec(netInfo[1]);

				// 设置ssh相关信息
				LogInfo logInfo = new LogInfo();
				double[] lInfo = intDataReader("/mnt/backup/" + k
						+ "/linfo.txt");
				double ssh = lInfo[0];
				logInfo.setSshTimes(ssh);
				ArrayList<String> log = stringDataReader("/mnt/backup/" + k
						+ "/log.txt");
				String logName = log.get(0);
				String logIp = log.get(1);
				logInfo.setUserName(logName);
				logInfo.setSshIp(logIp);
				// 保存各个logName、logIp以及其出现的次数
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

				VMInstance vmInstance = new VMInstance(k, osInfo, networkInfo,
						logInfo);
				allVmInstances.add(vmInstance);
			}
			// 添加各arrayList进入lists中
			lists.add(allVmInstances);
			lists.add(logNames);
			lists.add(logNamesNum);
			lists.add(logIps);
			lists.add(logIpsNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public String maxTime(ArrayList<String> arrayList1,
			ArrayList<Integer> arrayList2) {
		int max = arrayList2.get(0);
		int k = 0;
		HashMap<String, Integer> maxTime = new HashMap<String, Integer>();
		for (int i = 0; i < arrayList2.size(); i++) {
			if (max < arrayList2.get(i)) {
				max = arrayList2.get(i);
				k = i;
			}
		}
		String maxIp = arrayList1.get(k);
		return maxIp;
	}
	// public static void main(String[] args) {
	// prepare();
	// System.out.println("1");
	// }
}
