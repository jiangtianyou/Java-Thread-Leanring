package com.jty;

/**
 * 测试并发时数据可见性
 */
public class NoVisibility {

	private static boolean ready;
	private static int number;

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (!ready)
					Thread.yield();
				System.out.println(number);
			}
		};
		new Thread(runnable).start();
		number = 42;
		ready = true;
	}

}
