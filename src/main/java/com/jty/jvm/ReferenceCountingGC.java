package com.jty.jvm;

/**
 * testGC()方法执行后，objA和objB会不会被GC呢？
 * -XX:+PrintGCDetails
 * -Xloggc:../gc.log
 *
 * Java并没有使用计数法来判断对象是否已死 因为计数法没法解决循环引用的情况
 *
 */
public class ReferenceCountingGC {

	public Object instance = null;

	private static final int _1MB = 1024 * 1024;

	/**
	 * 这个成员属性的唯一意义就是占点内存，以便在能在GC日志中看清楚是否有回收过
	 */
	private byte[] bigSize = new byte[2 * _1MB];

	public static void testGC() {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;

		objA = null;
		objB = null;

		// 假设在这行发生GC，objA和objB是否能被回收？
		System.gc();

	}

	public static void main(String[] args) {
		while (true) {

		}

	}
}