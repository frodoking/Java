package cn.com.frodo.design.pattern.behavior.commond.demo;

import javax.swing.*;
import java.awt.*;

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
