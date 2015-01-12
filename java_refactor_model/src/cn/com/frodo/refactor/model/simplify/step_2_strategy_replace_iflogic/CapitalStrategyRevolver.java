package cn.com.frodo.refactor.model.simplify.step_2_strategy_replace_iflogic;

public class CapitalStrategyRevolver extends CapitalStrategy {

	@Override
	public double capital(Loan loan) {
		return loan.getCommitment() * loan.getUnusedPercentage() * duration(loan) * riskFactor(loan);
	}

}
