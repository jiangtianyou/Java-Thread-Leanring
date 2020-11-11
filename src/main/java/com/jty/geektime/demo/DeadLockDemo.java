package com.jty.geektime.demo;

import com.jty.geektime.entity.Account;

/**
 * 演示死锁
 * 两个线程。一个从账户a到b转100元，另一个从账户b到a转100元。
 */
public class DeadLockDemo {

	public static void main(String[] args) {
		Account a = new Account();
		a.setBalance(100);

		Account b = new Account();
		b.setBalance(200);


		Thread aToB = new Thread(new Runnable() {
			@Override
			public void run() {
				a.transfer(b, 100);
			}
		});
		aToB.start();



		Thread bToA = new Thread(new Runnable() {
			@Override
			public void run() {
				b.transfer(a, 100);
			}
		});
		bToA.start();

	}

}


