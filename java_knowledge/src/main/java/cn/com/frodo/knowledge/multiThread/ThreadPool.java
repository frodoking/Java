package cn.com.frodo.knowledge.multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool{
	ExecutorService executorService;

	public ThreadPool() {
		executorService = Executors.newFixedThreadPool(3);
	}
	
	
	

}
