package cn.com.frodo.design.pattern.behavior.commond.demo;

import javax.swing.*;

public class ExitCommand extends JButton implements MyCommand {

    public ExitCommand(String name) {
        super(name);
    }

    @Override
    public void exec() {
        System.exit(0);
    }
}
