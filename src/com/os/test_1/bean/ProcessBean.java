package com.os.test_1.bean;

/**
 * ��Ҫ���ַ�ʽ��GridLayout
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
	private int inWellTime = -1;//�������뾮ʱ��
	private int serviceTime = -1;//Ҫ�����ʱ��
	private int origintime=-1;
	private int inMemoryTime = -1;//�����ڴ�ʱ��
	private int inCPUTime = -1;//����CPUʱ��
	private int finishTime = -1;//���н���ʱ��
	private float weightedTurnTime = -1;//���̴�Ȩ��תʱ��
	private int wage = -1;//��������Ŵ�������
	private int capacity = -1;//�����ڴ�              
	private int prosuper=-1;//�������ȼ�

	

}
