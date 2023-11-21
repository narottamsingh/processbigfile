package com.bce.fileprocess;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class LongFileGeneration {

	public static void main(String[] args) throws InterruptedException {
		try {
			PrintWriter writer = new PrintWriter("/home/narottam/project/tmp/bigfile1.txt", "UTF-8");
			Random random = new Random();
			for (int i = 0; i < 123695502; i++) {
				char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words
																// are boring.)
				for (int j = 0; j < word.length; j++) {
					word[j] = (char) ('a' + random.nextInt(26));
				}
				writer.print(new String(word) + ' ');

				if (i % 10 == 0) {
					writer.println();
				}
				
				//System.out.println(i);
			}

			writer.close();
		} catch (IOException e) {
			// do something
		}

	}

}
