package com.bce.fileprocess;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FileReadAndProcessUsingStreamRunnable {

	private static Runnable getRunnable(String run) {
		Runnable task = () -> {
			// System.out.println(Thread.currentThread().getName() + " -- " + run);
			new StreamProcessUsingExecutor(run);
		};
		return task;
	}

	public static void main(String[] args) throws InterruptedException {
		String fileName = "/home/narottam/project/tmp/bigfile1.txt";
		File file = new File(fileName);
		long startTime = System.currentTimeMillis();
		int numThreads = Runtime.getRuntime().availableProcessors();
		ExecutorService pool = Executors.newFixedThreadPool(numThreads);
		try (Stream<String> linesStream = Files.lines(file.toPath())) {
			linesStream.forEach(line -> {
				pool.execute(getRunnable(line));
				// getRunnable(line);
			});
			pool.shutdown();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pool.awaitTermination(12000, TimeUnit.SECONDS);
		
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) / 1000 + " seconds");
		System.out.println("total count---" + Constant.getCount());

	}
}
