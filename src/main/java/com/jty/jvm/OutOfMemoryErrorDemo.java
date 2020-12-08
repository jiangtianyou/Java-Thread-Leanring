package com.jty.jvm;

import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.util.ArrayList;

/**
 * OutOfMemoryError错误演示
 */

@Slf4j
public class OutOfMemoryErrorDemo {

	public static void main(String[] args) {
		OutOfMemoryErrorDemo object = new OutOfMemoryErrorDemo();

		ArrayList<Object> objects = new ArrayList<>();
		while (true) {
			objects.add(object);
		}
	}

}
