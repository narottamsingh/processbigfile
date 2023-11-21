package com.bce.fileprocess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FileReadAndProcess {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader("/home/narottam/project/tmp/bigfile.txt"))) {
			String line;
			BlockingQueue<String> queue = new ArrayBlockingQueue<>(100,true);
			int numThreads = Runtime.getRuntime().availableProcessors();
			System.out.println(numThreads);
			FileProcessConsumer[] consumer = new FileProcessConsumer[numThreads];
			for (int i = 0; i < consumer.length; i++) {
				consumer[i] = new FileProcessConsumer(queue);
				consumer[i].start();
			}
			long startTime = System.currentTimeMillis();
			while ((line = br.readLine()) != null) {
				queue.put(line);
			}
			queue.put("exit");
			long endTime = System.currentTimeMillis();

			System.out.println("That took " + (endTime - startTime)/1000 + " seconds");
			System.out.println("total count---"+Constant.getCount());
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException | InterruptedException ex) {
			System.out.println(ex);
		}
	}
}
