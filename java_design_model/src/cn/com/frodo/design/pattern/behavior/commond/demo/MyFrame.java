package cn.com.frodo.design.pattern.behavior.commond.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame implements ActionListener {

	private JPanel p;
	private YellowCommand yellowCommand;
	private RedCommand redCommand;
	private ExitCommand exitCommand;

	MyFrame() {
		super("命令模式");
		p = new JPanel();
		this.add(p);
		yellowCommand = new YellowCommand("黄色", p);
		redCommand = new RedCommand("红色", p);
		exitCommand = new ExitCommand("退出");

		p.add(yellowCommand);
		p.add(redCommand);
		p.add(exitCommand);

		yellowCommand.addActionListener(this);
		redCommand.addActionListener(this);
		exitCommand.addActionListener(this);

		this.setSize(400, 300);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		MyCommand cmd = (MyCommand) evt.getSource();
		cmd.exec();
	}

	public static void main(String args[]) {
		new MyFrame();
	}
}
