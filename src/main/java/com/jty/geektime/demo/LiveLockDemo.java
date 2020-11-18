package com.jty.geektime.demo;

/**
 * 演示什么是活锁
 *
 * 丈夫和老婆喝汤，但是只有一把勺子，但是拿到勺子之后，两者疯狂谦让。
 */
public class LiveLockDemo {
	static class Spoon {
		private Diner owner;
		public Spoon(Diner d) { owner = d; }
		public Diner getOwner() { return owner; }
		public synchronized void setOwner(Diner d) { owner = d; }
		public synchronized void use() {
			System.out.printf("%s has eaten!", owner.name);
		}
	}

	static class Diner {
		private String name;
		private boolean isHungry;

		public Diner(String n) { name = n; isHungry = true; }
		public String getName() { return name; }
		public boolean isHungry() { return isHungry; }

		public void eatWith(Spoon spoon, Diner spouse) {
			while (isHungry) {
				// Don't have the spoon, so wait patiently for spouse. 下面的两个if代表夫妇疯狂谦让
				if (spoon.owner != this) {
					try { Thread.sleep(1); }
					catch(InterruptedException e) { continue; }
					continue;
				}

				// If spouse is hungry, insist upon passing the spoon.
				if (spouse.isHungry()) {
					System.out.printf(
							"%s: You eat first my darling %s!%n",
							name, spouse.getName());
					spoon.setOwner(spouse);
					continue;
				}

				// 下面代表有人真正的拿着勺子吃饭
				spoon.use();
				isHungry = false;
				System.out.printf(
						"%s: I am stuffed, my darling %s!%n",
						name, spouse.getName());
				spoon.setOwner(spouse);
			}
		}
	}

	public static void main(String[] args) {
		final Diner husband = new Diner("Bob");
		final Diner wife = new Diner("Alice");

		final Spoon s = new Spoon(husband);

		new Thread(new Runnable() {
			public void run() { husband.eatWith(s, wife); }
		}).start();

		new Thread(new Runnable() {
			public void run() { wife.eatWith(s, husband); }
		}).start();
	}
}
