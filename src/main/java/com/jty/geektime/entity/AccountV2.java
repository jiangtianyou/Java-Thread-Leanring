package com.jty.geektime.entity;

/**
 * 账号类
 * 使用Allocator对转账时的共享对象管理，避免死锁
 */
public class AccountV2 {

	// 必须是单例
	private static Allocator allocator = Allocator.getInstance();

	private int balance;

	// 转账
	public void transfer(AccountV2 target, int amt){
		allocator.apply(this, target); //申请共享资源 让其他线程进入等待队列
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (this.balance > amt){
			this.balance -= amt;
			target.balance += amt;
		}
		allocator.free(this, target); // 释放共享资源 通知其他等待队列重新执行
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
