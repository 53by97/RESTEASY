package com.kvvssut.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * java.util.concurrent.ThreadPoolExecutor.ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
 * 
 * corePoolSize the number of threads to keep in the pool, even if they are idle, unless allowCoreThreadTimeOut is set
 * maximumPoolSize the maximum number of threads to allow in the pool
 * keepAliveTime when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.
 * unit the time unit for the keepAliveTime argument
 * workQueue the queue to use for holding tasks before they are executed. This queue will hold only the Runnable tasks submitted by the execute method.
 */

public class JobExecutor {

	private final ThreadPoolExecutor threadPoolExecutor;

	public JobExecutor() {
		BlockingQueue<Runnable> jobQueue = new ArrayBlockingQueue<Runnable>(100);
		threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.MINUTES, jobQueue);
		threadPoolExecutor.allowCoreThreadTimeOut(true);
	}

	public void executor(Runnable runnable) {
		this.threadPoolExecutor.execute(runnable);
	}

}
