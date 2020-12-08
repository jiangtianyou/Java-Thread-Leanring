package com.jty.jvm;

import lombok.extern.slf4j.Slf4j;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 常量池太大导致内存溢出
 * 结果：并不会出现常量池太大导致的OOM
 * -Xms10m -Xmx10m  -XX:PermSize=2m -XX:MaxPermSize=2m
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=2m; support was removed in 8.0
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=2m; support was removed in 8.0
 * jdk8之后 已经不存在永久代了，PermSize MaxPermSize参数已经无效。因此常量池也不存在于方法区了，而是存在与堆内存中。
 */

@Slf4j
public class RuntimeConstantPoolOOMDemo {


	public static void main(String[] args) {
		List<Integer> holder = new ArrayList<>();
		int i = 0;
		while (true) {
			++i;
			String str = i + "";
			holder.add(i);
		}

	}
}
