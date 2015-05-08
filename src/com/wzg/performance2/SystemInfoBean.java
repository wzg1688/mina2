package com.wzg.performance2;

import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wzg.utils.DateUtils;


/**
 */
public class SystemInfoBean {
	// 加载类的数量
	private int loadClazzCount;
	// 已经加载类的数量
	private long hasloadClazzCount;
	// 尚未加载类的数量
	private long hasUnloadClazzCount;
	// 堆内存信息
	private MemoryUsage heapMemoryUsage;
	// 非堆内存信息
	private MemoryUsage nonHeapMemoryUsage;
	// 操作系统的名称
	private String operateName;
	// 操作系统的进程数
	private int processListCount;
	// 操作系统的架构
	private String archName;
	// 操作系统的版本号码
	private String versionName;
	// 虚拟机的名称
	private String vmName;
	// 虚拟机的版本
	private String vmVersion;
	// 系统的供应商的名称
	private String vmVendor;
	// JVM启动时间
	private Date startTime;
	// 输入参数
	private List<String> arguments;
	// 系统参数
	private Map<String, String> systemProperties;

	public int getLoadClazzCount() {
		return loadClazzCount;
	}

	public void setLoadClazzCount(int loadClazzCount) {
		this.loadClazzCount = loadClazzCount;
	}

	public long getHasloadClazzCount() {
		return hasloadClazzCount;
	}

	public void setHasloadClazzCount(long hasloadClazzCount) {
		this.hasloadClazzCount = hasloadClazzCount;
	}

	public long getHasUnloadClazzCount() {
		return hasUnloadClazzCount;
	}

	public void setHasUnloadClazzCount(long hasUnloadClazzCount) {
		this.hasUnloadClazzCount = hasUnloadClazzCount;
	}

	public MemoryUsage getHeapMemoryUsage() {
		return heapMemoryUsage;
	}

	public void setHeapMemoryUsage(MemoryUsage heapMemoryUsage) {
		this.heapMemoryUsage = heapMemoryUsage;
	}

	public MemoryUsage getNonHeapMemoryUsage() {
		return nonHeapMemoryUsage;
	}

	public void setNonHeapMemoryUsage(MemoryUsage nonHeapMemoryUsage) {
		this.nonHeapMemoryUsage = nonHeapMemoryUsage;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public int getProcessListCount() {
		return processListCount;
	}

	public void setProcessListCount(int processListCount) {
		this.processListCount = processListCount;
	}

	public String getArchName() {
		return archName;
	}

	public void setArchName(String archName) {
		this.archName = archName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getVmVersion() {
		return vmVersion;
	}

	public void setVmVersion(String vmVersion) {
		this.vmVersion = vmVersion;
	}

	public String getVmVendor() {
		return vmVendor;
	}

	public void setVmVendor(String vmVendor) {
		this.vmVendor = vmVendor;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	public Map<String, String> getSystemProperties() {
		return systemProperties;
	}

	public void setSystemProperties(Map<String, String> systemProperties) {
		this.systemProperties = systemProperties;
	}

	@Override
	public String toString() {
		return "系统信息:" + "\n 加载数个数: " + loadClazzCount + "\n 已经加载类个数: " + hasloadClazzCount + "\n 未加载类个数:" + hasUnloadClazzCount + "\n 堆内存信息: "
				+ heapMemoryUsage + "\n 非堆内存信息: " + nonHeapMemoryUsage + "\n 操作系统的名称: " + operateName + "\n 操作系统的进程数: " + processListCount + "\n 操作系统的架构: "
				+ archName + "\n 操作系统的版本号码: " + versionName + "\n 虚拟机的名称: " + vmName + "\n 虚拟机的版本: " + vmVersion + "\n 系统的供应商的名称: " + vmVendor + "\n JVM启动时间: "
				+ DateUtils.dateToString(startTime) + "\n 输入参数: " + arguments + "]";
	}

}