package com.os.test_1.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import com.os.test_1.bean.ProcessBean;
import com.os.test_1.list.ProcessList;

/**
 * �����ҵ���ȵ����㷨
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
		well = processState.get("well");//������뾮�е�������ҵ
		memory = processState.get("memory");//����ڴ������н���
		run = processState.get("run");//����������е��ڴ�
		finish = processState.get("finish");//����Ѿ�������е��ڴ�
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

	// ���������ҵ���ȵ����㷨���������״̬��ֵ��
	public boolean nextFromMemory() {
		boolean mark = false;
		int time = ProcessList.getTime();
		if (!well.isEmpty()) {
			LinkedList<ProcessBean> well0 = new LinkedList<ProcessBean>(well);

			for (int i = 0; !well0.isEmpty() && i < well0.size()
					&& ProcessList.getCapacity() > 0; i++) {
				
				ProcessBean memoryProcess = well0.get(i);
                //������뾮����Ҫ����ʱ����̵���ҵ
				for (ProcessBean bean : well0) {
					if (memoryProcess.getServiceTime() > bean.getServiceTime()
							&& bean.getInWellTime() <= time) {
						memoryProcess = bean;
					}
				}
				well0.remove(memoryProcess);//���뾮������ȥ�����ҵ
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
			//����ڴ�������������ȼ���ߵĽ���
			for (ProcessBean bean : memory) {
				if (runProcess.getProsuper() < bean.getProsuper()) {
					runProcess = bean;
				}
			}
			//���û�н������У�����̽���ռ��CPU
			if (run.isEmpty()) {
				runProcess.setInCPUTime(time);
				run.add(runProcess);
			} 
			//�����õ���̽������ȼ����������еĽ��̸ߣ�����ռCPU 
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
			//������ж����������еĽ����Ѿ��������
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
