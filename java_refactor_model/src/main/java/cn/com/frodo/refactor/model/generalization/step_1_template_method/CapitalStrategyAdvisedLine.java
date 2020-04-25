package cn.com.frodo.refactor.model.generalization.step_1_template_method;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {

    @Override
    protected double riskAmountFor(Loan loan) {
        return loan.getCommitment() * loan.getUnusedPercentage();
    }

}
