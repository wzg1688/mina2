package com.wzg.performance;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import com.sun.management.OperatingSystemMXBean;

/**
 * 获取系统信息的业务逻辑实现类.
 */
public class MonitorServiceImpl implements IMonitorService {

	protected static final int MB = 1024 * 1024;

	/**
	 * 获得当前的监控对象.
	 * 
	 * @return 返回构造好的监控对象
	 * @throws Exception
	 */
	public MonitorInfoBean getMonitorInfoBean() throws Exception {

		// 可使用内存
		long jvmTotalMemory = Runtime.getRuntime().totalMemory() / MB;
		// 剩余内存
		long jvmFreeMemory = Runtime.getRuntime().freeMemory() / MB;
		// 最大可使用内存
		long jvmMaxMemory = Runtime.getRuntime().maxMemory() / MB;

		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		// 操作系统
		String osName = System.getProperty("os.name");
		// 总的物理内存
		long physicalTotalMemorySize = osmxb.getTotalPhysicalMemorySize() / MB;
		// 剩余的物理内存
		long physicalFreeMemorySize = osmxb.getFreePhysicalMemorySize() / MB;
		// 已使用的物理内存
		long physicalUsedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / MB;

		// 获得线程总数
		// ThreadGroup parentThread;
		// for (parentThread = Thread.currentThread().getThreadGroup();
		// parentThread.getParent() != null; parentThread =
		// parentThread.getParent())
		// ;
		// int totalThread = parentThread.activeCount();

		MonitorInfoBean infoBean = new MonitorInfoBean();
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

		// 构造返回对象

		infoBean.setPhysicalFreeMemorySize(physicalFreeMemorySize);
		infoBean.setPhysicalTotalMemorySize(physicalTotalMemorySize);
		infoBean.setPhysicalUsedMemory(physicalUsedMemory);

		infoBean.setOsName(osName);

		infoBean.setJvmFreeMemory(jvmFreeMemory);
		infoBean.setJvmMaxMemory(jvmMaxMemory);
		infoBean.setJvmTotalMemory(jvmTotalMemory);

		return infoBean;
	}

	/**
	 * 测试方法.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		IMonitorService service = new MonitorServiceImpl();
		MonitorInfoBean monitorInfo = service.getMonitorInfoBean();

		System.out.println("操作系统: " + monitorInfo.getOsName());
		System.out.println("总的物理内存: " + monitorInfo.getPhysicalTotalMemorySize() + "MB");
		System.out.println("剩余的物理内存: " + monitorInfo.getPhysicalFreeMemorySize() + "MB");
		System.out.println("已使用的物理内存: " + monitorInfo.getPhysicalUsedMemory() + "MB");
		System.out.println("线程总数: " + monitorInfo.getTotalThread() + "MB");
		System.out.println();
		System.out.println("Java 虚拟机最大内存量maxMemory(): " + monitorInfo.getJvmMaxMemory() + " MB");
		System.out.println("Java 虚拟机内存总量totalMemory(): " + monitorInfo.getJvmTotalMemory() + " MB");
		System.out.println("Java 虚拟机空闲内存量freeMemory(): " + monitorInfo.getJvmFreeMemory() + " MB");
		double used = monitorInfo.getJvmTotalMemory() - monitorInfo.getJvmFreeMemory();
		System.out.println("JVM实际使用内存: " + used + " MB");
		System.out.println();
		long temp = System.currentTimeMillis();
		System.out.println("JVM已经运行: " + (temp - monitorInfo.JVNstartTime));
		System.out.println("StarLand已经运行: " + (temp - monitorInfo.servletContextStartTime));
		System.out.println("堆内存信息: " + monitorInfo.getHeapMemoryUsage());
		System.out.println("非堆内存信息: " + monitorInfo.getNonHeapMemoryUsage());

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
