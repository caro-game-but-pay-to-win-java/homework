package Account;

import java.util.concurrent.ThreadLocalRandom;

public class Account {
	private int id;
	private int balance;
	public Account(int id, int balance) {
		this.id = id;
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}
	
	public int getBalance() {
		return balance;
	}
	
	private Boolean checkBalance(int withdrawAmount) {
		int time = ThreadLocalRandom.current().nextInt(680, 1250);	
		try {			
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		if (withdrawAmount > this.balance)
			return false;
		return true;
	}
	
	public synchronized Boolean withdraw(int amount) {
		if (this.checkBalance(amount)) {
			int time = ThreadLocalRandom.current().nextInt(680, 1250);	
			try {			
				Thread.sleep(time);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			this.balance -= amount;
			return true;
		}
		System.out.println(amount + "is larger than this " + this.balance);
		return false;
	}
	
	public synchronized void deposit(int amount) {
		int time = ThreadLocalRandom.current().nextInt(680, 1250);	
		try {			
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		this.balance += amount;
	}
}
