package cn.com.frodo.refactor.model.simplify.step_2_strategy_replace_iflogic;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {

    @Override
    public double capital(Loan loan) {
        return loan.outstandingRiskAmount() * duration(loan) * riskFactor(loan) + loan.unusedRiskAmount()
                * duration(loan) * unusedRiskFactor(loan);
    }

}
