package com.jty.reenterant;

/**
 * 测试Synchronized关键字的锁对象
 * 1、静态方法上锁对象是class 与 代码块里syncrhonized(ReentrantTest.class)等效
 * 2、普通方法上锁对象是当前实例 与 代码里syncrhonized(ReentrantTest.class)等效
 */
public class ReentrantTest {

	private Object LOCK = new Object();

	public static void main(String[] args) {
		// 测试多个对象调用同步方法是否能够锁住
		ReentrantTest a = new ReentrantTest();
		ReentrantTest b = new ReentrantTest();

		Runnable runnable1 = new Runnable() {
			@Override
			public void run() {
				a.a();
			}
		};

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				b.a();
			}
		};

		Thread thread1 = new Thread(runnable1);
		Thread thread = new Thread(runnable);

		thread1.start();
		thread.start();
	}

	public void a() {
		System.out.println("a");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void b() {
		System.out.println("a");
	}

}
