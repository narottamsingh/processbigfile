package com.bce.fileprocess;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

public class FileReadAndProcessUsingStream {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(100, true);

		String fileName = "/home/narottam/project/tmp/bigfile.txt";
		File file = new File(fileName);
		int numThreads = Runtime.getRuntime().availableProcessors();
		System.out.println(numThreads);
		FileProcessConsumer[] consumer = new FileProcessConsumer[numThreads];
		for (int i = 0; i < consumer.length; i++) {
			consumer[i] = new FileProcessConsumer(queue);
			consumer[i].start();
		}
		long startTime = System.currentTimeMillis();
		try (Stream<String> linesStream = Files.lines(file.toPath())) {
			linesStream.forEach(line -> {
				try {
					queue.put(line);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			queue.put("exit");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) / 1000 + " seconds");
		System.out.println("total count---" + Constant.getCount());

	}
}
