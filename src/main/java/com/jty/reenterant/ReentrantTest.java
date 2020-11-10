package com.jty.reenterant;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试Synchronized关键字的锁对象
 * 1、静态方法上锁对象是class 与 代码块里syncrhonized(ReentrantTest.class)等效
 * 2、普通方法上锁对象是当前实例 与 代码里syncrhonized(ReentrantTest.class)等效
 */
@Slf4j
public class ReentrantTest {

	public static void main(String[] args) {


		// 测试map 使用capacity和不使用capacity效率的影响

		long begin = System.currentTimeMillis();
		HashMap<String, Integer> map = new HashMap<>();

		for (int i = 0; i < 100000; i++) {
			map.put("k" + i, i);
		}
		log.info("times1 : {}", System.currentTimeMillis() - begin);


		begin = System.currentTimeMillis();
		HashMap<String, Integer> map1 = new HashMap<>(1000000);

		for (int i = 0; i < 100000; i++) {
			map1.put("k" + i, i);
		}
		log.info("times2 : {}", System.currentTimeMillis() - begin);

	}
}
