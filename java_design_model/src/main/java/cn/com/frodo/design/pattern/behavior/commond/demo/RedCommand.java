package cn.com.frodo.design.pattern.behavior.commond.demo;

import javax.swing.*;
import java.awt.*;

public class RedCommand extends JButton implements MyCommand {
    private JPanel p;

    public RedCommand(String name, JPanel p) {
        super(name);
        this.p = p;
    }

    @Override
    public void exec() {
        p.setBackground(Color.RED);
    }
}
