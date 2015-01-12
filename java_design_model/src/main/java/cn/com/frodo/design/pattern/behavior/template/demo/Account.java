package cn.com.frodo.design.pattern.behavior.template.demo;

public abstract class Account {
	private String accountNumber;

	public Account() {
		accountNumber = null;
	}

	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	protected abstract String getAccountType();

	protected abstract double getInterestRate();

	public double calculateAmount(String accountType, String accountNumber) {
		// 访问数据库
		return 4563.23;
	}

	public double calculateInterest() {
		String accountType = getAccountType();
		double accountRate = getInterestRate();
		double amount = calculateAmount(accountType, accountNumber);
		return amount * accountRate;
	}
}
