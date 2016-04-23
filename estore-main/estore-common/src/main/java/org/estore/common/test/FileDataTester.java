package org.estore.common.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件案例测试包装类,该类用于逐行读取指定文件中的数据,
 * 将其交于给定的FileTestTask,执行FileTestTask.runTestTask(String)
 * @author iJIAJIA
 *
 */
public class FileDataTester {
	
	private static Logger logger = LoggerFactory.getLogger(FileDataTester.class);
	
	private FileTestTask task;
	
	private String filePath;
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FileTestTask getTask() {
		return task;
	}

	public void setTask(FileTestTask task) {
		this.task = task;
	}
	
	public void runTest(){
		File file = new File(filePath);
		BufferedReader reader = null;
		try {
			if(task == null){
				return ;
			}
			reader = new BufferedReader(
					new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null){
				task.runTestTask(line);
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}
}
