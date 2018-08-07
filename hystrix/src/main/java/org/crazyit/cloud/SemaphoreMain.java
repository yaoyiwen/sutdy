package org.crazyit.cloud;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;

public class SemaphoreMain {

	public static void main(String[] args) throws Exception {
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.execution.isolation.strategy", ExecutionIsolationStrategy.SEMAPHORE);
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests", 3);
		for(int i = 0; i < 6; i++) {
			final int index = i;
			Thread t = new Thread() {
				public void run() {
					MyCommand c = new MyCommand(index);
					c.execute();
				}
			};
			t.start();
		}
		Thread.sleep(5000);
	}

}
