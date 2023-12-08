package com.bce.ziputility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipAllDirectory {
	public static void main(String[] args) throws IOException {
		unzipFile("/home/narottam/project/data/zip/index.zip", "/home/narottam/project/data/unzip/");
	}

	public static void unzipFile(String zipFilePath, String outputDir) throws IOException {
		byte[] buffer = new byte[1024];
		System.out.println(zipFilePath);
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry zipEntry = zis.getNextEntry();
		while (zipEntry != null) {
			File newFile = new File(outputDir + File.separator, zipEntry.getName());
			if (zipEntry.isDirectory()) {
				if (!newFile.isDirectory() && !newFile.mkdirs()) {
					throw new IOException("Failed to create directory " + newFile);
				}
			} else {
				File parent = newFile.getParentFile();
				if (!parent.isDirectory() && !parent.mkdirs()) {
					throw new IOException("Failed to create directory " + parent);
				}
				FileOutputStream fos = new FileOutputStream(newFile);
				int len = 0;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}
}
