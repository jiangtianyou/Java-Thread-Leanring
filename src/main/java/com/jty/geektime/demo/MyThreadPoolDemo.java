package com.jty.geektime.demo;

import com.jty.common.utils.Tools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 线程池示例
 * 思路：创建poolSize个线程，每个线程while（true） task.run()进行运行，运行的内容来自任务队列
 * 任务队列里的内容，来自用户的提交。
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        MyThreadPoolDemo myThreadPoolDemo = new MyThreadPoolDemo(3);
        myThreadPoolDemo.execute(new Runnable() {
            @Override
            public void run() {
                Tools.sleep(2000);
                System.out.println(Thread.currentThread().getName() + ": 这是一个输出1");
            }
        });


        Tools.sleep(3000);

        myThreadPoolDemo.execute(new Runnable() {
            @Override
            public void run() {
                Tools.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ": 这是一个输出2");
            }
        });
    }



    private BlockingQueue<Runnable> tasks = new LinkedBlockingDeque<>(3); // 最多同时处理三个任务


    public MyThreadPoolDemo(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            // 由于线程里存在while(true)所以thread并不会被释放
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //从task中取出任务运行
                    try {
                        Runnable take = tasks.take();
                        take.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            thread.start();
        }

    }

    public void execute(Runnable task) {
        try {
            tasks.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
