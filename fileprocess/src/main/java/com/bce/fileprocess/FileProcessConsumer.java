package com.bce.fileprocess;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class FileProcessConsumer extends Thread {
	private final BlockingQueue<String> queue;

	FileProcessConsumer(BlockingQueue<String> q) {
		this.queue = q;
	}

	int sleep[] = { 1000, 5000, 4000, 2000, 3000, 6000, 6500, 1000, 2000, 1500,2000 };

	public void run() {
		while (true) {
			try {
				String result = queue.take();
				if (result.equals("exit")) {
					queue.put("exit");
					break;
				}
				System.out.println(Thread.currentThread().getName() + "--" + result);
				//threadSleep();
				Constant.inc();
			} catch (InterruptedException ex) {
				System.out.println(ex);
			}
		}

	}

	private void threadSleep() throws InterruptedException {
		Random random = new Random();
		int rand = 0;
		rand = random.nextInt(11);
		System.out.println(rand);
		Thread.sleep(sleep[rand]);
	}
}
