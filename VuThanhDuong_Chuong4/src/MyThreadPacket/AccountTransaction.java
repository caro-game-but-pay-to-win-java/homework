package MyThreadPacket;

import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

import Account.Account;
import MainPacket.Bai4;

public class AccountTransaction implements Runnable {
	Account from;
	Account to;
	List<Account> accounts;
	
	public AccountTransaction(int from, int to, List<Account> accounts) {
		this.from = accounts.get(from);
		this.to = accounts.get(to);
		this.accounts = accounts;
	}
	
	public void run() {
		int time = ThreadLocalRandom.current().nextInt(680, 1250);	
		try {			
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			return;
		}
		int num = ThreadLocalRandom.current().nextInt(120, 10000);	
		if (this.from.withdraw(num)) {
			this.to.deposit(num);
			System.out.println("Account: " + from.getId() + " send " + num + " to Account: " + to.getId() + "; Current Balance of from Account: " + from.getId() + " is " + from.getBalance() + "; Current Balance of to Account: " + to.getId() + " is " + to.getBalance());
			Bai4.model.addRow(new Object[] {Thread.currentThread().getName(), num, from.getId(), to.getId(), from.getBalance(), to.getBalance()});
		}
	}
}