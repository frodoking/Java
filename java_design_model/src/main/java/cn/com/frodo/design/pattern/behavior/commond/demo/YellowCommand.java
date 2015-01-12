package cn.com.frodo.design.pattern.behavior.commond.demo;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class YellowCommand extends JButton implements MyCommand {
	private JPanel p;

	public YellowCommand(String name, JPanel p) {
		super(name);
		this.p = p;
	}

	@Override
	public void exec() {
		p.setBackground(Color.YELLOW);
	}
}
