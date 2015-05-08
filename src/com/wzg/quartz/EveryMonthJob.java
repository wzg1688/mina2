package com.wzg.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wzg.utils.MyLog;

public class EveryMonthJob implements Job {

	private static MyLog logger = MyLog.getPressureLogger(EveryMonthJob.class);

	/**
	 * Empty constructor for job initialization
	 */
	public EveryMonthJob() {
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
		logger.I("每月1号1点 执行的任务");
	}

}
