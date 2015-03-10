package com.os.test_1.bean;

/**
 * 主要布局方式：GridLayout
 * @return void
 */

public class ProcessBean {
	


	


	public ProcessBean(){}
	
	
	public ProcessBean(String name, int inWellTime, int serviceTime, int wage,
			int capacity,int prosuper) {
		this.name = name;
		this.inWellTime = inWellTime;
		this.serviceTime = serviceTime;
		this.origintime=this.serviceTime;
		this.wage = wage;
		this.capacity = capacity;
		this.prosuper=prosuper;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInWellTime() {
		return inWellTime;
	}
	public void setInWellTime(int inWellTime) {
		this.inWellTime = inWellTime;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	public int getInMemoryTime() {
		return inMemoryTime;
	}
	public void setInMemoryTime(int inMemoryTime) {
		this.inMemoryTime = inMemoryTime;
	}
	public int getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	public float getWeightedTurnTime() {
		return weightedTurnTime;
	}
	public void setWeightedTurnTime(float weightedTurnTime) {
		this.weightedTurnTime = weightedTurnTime;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int wage) {
		this.wage = wage;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getInCPUTime() {
		return inCPUTime;
	}


	public void setInCPUTime(int inCPUTime) {
		this.inCPUTime = inCPUTime;
	}
	
	public int getProsuper() {
		return prosuper;
	}

	public void setProsuper(int prosuper) {
		this.prosuper = prosuper;
	}
	
	public int getOrigintime() {
		return origintime;
	}

	private String name = "";
	private int inWellTime = -1;//到达输入井时间
	private int serviceTime = -1;//要求服务时间
	private int origintime=-1;
	private int inMemoryTime = -1;//到达内存时间
	private int inCPUTime = -1;//到达CPU时间
	private int finishTime = -1;//运行结束时间
	private float weightedTurnTime = -1;//进程带权周转时间
	private int wage = -1;//进程所需磁带机数量
	private int capacity = -1;//所需内存              
	private int prosuper=-1;//进程优先级

	

}
