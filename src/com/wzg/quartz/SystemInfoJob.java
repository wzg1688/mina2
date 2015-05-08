package com.wzg.quartz;

import java.lang.management.MemoryUsage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wzg.performance.IMonitorService;
import com.wzg.performance.MonitorInfoBean;
import com.wzg.performance.MonitorServiceImpl;
import com.wzg.utils.DateUtils;
import com.wzg.utils.MyLog;

public class SystemInfoJob implements Job {

	private static MyLog logger = MyLog.getPressureLogger(SystemInfoJob.class);

	public static final long 分钟5 = 1000 * 300;
	protected static final int MB = 1024 * 1024;
	private static final String ln = "\n";

	/**
	 * Empty constructor for job initialization
	 */
	public SystemInfoJob() {
	}

	/**
	 * <p>
	 * Called by the <code>{@link org.quartz.Scheduler}</code> when a
	 * <code>{@link org.quartz.Trigger}</code> fires that is associated with the
	 * <code>Job</code>.
	 * </p>
	 * 
	 * @throws JobExecutionException
	 *             if there is an exception while executing the job.
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {

		IMonitorService service = new MonitorServiceImpl();
		MonitorInfoBean monitorInfo;
		try {
			monitorInfo = service.getMonitorInfoBean();
			double used = monitorInfo.getJvmTotalMemory() - monitorInfo.getJvmFreeMemory();
			long temp = System.currentTimeMillis();
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(ln).append("操作系统: " + monitorInfo.getOsName()).append(ln).append("总的物理内存: " + monitorInfo.getPhysicalTotalMemorySize() + "MB")
					.append(ln).append("剩余的物理内存: " + monitorInfo.getPhysicalFreeMemorySize() + "MB").append(ln)
					.append("已使用的物理内存: " + monitorInfo.getPhysicalUsedMemory() + "MB").append(ln).append("线程总数: " + Thread.activeCount()).append(ln)
					.append("加载的类个数: " + monitorInfo.getLoadClazzCount()).append(ln).append("已加载的类个数: " + monitorInfo.getHasloadClazzCount()).append(ln)
					.append("未加载的类个数: " + monitorInfo.getHasUnloadClazzCount()).append(ln)
					.append("Tomcat 虚拟机最大内存量maxMemory(): " + monitorInfo.getJvmMaxMemory() + " MB").append(ln)
					.append("Tomcat 虚拟机内存总量totalMemory(): " + monitorInfo.getJvmTotalMemory() + " MB").append(ln)
					.append("Tomcat 虚拟机空闲内存量freeMemory(): " + monitorInfo.getJvmFreeMemory() + " MB").append(ln).append("JVM实际使用内存: " + used + " MB")
					.append(ln).append("Tomcat 已经运行: " + DateUtils.secondToDateString((temp - monitorInfo.JVNstartTime) / 1000)).append(ln)
					.append("StarLand 已经运行: " + DateUtils.secondToDateString((temp - monitorInfo.servletContextStartTime) / 1000)).append(ln);

			MemoryUsage heapMenoryUsage = monitorInfo.getHeapMemoryUsage();
			sBuilder.append(
					"堆内存 初始化" + (heapMenoryUsage.getInit() / MB) + " MB , 已使用: " + heapMenoryUsage.getUsed() / MB + " MB, 堆已分配: "
							+ heapMenoryUsage.getCommitted() / MB + " MB , 堆最大: " + heapMenoryUsage.getMax() / MB + " MB").append(ln);
			heapMenoryUsage = monitorInfo.getNonHeapMemoryUsage();
			sBuilder.append(
					"非堆内存 初始化" + (heapMenoryUsage.getInit() / MB) + " MB , 已使用: " + heapMenoryUsage.getUsed() / MB + " MB, 堆已分配: "
							+ heapMenoryUsage.getCommitted() / MB + " MB , 堆最大: " + heapMenoryUsage.getMax() / MB + " MB").append(ln);
			String pressureString = sBuilder.toString();
			logger.I(pressureString);
		} catch (Exception e) {
			logger.E("获取Tomcat内存信息错误 ！", e);
		}

	}

}
