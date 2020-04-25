package cn.com.frodo.design.pattern.behavior.interpreter.demo;

import java.util.HashMap;
import java.util.Map;

public class Variables {
    Map<Variable, Integer> v = new HashMap<Variable, Integer>();

    public void put(Variable variable, int value) {
        v.put(variable, value);
    }

    public int getVariable(Variable variable) {
        return v.get(variable);
    }
}
