package cn.com.frodo.refactor.model.simplify.step_2_strategy_replace_iflogic;

import java.util.Date;
import java.util.List;

/**
 * 通过策略模式或者组合模式达到 -- 简化条件表达式
 *
 * @author frodoking
 */
public class Loan {
    double commitment;
    int riskRating;
    Date maturity;
    double outstanding;
    Date expiry;

    double unusedPercentage;

    private Date start;
    private Date today;
    private List<Payment> payments;
    private CapitalStrategy capitalStrategy;

    private Loan(double commitment, int riskRating, Date maturity, double outstanding, Date expiry, Date start,
                 CapitalStrategy capitalStrategy) {
        this.commitment = commitment;
        this.riskRating = riskRating;
        this.maturity = maturity;
        this.outstanding = outstanding;
        this.expiry = expiry;
        this.start = start;
        this.capitalStrategy = capitalStrategy;
    }

    public static Loan newTermLoan(double commitment, Date start, int riskRating, Date maturity) {
        return new Loan(commitment, riskRating, maturity, 0.0, null, start, new CapitalStrategyTermLoan());
    }

    public static Loan newRevolver(double commitment, Date start, Date expiry, int riskRating) {
        return new Loan(commitment, riskRating, null, 0.0, expiry, start, new CapitalStrategyRevolver());
    }

    public static Loan newAdvisedLine(double commitment, Date start, Date expiry, int riskRating) {
        if (riskRating > 3)
            return null;
        Loan advisedLine = new Loan(commitment, riskRating, null, 0.0, expiry, start, new CapitalStrategyAdvisedLine());

        advisedLine.setUnusedPercentage(1.0);
        return advisedLine;
    }

    public double capital() {
        return capitalStrategy.capital(this);
    }

    public double duration() {
        return capitalStrategy.duration(this);
    }

    double unusedRiskAmount() {
        return commitment - outstanding;
    }

    double outstandingRiskAmount() {
        return outstanding;
    }

    double getUnusedPercentage() {
        return unusedPercentage;
    }

    public void setUnusedPercentage(double unusedPercentage) {
        this.unusedPercentage = unusedPercentage;
    }

    public double getCommitment() {
        return commitment;
    }

    int getRiskRating() {
        return riskRating;
    }

    Date getMaturity() {
        return maturity;
    }

    double getOutstanding() {
        return outstanding;
    }

    Date getExpiry() {
        return expiry;
    }

    Date getStart() {
        return start;
    }

    Date getToday() {
        return today;
    }

    public List<Payment> getPayments() {
        return payments;
    }

}
