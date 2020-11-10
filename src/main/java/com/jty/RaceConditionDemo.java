package com.jty;

import com.jty.common.utils.Tools;
import lombok.extern.slf4j.Slf4j;


/**
 * 假设秋创建40个线程,每个线程处理10个请求
 * 理论上输出的最大sequence为399,但是实际中要小于299并且有重复的,
 * 核心在于RequestIDGenerator.nextGenerator是被并发访问的，但是并没有做访问控制
 */

@Slf4j
public class RaceConditionDemo {


	public static void main(String[] args) throws Exception {
		// 客户端线程数
		int numberOfThreads = args.length > 0 ? Short.valueOf(args[0]) : Runtime
				.getRuntime().availableProcessors();
		Thread[] workerThreads = new Thread[numberOfThreads];

		log.info("一共{}个请求",numberOfThreads);
		for (int i = 0; i < numberOfThreads; i++) {
			workerThreads[i] = new WorkerThread(i, 10);
		}

		// 待所有线程创建完毕后，再一次性将其启动，以便这些线程能够尽可能地在同一时间内运行
		for (Thread ct : workerThreads) {
			ct.start();
		}
	}

	// 模拟业务线程
	static class WorkerThread extends Thread {
		private final int requestCount;

		public WorkerThread(int id, int requestCount) {
			super();
			String prefix = "worker-";
			// 一位数 补上0
			if (id < 10) {
				prefix = prefix + "0";
			}
			setName(prefix + id);
			this.requestCount = requestCount;
		}

		@Override
		public void run() {
			int i = requestCount;
			String requestID;
			RequestIDGenerator requestIDGen = RequestIDGenerator.getInstance();
			while (i-- > 0) {
				// 生成Request ID
				requestID = requestIDGen.nextID();
				processRequest(requestID);
			}
		}

		// 模拟请求处理
		private void processRequest(String requestID) {
			// 模拟请求处理耗时
			Tools.randomPause(50);
			System.out.printf("%s got requestID: %s %n",
					Thread.currentThread().getName(), requestID);
		}
	}

}
