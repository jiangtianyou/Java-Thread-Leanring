package com.jty.geektime.demo;


import com.jty.common.utils.Tools;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示两阶段终止一个线程
 */
@Slf4j
public class TwoPhraseTerminationDemo {

	public static void main(String[] args) throws Exception {

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Runnable> runnables = executorService.shutdownNow();
		//


		// 适配器模式
		List<String> strings = Arrays.asList("1", "2");


		executorService.submit(() -> {
		});
	}




}
