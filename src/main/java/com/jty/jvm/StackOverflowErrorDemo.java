package com.jty.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * StackOverflowError错误演示
 *  -Xss108k 设置栈大小
 */

@Slf4j
public class StackOverflowErrorDemo {

	int depth = 0;
	public static void main(String[] args) {
		StackOverflowErrorDemo demo = new StackOverflowErrorDemo();
		demo.test();
	}

	public void test() {
		log.info("depth = {}", ++depth);
		test();
	}
}
