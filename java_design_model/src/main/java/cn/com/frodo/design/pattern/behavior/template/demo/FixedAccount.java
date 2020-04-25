package cn.com.frodo.design.pattern.behavior.template.demo;

public class FixedAccount extends Account {

    @Override
    protected String getAccountType() {
        return "定期";
    }

    @Override
    protected double getInterestRate() {
        return 0.0325D;
    }

}
