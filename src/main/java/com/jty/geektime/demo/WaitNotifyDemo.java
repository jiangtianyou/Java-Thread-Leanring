package com.jty.geektime.demo;

import com.jty.geektime.entity.AccountV2;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 演示等待通知机制--- 使用synchronized结合notify继续实现
 * 使用Allocator对资源进行统一的管理
 */

@Slf4j
public class WaitNotifyDemo {
	public static void main(String[] args) {

		AccountV2 a = new AccountV2();
		a.setBalance(1000);

		AccountV2 b = new AccountV2();
		b.setBalance(1000);

		int times = 1000;  //分别起1000个线程 a转b1元 b转a1元 预期结果为a余额1000b余额1000
		CountDownLatch latch = new CountDownLatch(2 * times);

		for (int i = 0; i < times; i++) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					a.transfer(b, 1);
					latch.countDown();
				}
			});
			t1.setName("thread-" + i + "a->b");


			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					b.transfer(a, 1);
					latch.countDown();
				}
			});
			t2.setName("thread-" + i + "b->a");

			t1.start();
			t2.start();
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		log.info("当前a账户余额:{},b账户余额:{}", a.getBalance(), b.getBalance());

	}

}



