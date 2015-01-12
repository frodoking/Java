package cn.com.frodo.refactor.model.generalization.step_1_template_method;

public class CapitalStrategyRevolver extends CapitalStrategy {

	@Override
	public double capital(Loan loan) {
		return super.capital(loan) + unusedCapital(loan);
	}

	@Override
	protected double riskAmountFor(Loan loan) {
		return loan.outstandingRiskAmount();
	}

	private double unusedCapital(Loan loan) {
		return loan.unusedRiskAmount() * duration(loan) * unusedRiskFactor(loan);
	}
}
