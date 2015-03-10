package com.os.test_1.view;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.os.test_1.algorithm.Algorithm;
import com.os.test_1.bean.ProcessBean;
import com.os.test_1.list.ProcessList;

/**
 * 程序主界面
 * 
 * @author 312005790
 **/
public class View_Main {

	private JFrame frame;
	private JPanel jPanel;
	private JPanel north;
	private JPanel west;
	private JPanel center;
	private JLabel labelALG;
	private JPanel createProcess;
	private JPanel add;
	private JPanel rd;
	private JPanel operate;
	private JLabel addProcess;
	private JButton random;
	private JLabel operation;
	private JButton run;
	private JButton runAll;
	private JButton runstep;
	private JPanel op;
	private JPanel rn;
	private JPanel rna;
	private JPanel rns;
	private JPanel runWait;
	private JPanel runState;
	private JLabel runLabel;
	private JPanel runStateDisplay;
	private JPanel runStateDisplay1;
	private JPanel finishStateDisplay1;
	private JPanel finishStateDisplay2;
	private JPanel finishStateDisplay3;
	private JPanel finishStateDisplay4;
	private JPanel finishStateDisplay5;
	private LinkedList<ProcessBean> originalProcessesList;
	private JLabel label;
	private JPanel finishState;
	private JLabel finishLabel;
	private JPanel finishStateDisplay;
	private JPanel memoryState;
	private JLabel memoryLabel;
	private JPanel memoryStateDisplay;
	
	private JPanel inputwellState;
	private JComponent inputwellLabel;
	private JPanel inputwellStateDisplay;
	private JPanel inputwellStateDisplay1;
	private JPanel inputwellStateDisplay2;
	private JPanel inputwellStateDisplay3;
	private JPanel inputwellStateDisplay4;
	private JPanel inputwellStateDisplay5;
	private JButton preinput;
	private JPanel pp;
	private JLabel tape;
	private JLabel capacity;
	private JLabel timelabel;
	private JPanel memoryStateDisplay1;
	private JPanel memoryStateDisplay2;
	private JPanel memoryStateDisplay3;
	private JPanel memoryStateDisplay4;
	private JPanel memoryStateDisplay5;
	//private int time=0;
	static JProgressBar jpb = null;
	private HashMap<String, LinkedList<ProcessBean>> processState;
	private LinkedList<ProcessBean> runpro;
	

	/**
	 * 主界面中的North布局设置
	 * 
	 * @return void
	 */
	private void northViewInit() {

		north = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 12));
		jPanel.add(north, BorderLayout.SOUTH);

		labelALG = new JLabel(" 进程完成进度：");
		//labelALG.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		//labelALG.setForeground(Color.WHITE);
		labelALG.setOpaque(true);
		//labelALG.setBackground(new Color(47, 105, 60));

		//north.add(labelALG);
		jpb = new JProgressBar();
		jpb.setOrientation(JProgressBar.HORIZONTAL);
		jpb.setMaximum(100);
		jpb.setMinimum(0);
		jpb.setValue(0);
		jpb.setStringPainted(true);
		jpb.setPreferredSize(new Dimension(200, 20));
		//jpb.setBackground(Color.RED);
		
		label = new JLabel(" 多道批处理系统的两级调度-3");
		label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		label.setOpaque(true);

		capacity = new JLabel(" 100 KB");
		tape = new JLabel("4 台      ");
		timelabel=new JLabel("目前时间为："+int2Time(ProcessList.getTime()));
		north.add(labelALG);
		north.add(jpb);
		//north.add(label);
		north.add(new JLabel("            "));
		north.add(new JLabel("当前内存容量： "));
		north.add(capacity);
		north.add(new JLabel("            "));
		north.add(new JLabel("当前磁带机数量： "));
		north.add(tape);
		north.add(timelabel);
	}

	/**
	 * 主界面中的Center布局设置 主要布局方式：GridLayout
	 * 
	 * @return void
	 */
	private void centerViewInit() {
		center = new JPanel(new GridLayout(2, 1, 0, 10));

		runWait = new JPanel(new GridLayout(1, 1, 20, 20));

		memoryState = new JPanel(new GridLayout(8, 1));
		memoryState.setOpaque(true);
		memoryState.setBackground(Color.WHITE);
		memoryLabel = new JLabel("    内存就绪态");
		memoryLabel.setOpaque(true);
		//memoryLabel.setBackground(new Color(47, 105, 60));
		memoryLabel.setBackground(new Color(0, 0, 0));
		memoryLabel.setForeground(Color.white);
		memoryStateDisplay = new JPanel(new GridLayout(1, 5, 0, 0));
		memoryStateDisplay.add(new JLabel("进程名"));
		memoryStateDisplay.add(new JLabel("到达时间"));
		memoryStateDisplay.add(new JLabel("运行时间"));
		memoryStateDisplay.add(new JLabel("进内存时间"));
		memoryStateDisplay.add(new JLabel("优先级"));
		memoryState.add(memoryLabel);
		memoryState.add(memoryStateDisplay);
		memoryStateDisplay1 = createSpecialPanel(memoryState, 5);
		memoryStateDisplay2 = createSpecialPanel(memoryState, 5);
		memoryStateDisplay3 = createSpecialPanel(memoryState, 5);
		memoryStateDisplay4 = createSpecialPanel(memoryState, 5);
		memoryStateDisplay5 = createSpecialPanel(memoryState, 5);
		runWait.add(memoryState);
		center.add(runWait);

		inputwellState = new JPanel(new GridLayout(8, 1, 0, 0));
		inputwellState.setOpaque(true);
		inputwellState.setBackground(Color.WHITE);
		inputwellLabel = new JLabel("     输入井");
		inputwellLabel.setOpaque(true);
		inputwellLabel.setBackground(new Color(0, 0, 0));
		inputwellLabel.setForeground(Color.white);
		inputwellStateDisplay = new JPanel(new GridLayout(1, 6, 0, 0));
		inputwellStateDisplay.add(new JLabel("进程名"));
		inputwellStateDisplay.add(new JLabel("到达时间"));
		inputwellStateDisplay.add(new JLabel("运行时间"));
		inputwellStateDisplay.add(new JLabel("内存需要"));
		inputwellStateDisplay.add(new JLabel("磁带机"));
		inputwellStateDisplay.add(new JLabel("优先级"));
		inputwellState.add(inputwellLabel);
		inputwellState.add(inputwellStateDisplay);

		inputwellStateDisplay1 = createSpecialPanel(inputwellState, 5);
		inputwellStateDisplay2 = createSpecialPanel(inputwellState, 5);
		inputwellStateDisplay3 = createSpecialPanel(inputwellState, 5);
		inputwellStateDisplay4 = createSpecialPanel(inputwellState, 5);
		inputwellStateDisplay5 = createSpecialPanel(inputwellState, 5);

		runWait.add(inputwellState);
		center.add(runWait);

		runWait = new JPanel(new GridLayout(1, 1, 20, 0));

		runState = new JPanel(new GridLayout(8, 1));
		runState.setOpaque(true);
		runState.setBackground(Color.WHITE);
		runLabel = new JLabel("    运行态");
		runLabel.setOpaque(true);
		//runLabel.setBackground(new Color(47, 105, 60));
		runLabel.setBackground(new Color(0, 0, 0));
		runLabel.setForeground(Color.white);
		runStateDisplay = new JPanel(new GridLayout(1, 5, 0, 0));
		runStateDisplay.add(new JLabel("进程名"));
		runStateDisplay.add(new JLabel("到达时间"));
		runStateDisplay.add(new JLabel("运行时间"));
		runStateDisplay.add(new JLabel("进内存时间"));
		runStateDisplay.add(new JLabel("进CPU时间"));
		runState.add(runLabel);
		runState.add(runStateDisplay);
		runStateDisplay1 = createSpecialPanel(runState, 5);
		runWait.add(runState);
		center.add(runWait);

		finishState = new JPanel(new GridLayout(8, 1, 0, 0));
		finishState.setOpaque(true);
		finishState.setBackground(Color.WHITE);
		finishLabel = new JLabel("     完成态");
		finishLabel.setOpaque(true);
		//finishLabel.setBackground(new Color(47, 105, 60));
		finishLabel.setBackground(new Color(0, 0, 0));
		finishLabel.setForeground(Color.white);
		finishStateDisplay = new JPanel(new GridLayout(1, 5, 0, 0));
		finishStateDisplay.add(new JLabel("进程名"));
		finishStateDisplay.add(new JLabel("到达时间"));
		finishStateDisplay.add(new JLabel("运行时间"));
		finishStateDisplay.add(new JLabel("完成时间"));
		finishStateDisplay.add(new JLabel("带权周转时间"));
		finishState.add(finishLabel);
		finishState.add(finishStateDisplay);

		finishStateDisplay1 = createSpecialPanel(finishState, 4);
		finishStateDisplay2 = createSpecialPanel(finishState, 4);
		finishStateDisplay3 = createSpecialPanel(finishState, 4);
		finishStateDisplay4 = createSpecialPanel(finishState, 4);
		finishStateDisplay5 = createSpecialPanel(finishState, 4);

		runWait.add(finishState);
		center.add(runWait);

		jPanel.add(center);
	}

	/**
	 * 主界面中的West布局设置 主要布局方式：GridLayout
	 * 
	 * @return void
	 */
	private void westViewInit() {
		west = new JPanel(new GridLayout(2, 1));
		jPanel.add(west, BorderLayout.EAST);

		createProcess = new JPanel(new GridLayout(3, 1, 20, 20));
		createProcess.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		operate = new JPanel(new BorderLayout());
		west.add(createProcess);
		addProcess = new JLabel("  增加进程              ");
		addProcess.setForeground(Color.white);
		addProcess.setOpaque(true);
		//addProcess.setBackground(new Color(47, 105, 60));
		addProcess.setBackground(new Color(0, 0, 0));
		addProcess.setPreferredSize(new Dimension(100, 30));

		preinput = new JButton("预先输入");

		random = new JButton("随机输入");
		add = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		pp = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		rd = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		pp.add(preinput);
		add.add(addProcess);
		rd.add(random);
		createProcess.add(add);
		createProcess.add(pp);
		createProcess.add(rd);

		operate = new JPanel(new GridLayout(4, 1));
		operate.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		operation = new JLabel("  操作执行              ");
		operation.setForeground(Color.WHITE);
		operation.setOpaque(true);
		//operation.setBackground(new Color(47, 105, 60));
		operation.setBackground(new Color(0, 0, 0));
		operation.setPreferredSize(new Dimension(100, 30));
		run = new JButton("自动运行");
		runAll = new JButton("一键运行");
		runstep=new JButton("单步运行");
		op = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		rn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		rna = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		rns = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

		op.add(operation);
		rn.add(run);
		rna.add(runAll);
		rns.add(runstep);

		operate.add(op);
		operate.add(rn);
		operate.add(rna);
		operate.add(rns);

		west.add(operate);

		// 为“预处理"按钮添加事件监听器
		preinput.addActionListener(new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent e) {
				//time=-50;
				ProcessList.setTime(0);
				originalProcessesList = ProcessList.getInstance()
						.getOriginalProcessesList();
				originalProcessesList.clear();
				originalProcessesList
						.add(new ProcessBean(" JOB1", 0, 25, 15, 2,3));
				originalProcessesList.add(new ProcessBean(" JOB2", 20, 30, 60,
						1,1));
				originalProcessesList.add(new ProcessBean(" JOB3", 30, 10, 50,
						3,4));
				originalProcessesList.add(new ProcessBean(" JOB4", 35, 20, 10,
						2,2));
				originalProcessesList.add(new ProcessBean(" JOB5", 40, 15, 30,
						2,5));

				ProcessList.getInstance().setOriginalProcessesList(
						originalProcessesList);
				display();
				run.setEnabled(true);
				runAll.setEnabled(true);
				runstep.setEnabled(true);
			}
		});

		// 为“自动增加"按钮添加事件监听器
		random.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProcessList.setTime(0);
				originalProcessesList = ProcessList.getInstance()
						.getOriginalProcessesList();
				originalProcessesList.clear();
				for (int count = 0; count < 5; count++) {
					originalProcessesList.add(new ProcessBean("  JOB" + count,
							(int) ((Math.random() * 10) % 6) * 2, (int) (Math
									.random() * 100),
							(int) (Math.random() * 100) / 5 + 10, (int) ((Math
									.random() * 10) % 4) + 1,(int) ((Math.random() * 10) % 5) + 1));
				}
				ProcessList.getInstance().setOriginalProcessesList(
						originalProcessesList);
				display();
				run.setEnabled(true);
				runAll.setEnabled(true);
				runstep.setEnabled(true);
			}
		});
		// 为“运行"按钮添加事件监听器
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*Algorithm algorithm = Algorithm.getInstance();
				// algorithm.nextWell2Memory();
				if (algorithm.nextFromMemory()) {
					display();
				}else{
					run.setEnabled(false);
					runAll.setEnabled(false);
				}*/
				jpb.setForeground(Color.RED);
				runAll.setEnabled(false);
				runstep.setEnabled(false);
				Timer timer = new Timer();
				timer.schedule(new mytask(jpb), 100, 1500);

			}
		});
		// 为“一键运行"按钮添加事件监听器
		runAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jpb.setForeground(Color.RED);
				Algorithm algorithm = Algorithm.getInstance();
				while (algorithm.nextFromMemory()) {
					display();
				}
				run.setEnabled(false);
				runAll.setEnabled(false);
				runstep.setEnabled(false);
			}
		});
		runstep.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				jpb.setForeground(Color.RED);
				run.setEnabled(false);
				runAll.setEnabled(false);
				Algorithm algorithm = Algorithm.getInstance();
				// algorithm.nextWell2Memory();
				if (algorithm.nextFromMemory()) {
					display();
				}else{
					run.setEnabled(false);
					runAll.setEnabled(false);
					runstep.setEnabled(false);
				}
				int i = 0;
				processState = ProcessList.getInstance().getAllStateProcessList();
			    runpro=processState.get("run");
			    if (!runpro.isEmpty()) {
			    	ProcessBean process = runpro.get(0);
			    	i=(int)((double)process.getServiceTime()/process.getOrigintime()*100);
			    	System.out.println("i="+i);
			    	System.out.println("ServiceTime="+process.getServiceTime());
			    	System.out.println("origintime="+process.getOrigintime());
			    	jpb.setValue(100-i);
			    }
			}
			
			
		});
		
	}

	/**
	 * 主界面布局设置 布局方式：采用BorderLayout
	 * 
	 * @return void
	 */
	private void init() {
		frame = new JFrame("计算机操作系统课程设计");
		// frame.setSize(850, 650);
		frame.setLocation(300, 30);
		frame.setBackground(Color.BLUE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jPanel = new JPanel(new BorderLayout(20, 20));
		//jPanel.setBackground(Color.RED);
		frame.add(jPanel);

		northViewInit();
		westViewInit();
		centerViewInit();
		jPanel.add(new JPanel(), BorderLayout.NORTH);
		jPanel.add(new JPanel(), BorderLayout.WEST);
		frame.pack();
		frame.setVisible(true);
	}

	// 移除传进来的JPanel上的所有组件
	private void removeAll(JPanel panel) {
		panel.removeAll();
		panel.repaint();
		panel.validate();
	}

	private void initStateView() {
		removeAll(inputwellStateDisplay1);
		removeAll(inputwellStateDisplay2);
		removeAll(inputwellStateDisplay3);
		removeAll(inputwellStateDisplay4);
		removeAll(inputwellStateDisplay5);

		removeAll(memoryStateDisplay1);
		removeAll(memoryStateDisplay2);
		removeAll(memoryStateDisplay3);
		removeAll(memoryStateDisplay4);
		removeAll(memoryStateDisplay5);

		removeAll(runStateDisplay1);

		removeAll(finishStateDisplay1);
		removeAll(finishStateDisplay2);
		removeAll(finishStateDisplay3);
		removeAll(finishStateDisplay4);
		removeAll(finishStateDisplay5);

	}

	// 改变主界面的所有进程的所有状态
	public void display() {
		initStateView();
		ProcessList list = ProcessList.getInstance();
		LinkedList<ProcessBean> well = list.getAllStateProcessList()
				.get("well");
		LinkedList<ProcessBean> memory = list.getAllStateProcessList().get(
				"memory");
		LinkedList<ProcessBean> run = list.getAllStateProcessList().get("run");
		LinkedList<ProcessBean> finish = list.getAllStateProcessList().get(
				"finish");
		capacity.setText(ProcessList.getCapacity() + "");
		tape.setText(ProcessList.getTage() + "");
		timelabel.setText("        目前时间为："+int2Time(ProcessList.getTime()));
		if (!well.isEmpty()) {
			for (int i = 0; i < well.size(); i++) {
				switch (i) {
				case 0:
					addWellProcess(inputwellStateDisplay1, well.get(i));
					break;
				case 1:
					addWellProcess(inputwellStateDisplay2, well.get(i));
					break;
				case 2:
					addWellProcess(inputwellStateDisplay3, well.get(i));
					break;
				case 3:
					addWellProcess(inputwellStateDisplay4, well.get(i));
					break;
				case 4:
					addWellProcess(inputwellStateDisplay5, well.get(i));
					break;

				}

			}
		}
		if (!memory.isEmpty()) {
			for (int i = 0; i < memory.size(); i++) {
				switch (i) {
				case 0:
					addMemoryProcess(memoryStateDisplay1, memory.get(i));
					break;
				case 1:
					addMemoryProcess(memoryStateDisplay2, memory.get(i));
					break;
				case 2:
					addMemoryProcess(memoryStateDisplay3, memory.get(i));
					break;
				case 3:
					addMemoryProcess(memoryStateDisplay4, memory.get(i));
					break;
				case 4:
					addMemoryProcess(memoryStateDisplay5, memory.get(i));
					break;
				}

			}
		}
		if (!run.isEmpty()) {
			addRunProcess(runStateDisplay1, run.get(0));
		}
		if (!finish.isEmpty()) {
			for (int i = 0; i < finish.size(); i++) {
				switch (i) {
				case 0:
					addFinishProcess(finishStateDisplay1, finish.get(i));
					break;
				case 1:
					addFinishProcess(finishStateDisplay2, finish.get(i));
					break;
				case 2:
					addFinishProcess(finishStateDisplay3, finish.get(i));
					break;
				case 3:
					addFinishProcess(finishStateDisplay4, finish.get(i));
					break;
				case 4:
					addFinishProcess(finishStateDisplay5, finish.get(i));
					break;
				}
			}
		}

	}

	// 在就绪态的面板上增加进程
	private void addWellProcess(JPanel panel, ProcessBean bean) {
		panel.removeAll();
		panel.add(new JLabel(bean.getName()));
		panel.add(new JLabel(int2Time(bean.getInWellTime())));
		panel.add(new JLabel(bean.getServiceTime() + ""));
		panel.add(new JLabel(bean.getCapacity() + ""));
		panel.add(new JLabel(bean.getWage() + ""));
		panel.add(new JLabel(bean.getProsuper()+""));
		panel.validate();
	}

	// 在内存的面板上增加进程
	private void addMemoryProcess(JPanel panel, ProcessBean bean) {
		panel.removeAll();
		panel.add(new JLabel(bean.getName()));
		panel.add(new JLabel(int2Time(bean.getInWellTime())));
		panel.add(new JLabel(bean.getServiceTime() + ""));
		panel.add(new JLabel(int2Time(bean.getInMemoryTime())));
		panel.add(new JLabel(bean.getProsuper()+""));

		panel.validate();
	}

	// 在运行态的面板上增加进程
	private void addRunProcess(JPanel panel, ProcessBean bean) {
		panel.removeAll();
		panel.add(new JLabel(bean.getName()));
		panel.add(new JLabel(int2Time(bean.getInWellTime())));
		panel.add(new JLabel(bean.getServiceTime() + ""));
		panel.add(new JLabel(int2Time(bean.getInMemoryTime())));
		panel.add(new JLabel(int2Time(bean.getInCPUTime())));
		panel.validate();
	}

	// 在就完成的面板上增加进程
	private void addFinishProcess(JPanel panel, ProcessBean bean) {
		panel.removeAll();
		panel.add(new JLabel(bean.getName()));
		panel.add(new JLabel(int2Time(bean.getInWellTime())));
		panel.add(new JLabel(bean.getServiceTime() + ""));
		panel.add(new JLabel(int2Time(bean.getFinishTime())));
		panel.add(new JLabel(bean.getWeightedTurnTime() + ""));
		panel.validate();
	}

	private String int2Time(int time) {
		String str = "";
		if (time % 60 < 10) {
			if(time%60==0) str = "" + (10 + time / 60) + ":" + time % 60 + 0;
			else           str=""+(10 + time / 60) + ":"+0+time % 60;
		} else {
			str = "" + (10 + time / 60) + ":" + time % 60;
		}
		return str;

	}

	// 创造特定的JPanel
	private JPanel createSpecialPanel(JPanel state, int cols) {
		JPanel panel = new JPanel(new GridLayout(1, cols));
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		state.add(panel);
		return panel;
	}

	public static void main(String[] args) {
		new View_Main().init();
	}

	class mytask extends TimerTask
	{
		JProgressBar jpb = null;
		int i = 0;

		public mytask(JProgressBar jp)
		{
			this.jpb = jp;
		}

		public void run()
		{
			Algorithm algorithm = Algorithm.getInstance();
			// algorithm.nextWell2Memory();
			if (algorithm.nextFromMemory()) {
				display();
			}else{
				run.setEnabled(false);
				runAll.setEnabled(false);
				runstep.setEnabled(false);
			}
			//this.jpb.setValue(i++);
			processState = ProcessList.getInstance().getAllStateProcessList();
		    runpro=processState.get("run");
		    if (!runpro.isEmpty()) {
		    	ProcessBean process = runpro.get(0);
		    	i=(int)((double)process.getServiceTime()/process.getOrigintime()*100);
		    	System.out.println("i="+i);
		    	System.out.println("ServiceTime="+process.getServiceTime());
		    	System.out.println("origintime="+process.getOrigintime());
		    	this.jpb.setValue(100-i);
		    }
		}
	}
}



