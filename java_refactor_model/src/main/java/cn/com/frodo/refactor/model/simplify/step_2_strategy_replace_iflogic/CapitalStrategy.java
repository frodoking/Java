package cn.com.frodo.refactor.model.simplify.step_2_strategy_replace_iflogic;

import java.util.Date;
import java.util.Iterator;

public abstract class CapitalStrategy {

    private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    private static final long DAYS_PER_YEAR = 365;

    public abstract double capital(Loan loan);

    protected double riskFactor(Loan loan) {
        return RiskFactor.getFactors().forRating(loan.getRiskRating());
    }

    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }

    protected double unusedRiskFactor(Loan loan) {
        return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
    }

    protected double yearsTo(Date endDate, Loan loan) {
        Date beginDate = loan.getToday() == null ? loan.getStart() : loan.getToday();
        return (endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY / DAYS_PER_YEAR;
    }

    protected double weightedAverageDuration(Loan loan) {
        double duration = 0.0;
        double weightedAverage = 0.0;
        double sumOfPayments = 0.0;
        Iterator<Payment> loanPayments = loan.getPayments().iterator();
        while (loanPayments.hasNext()) {
            Payment payment = loanPayments.next();
            sumOfPayments += payment.amount();
            weightedAverage += yearsTo(payment.date(), loan) * payment.amount();
        }

        if (loan.getCommitment() != 0.0)
            duration = weightedAverage / sumOfPayments;

        return duration;
    }

}
