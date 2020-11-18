package com.jty.geektime.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 演示非线程安全集合的ConcurrentModificationDemo
 */
@Slf4j
public class ConcurrentModificationDemo {
	public static void main(String[] args) {
		ConcurrentModificationDemo demo = new  ConcurrentModificationDemo();
		demo.demo1();
		demo.demo2();
	}
	public void demo1() {
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		Iterator<Integer> iterator = arrayList.iterator();

		// 下面的使用方式是错的
		while (iterator.hasNext()) {
			Integer element = iterator.next();
			arrayList.remove(element);
		}
	}


	public void demo2() {
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		// 下面的使用方式是错的
		for (Integer integer : arrayList) {
			arrayList.remove(integer);
		}
	}
}
