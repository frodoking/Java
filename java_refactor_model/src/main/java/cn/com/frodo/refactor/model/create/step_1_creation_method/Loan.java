package cn.com.frodo.refactor.model.create.step_1_creation_method;

import java.util.Date;

/**
 * 通过创建方法代替多个意图不明确的构造
 * (下一步：如果当前类太多的创建方法使得当前类的主要职责混淆，那么应该考虑使用Factory来剥离创建)
 * 
 * @author frodoking
 * 
 */
public class Loan {
	double commitment;
	int riskRating;
	Date maturity;
	double outstanding;
	Date expiry;
	CapitalStrategy capitalStrategy;

	private Loan(double commitment, int riskRating, Date maturity, double outstanding, Date expiry,
			CapitalStrategy capitalStrategy) {
		this.commitment = commitment;
		this.riskRating = riskRating;
		this.maturity = maturity;
		this.outstanding = outstanding;
		this.expiry = expiry;
		this.capitalStrategy = capitalStrategy;
	}

	// FIXME 原始多个构造 (需要改造) ,多个构造意图不明确
	public Loan(double commitment, int riskRating, Date maturity) {
		this(commitment, riskRating, maturity, 0, null, null);
	}

	public Loan(double commitment, int riskRating, Date maturity, Date expiry) {
		this(commitment, riskRating, maturity, 0, expiry, null);
	}

	public Loan(double commitment, int riskRating, Date maturity, double outstanding, Date expiry) {
		this(commitment, riskRating, maturity, outstanding, expiry, null);
	}

	public Loan(double commitment, int riskRating, Date maturity, Date expiry, CapitalStrategy capitalStrategy) {
		this(commitment, riskRating, maturity, 0, expiry, capitalStrategy);
	}

	// TODO 改造后使用明确方式来构造不同对象
	public static Loan createTermLoan(double commitment, int riskRating, Date maturity) {
		return new Loan(commitment, riskRating, maturity, 0, null, null);
	}

	public static Loan createTermLoan(CapitalStrategy capitalStrategy, double commitment, double outstanding,
			int riskRating, Date maturity) {
		return new Loan(commitment, riskRating, maturity, outstanding, null, capitalStrategy);
	}

	public static Loan createRevoler(double commitment, int riskRating, double outstanding, Date expiry) {
		return new Loan(commitment, riskRating, null, outstanding, expiry, null);
	}

	public static Loan createRevoler(CapitalStrategy capitalStrategy, double commitment, int riskRating,
			double outstanding, Date expiry) {
		return new Loan(commitment, riskRating, null, outstanding, expiry, capitalStrategy);
	}
	
	public static Loan createRCTL(double commitment, int riskRating, Date maturity, double outstanding, Date expiry) {
		return new Loan(commitment, riskRating, maturity, outstanding, expiry, null);
	}

	public static Loan createRCTL(double commitment, int riskRating, Date maturity, double outstanding, Date expiry,
			CapitalStrategy capitalStrategy) {
		return new Loan(commitment, riskRating, maturity, outstanding, expiry, capitalStrategy);
	}
	
	public static class CapitalStrategy {
	}
}
