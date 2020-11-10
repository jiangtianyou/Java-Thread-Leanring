package com.jty.geektime;

import com.jty.geektime.entity.Account;
import com.jty.geektime.entity.AccountV2;
import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 演示等待通知机制--- 使用synchronized结合notify继续实现
 * 使用Allocator对资源进行统一的管理
 */

@Slf4j
public class WaitNotifyDemo {
	public static void main(String[] args) {

		AccountV2 a = new AccountV2();
		a.setBalance(100);

		AccountV2 b = new AccountV2();
		b.setBalance(100);

		CountDownLatch latch = new CountDownLatch(2);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				a.transfer(b, 10);
				latch.countDown();
			}
		});
		t1.setName("t1-a2b");


		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				b.transfer(a, 100);
				latch.countDown();
			}
		});
		t2.setName("t2-b2a");


		t1.start();
		t2.start();

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("当前线程:{}", Thread.currentThread().getName());

		log.info("当前a账户余额:{},b账户余额:{}", a.getBalance(), b.getBalance());

	}

}



