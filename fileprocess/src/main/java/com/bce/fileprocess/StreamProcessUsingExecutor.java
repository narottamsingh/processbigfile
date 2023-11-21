package com.bce.fileprocess;

public class StreamProcessUsingExecutor {
	public StreamProcessUsingExecutor(String line) {
		System.out.println(Thread.currentThread().getName() + " " + line);
		Constant.inc();
	}
}
