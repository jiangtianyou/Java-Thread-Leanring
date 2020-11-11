package com.jty.geektime.demo;

import java.util.concurrent.ForkJoinPool;

public class RaceConditionDemo {
	public static void main(String[] args) {
		ForkJoinPool.commonPool().execute(()-> {
			System.out.println("something");
		});

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		new Thread(() -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("子线程在运行");

		}).start();
		System.out.println("主线程结束");
	}
}
