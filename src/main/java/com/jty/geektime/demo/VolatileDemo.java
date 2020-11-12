package com.jty.geektime.demo;

import com.jty.common.utils.Tools;
import lombok.extern.slf4j.Slf4j;

/**
 * 演示volatile在多写的情况下线程不安全
 * volatile能满足可见性和有序性，但并不能满足原子性，所以一下的代码并不能保证线程安全
 */
@Slf4j
public class VolatileDemo {

	public static volatile int count = 0;
	public static final int times = 100000;

	public static void main(String[] args) {

		Thread thread = new Thread(() -> {
			Tools.sleep(2000);
			for (int i = 1; i <= times; i++) {
				count++;
			}
		});
		thread.start();

		for (int i = 1; i <= times; i++) {
			count--;
		}

		Tools.join(thread);
		log.info("child thread is alive: {} ", thread.isAlive());
		log.info("count value : {}", count);
	}

}
