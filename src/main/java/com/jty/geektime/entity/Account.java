package com.jty.geektime.entity;

public class Account {
	private int balance;
	// 转账
	public void transfer(Account target, int amt){
		synchronized(this){
			try {
				Thread.sleep(1000); // 为了让死锁现象明显
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(target){
				if (this.balance > amt){
					this.balance -= amt;
					target.balance += amt;
				}
			}
		}
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
