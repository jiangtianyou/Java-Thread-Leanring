package com.jty.geektime.demo;


import com.jty.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Transient关键字的使用
 */
@Slf4j
public class TransientDemo implements Serializable {

	private static final long serialVersionUID = -1L; // 为了兼容性。


	public TransientDemo(String name,String password){
		this.name = name;
		this.password = password;
	}
	public String name;
	public transient String password; // 加上transient不会被序列化到文件中

	public static void main(String[] args) throws Exception {
		// 序列化和反序列化
//		TransientDemo object = new TransientDemo("蒋天佑","123");
//		FileOutputStream out = new FileOutputStream(new File("E:\\Desktop\\test.txt"));;
//		ObjectOutputStream outputStream = new ObjectOutputStream(out);
//		outputStream.writeObject(object);


		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("E:\\Desktop\\test.txt"));

		Object o = objectInputStream.readObject();

		log.info("object:{}",JsonUtils.toPrettyJson(o));
	}
}
