package com.jty.geektime.demo;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CopyOnWriteArrayListDemo {
	public static void main(String[] args) {


	}


	static void sleep(int t, TimeUnit u) {
		try {
			u.sleep(t);
		} catch (InterruptedException e) {
		}
	}


}
