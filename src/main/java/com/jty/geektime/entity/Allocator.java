package com.jty.geektime.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用Allocator对共享资源进行统一管理，这是一种避免死锁的方法。
 */
@Slf4j
public class Allocator {
	private List<Object> als = new ArrayList<>();

	// 使用最简单的单例方式
	private static Allocator INSTANCE = new Allocator();

	public static Allocator getInstance() {
		return INSTANCE;
	}

	// 一次性申请所有资源
	public synchronized void apply(
			Object from, Object to){
		// 经典写法
		while(als.contains(from) || als.contains(to)){
			log.info(Thread.currentThread().getName() + "进入等待队列");
			try{
				wait();
			}catch(Exception e){
			}
		}
		log.info(Thread.currentThread().getName() + "线程正在转账");
		als.add(from);
		als.add(to);
	}
	// 归还资源
	public  synchronized void free(Object from, Object to){
		als.remove(from);
		als.remove(to);
		log.info(Thread.currentThread().getName() + "通知等待队列");
		notifyAll();
	}
}
