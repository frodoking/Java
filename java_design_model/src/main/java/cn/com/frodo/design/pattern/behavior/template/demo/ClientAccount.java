package cn.com.frodo.design.pattern.behavior.template.demo;

public class ClientAccount {
    public static void main(String args[]) {
        Account account = new DemandAccount();
        System.out.println(account.getAccountType() + " -----------  " + account.calculateInterest());
        Account account2 = new FixedAccount();
        System.out.println(account2.getAccountType() + " -----------  " + account2.calculateInterest());
    }
}
