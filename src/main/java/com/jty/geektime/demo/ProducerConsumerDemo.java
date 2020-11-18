package com.jty.geektime.demo;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import com.jty.common.utils.Tools;
import lombok.extern.slf4j.Slf4j;

import javax.swing.plaf.synth.SynthColorChooserUI;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 演示生产者消费者问题
 *
 * 生产者往队列中生产数据
 * 消费者从队列中消费数据
 * 当队列满时生产者线程阻塞
 * 当队列空时消费者线程阻塞
 */
@Slf4j
public class ProducerConsumerDemo {
	public static void main(String[] args){
		SynchronousQueue<UUID> queue = new SynchronousQueue<>();
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		producer.start();
		consumer.start();
	}
}


@Slf4j
class Producer extends Thread{
	private final SynchronousQueue<UUID> queue;

	public Producer(SynchronousQueue<UUID> queue){
		this.queue = queue;
	}
	@Override
	public void run() {
		while (true) {
			UUID element = UUID.randomUUID();
			log.info("添加一个元素: {}", element.toString());
			try {
				queue.add(element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
@Slf4j
class Consumer extends Thread{
	private final SynchronousQueue<UUID> queue;

	public Consumer(SynchronousQueue<UUID> queue){
		this.queue = queue;
	}
	@Override
	public void run() {
		while (true) {
			UUID poll = null;
			try {
				poll = queue.element();
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("消费一个元素: {}", poll.toString());
		}
	}
}
