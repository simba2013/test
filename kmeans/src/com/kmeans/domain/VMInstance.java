package com.kmeans.domain;

public class VMInstance {
	private int id;
	private int center;
	private OsInfo osInfo;
	private LogInfo logInfo;
	private NetworkInfo networkInfo;

	public VMInstance(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCenter() {
		return center;
	}

	public void setCenter(int center) {
		this.center = center;
	}

	public OsInfo getOsInfo() {
		return osInfo;
	}

	public void setOsInfo(OsInfo osInfo) {
		this.osInfo = osInfo;
	}

	public LogInfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}

	public NetworkInfo getNetworkInfo() {
		return networkInfo;
	}

	public void setNetworkInfo(NetworkInfo networkInfo) {
		this.networkInfo = networkInfo;
	}

	public VMInstance(int id,OsInfo osInfo, NetworkInfo netInfo, LogInfo logInfo) {
		this.id = id;
		this.osInfo = osInfo;
		this.networkInfo = netInfo;
		this.logInfo = logInfo;
	}

	@Override
	public String toString() {
		return osInfo.getLoadAvag()[0]
				+ ", " + osInfo.getLoadAvag()[1] + ", "
				+ osInfo.getLoadAvag()[2]  + "; " +osInfo.getCpuInfo()[0] + ", " + osInfo.getCpuInfo()[1] + ", "
				+ osInfo.getCpuInfo()[2] + "; " + osInfo.getMemInfo()[0]+","
				+ osInfo.getMemInfo()[1] + ", " + osInfo.getMemInfo()[2] + "; "
				+ logInfo.getUserName() + ", " + logInfo.getSshIp() + ", "
				+ logInfo.getSshTimes() + "; " + networkInfo.getMbPerSec()
				+ ", " + networkInfo.getTotal() + "----Belongs To class "
				+ center + ".";
	}
}
