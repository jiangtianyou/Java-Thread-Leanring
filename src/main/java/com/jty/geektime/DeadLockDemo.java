package com.jty.geektime;

/**
 * 演示死锁
 */
public class DeadLockDemo {

	public static void main(String[] args) {
		Account a = new Account();
		a.setBalance(100);

		Account b = new Account();
		b.setBalance(200);

		// 两个线程 a -> b 100 b -> a 100

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


class Account {
	private int balance;
	// 转账
	void transfer(Account target, int amt){
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