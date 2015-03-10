package com.os.test_1.list;

import java.util.HashMap;
import java.util.LinkedList;
import com.os.test_1.bean.ProcessBean;

/**
 * 存储所有进程的状态和各种信息的集合
 * 
 * @author 3112005790
 **/
public class ProcessList {

	private HashMap<String, LinkedList<ProcessBean>> allStateProcessList;
	private LinkedList<ProcessBean> originalProcessesList;
	private static ProcessList processList;
	private static int time=0;//系统时间
	private static int capacity = 100;
	private static int tage = 4;

	public static int getCapacity() {
		return capacity;
	}

	public static void setCapacity(int capacity) {
		ProcessList.capacity = capacity;
	}

	public static int getTage() {
		return tage;
	}

	public static void setTage(int tage) {
		ProcessList.tage = tage;
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		ProcessList.time = time;
	}

	private ProcessList() {
		originalProcessesList = new LinkedList<ProcessBean>();
		allStateProcessList = new HashMap<String, LinkedList<ProcessBean>>();
		allStateProcessList.put("memory", new LinkedList<ProcessBean>());
		allStateProcessList.put("well", new LinkedList<ProcessBean>());
		allStateProcessList.put("run", new LinkedList<ProcessBean>());
		allStateProcessList.put("finish", new LinkedList<ProcessBean>());
	}

	// 采用懒构造的方式创造唯一一个ProcessList的实例
	public static ProcessList getInstance() {
		if (processList == null) {
			createProcessList();
		}
		return processList;
	}

	private synchronized static ProcessList createProcessList() {
		if (processList == null) {
			processList = new ProcessList();
		}
		return processList;
	}

	public HashMap<String, LinkedList<ProcessBean>> getAllStateProcessList() {
		return allStateProcessList;
	}

	public void setAllStateProcessList(
			HashMap<String, LinkedList<ProcessBean>> list) {
		this.allStateProcessList = list;
	}

	public LinkedList<ProcessBean> getOriginalProcessesList() {
		return originalProcessesList;
	}

	public void setOriginalProcessesList(LinkedList<ProcessBean> list) {
		this.originalProcessesList = list;
		allStateProcessList.get("well").clear();
		allStateProcessList.get("memory").clear();
		allStateProcessList.get("run").clear();
		allStateProcessList.get("finish").clear();
		for (ProcessBean bean : list) {
			allStateProcessList.get("well").add(
					new ProcessBean(bean.getName(), bean.getInWellTime(), bean
							.getServiceTime(), bean.getCapacity(),bean.getWage(),bean.getProsuper()));
		}
	}
}
