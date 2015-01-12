package cn.com.frodo.design.pattern.behavior.commond.demo;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ExitCommand extends JButton implements MyCommand {

	public ExitCommand(String name) {
		super(name);
	}

	@Override
	public void exec() {
		System.exit(0);
	}
}
