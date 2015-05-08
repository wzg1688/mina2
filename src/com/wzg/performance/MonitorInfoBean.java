package com.wzg.performance;

import java.lang.management.MemoryUsage;
import java.util.concurrent.atomic.AtomicLong;

public class MonitorInfoBean {
	/** 可使用内存. */
	private long jvmTotalMemory;

	/** 剩余内存. */
	private long jvmFreeMemory;

	/** 最大可使用内存. */
	private long jvmMaxMemory;

	/** 操作系统. */
	private String osName;

	/** 总的物理内存. */
	private long physicalTotalMemorySize;

	/** 剩余的物理内存. */
	private long physicalFreeMemorySize;

	/** 已使用的物理内存. */
	private long physicalUsedMemory;

	/** 线程总数. */
	private int totalThread;

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
	// JVM启动时间
	public static long JVNstartTime;
	// web 工程启动时间
	public static long servletContextStartTime;

	public static AtomicLong sqlWriteCount = new AtomicLong();
	public static AtomicLong sqlReadCount = new AtomicLong();

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

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public long getJvmTotalMemory() {
		return jvmTotalMemory;
	}

	public void setJvmTotalMemory(long jvmTotalMemory) {
		this.jvmTotalMemory = jvmTotalMemory;
	}

	public long getJvmFreeMemory() {
		return jvmFreeMemory;
	}

	public void setJvmFreeMemory(long jvmFreeMemory) {
		this.jvmFreeMemory = jvmFreeMemory;
	}

	public long getJvmMaxMemory() {
		return jvmMaxMemory;
	}

	public void setJvmMaxMemory(long jvmMaxMemory) {
		this.jvmMaxMemory = jvmMaxMemory;
	}

	public long getPhysicalTotalMemorySize() {
		return physicalTotalMemorySize;
	}

	public void setPhysicalTotalMemorySize(long physicalTotalMemorySize) {
		this.physicalTotalMemorySize = physicalTotalMemorySize;
	}

	public long getPhysicalFreeMemorySize() {
		return physicalFreeMemorySize;
	}

	public void setPhysicalFreeMemorySize(long physicalFreeMemorySize) {
		this.physicalFreeMemorySize = physicalFreeMemorySize;
	}

	public long getPhysicalUsedMemory() {
		return physicalUsedMemory;
	}

	public void setPhysicalUsedMemory(long physicalUsedMemory) {
		this.physicalUsedMemory = physicalUsedMemory;
	}

	public int getTotalThread() {
		return totalThread;
	}

	public void setTotalThread(int totalThread) {
		this.totalThread = totalThread;
	}

}