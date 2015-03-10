package com.os.test_1.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import com.os.test_1.bean.ProcessBean;
import com.os.test_1.list.ProcessList;

/**
 * 最短作业优先调度算法
 * 
 * @author 311006556
 **/
public class Algorithm {
	private static Algorithm algorthm;
	private HashMap<String, LinkedList<ProcessBean>> processState;
	private LinkedList<ProcessBean> originalProcess;
	private LinkedList<ProcessBean> well;
	private LinkedList<ProcessBean> memory;
	private LinkedList<ProcessBean> run;
	private LinkedList<ProcessBean> finish;

	private Algorithm() {
		processState = ProcessList.getInstance().getAllStateProcessList();
		originalProcess = ProcessList.getInstance().getOriginalProcessesList();
		well = processState.get("well");//获得输入井中的所有作业
		memory = processState.get("memory");//获得内存中所有进程
		run = processState.get("run");//获得正在运行的内存
		finish = processState.get("finish");//获得已经完成运行的内存
	}

	public static Algorithm getInstance() {
		if (algorthm == null) {
			algorthm = createAlgorthm();
		}
		return algorthm;
	}

	private synchronized static Algorithm createAlgorthm() {
		if (algorthm == null) {
			algorthm = new Algorithm();
		}
		return algorthm;
	}

	// 根据最短作业优先调度算法，求出各个状态的值；
	public boolean nextFromMemory() {
		boolean mark = false;
		int time = ProcessList.getTime();
		if (!well.isEmpty()) {
			LinkedList<ProcessBean> well0 = new LinkedList<ProcessBean>(well);

			for (int i = 0; !well0.isEmpty() && i < well0.size()
					&& ProcessList.getCapacity() > 0; i++) {
				
				ProcessBean memoryProcess = well0.get(i);
                //求出输入井中需要运行时间最短的作业
				for (ProcessBean bean : well0) {
					if (memoryProcess.getServiceTime() > bean.getServiceTime()
							&& bean.getInWellTime() <= time) {
						memoryProcess = bean;
					}
				}
				well0.remove(memoryProcess);//输入井队列移去最短作业
				if (memoryProcess.getCapacity() <= ProcessList.getCapacity()
						&& memoryProcess.getWage() <= ProcessList.getTage()
						&& memoryProcess.getInWellTime() <= time) {
					ProcessList.setCapacity(ProcessList.getCapacity()
							- memoryProcess.getCapacity());
					ProcessList.setTage(ProcessList.getTage()
							- memoryProcess.getWage());
					memoryProcess.setInMemoryTime(time);
					memory.add(memoryProcess);
					well.remove(memoryProcess);
				}
			}
			mark = true;
		}
		if (!memory.isEmpty()) {
			ProcessBean runProcess = memory.get(0);
			//求出内存就绪队列中优先级最高的进程
			for (ProcessBean bean : memory) {
				if (runProcess.getProsuper() < bean.getProsuper()) {
					runProcess = bean;
				}
			}
			//如果没有进程运行，则最短进程占用CPU
			if (run.isEmpty()) {
				runProcess.setInCPUTime(time);
				run.add(runProcess);
			} 
			//如果求得的最短进程优先级比正在运行的进程高，则抢占CPU 
			else if (run.get(0).getProsuper() < runProcess
					.getProsuper()) {
				runProcess.setInCPUTime(time);
				run.addFirst(runProcess);
			}
			mark = true;
		}
		if (!run.isEmpty()) {
			time += 5;
			ProcessBean process = run.get(0);
			//如果运行队列正在运行的进程已经运行完毕
			if (process.getServiceTime() - 5 < 0) {
				run.remove(process);
				for (ProcessBean finishBean : originalProcess) {
					if (finishBean.getName().equals(process.getName())) {
						process.setServiceTime(finishBean.getServiceTime());
					}
				}
				ProcessList.setCapacity(ProcessList.getCapacity()
						+ process.getCapacity());
				ProcessList
						.setTage((ProcessList.getTage() + process.getWage()));
				process.setFinishTime(time);
				process.setWeightedTurnTime((float)(process.getFinishTime()-(float)process.getInWellTime())/(float)process.getServiceTime());
				finish.add(process);
				run.remove(process);
				memory.remove(process);
			} else {
				process.setServiceTime(process.getServiceTime() - 5);
			}
			ProcessList.setTime(time);
			mark = true;
		}
		return mark;
	}
}
