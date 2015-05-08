package com.wzg.performance2;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class SystemInfoUtils {

	private static final SystemInfoBean infoBean = new SystemInfoBean();

	private SystemInfoUtils() {
	}

	public static SystemInfoBean getSystemInfo() {
		// 操作系统信息
		OperatingSystemMXBean operateSystemMBean = ManagementFactory.getOperatingSystemMXBean();
		String operateName = operateSystemMBean.getName();
		infoBean.setOperateName(operateName);
		int processListCount = operateSystemMBean.getAvailableProcessors();
		infoBean.setProcessListCount(processListCount);
		String archName = operateSystemMBean.getArch();// System.getProperty("os.arch");
		infoBean.setArchName(archName);
		String versionName = operateSystemMBean.getVersion();// System.getProperty("os.version");
		infoBean.setVersionName(versionName);

		// 运行时信息
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		String vmName = runtimeMXBean.getVmName();
		infoBean.setVmName(vmName);
		String vmVersion = runtimeMXBean.getVmVersion();
		// infoBean.setVmVersion(vmVersion);
		infoBean.setVmVersion(System.getProperty("java.version") + " (" + vmVersion + ")");
		String vmVendor = runtimeMXBean.getVmVendor();
		infoBean.setVmVendor(vmVendor);
		long startTime = runtimeMXBean.getStartTime();
		infoBean.setStartTime(new Date(startTime));

		infoBean.setArguments(runtimeMXBean.getInputArguments());
		infoBean.setSystemProperties(runtimeMXBean.getSystemProperties());

		// 类信息
		ClassLoadingMXBean classLoadMXBean = ManagementFactory.getClassLoadingMXBean();
		int loadClazzCount = classLoadMXBean.getLoadedClassCount();
		infoBean.setLoadClazzCount(loadClazzCount);
		long hasloadClazzCount = classLoadMXBean.getTotalLoadedClassCount();
		infoBean.setHasloadClazzCount(hasloadClazzCount);
		long hasUnloadClazzCount = classLoadMXBean.getUnloadedClassCount();
		infoBean.setHasUnloadClazzCount(hasUnloadClazzCount);

		// 内存
		MemoryMXBean memoryUsage = ManagementFactory.getMemoryMXBean();
		infoBean.setHeapMemoryUsage(memoryUsage.getHeapMemoryUsage());
		infoBean.setNonHeapMemoryUsage(memoryUsage.getNonHeapMemoryUsage());
		return infoBean;
	}

	public static void main(String[] args) {
		SystemInfoBean infoBean = SystemInfoUtils.getSystemInfo();

		System.out.println(infoBean);
		System.out.println(JSONObject.toJSON(infoBean));

	}
}
