package cn.com.frodo.design.pattern.behavior.template.demo;

public class DemandAccount extends Account {

    @Override
    protected String getAccountType() {
        return "活期";
    }

    @Override
    protected double getInterestRate() {
        return 0.005D;
    }

}
