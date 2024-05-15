package Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import Account.Account;
import MyThreadPacket.AccountTransaction;


public class BankThread implements Runnable {
	public void run() {
		List<Account> accounts = new ArrayList<Account>();
		for (int i = 1; i <= 100; ++i) {
			accounts.add(new Account(i, 100000));
		}
		while (true) {
			int fromIndex = ThreadLocalRandom.current().nextInt(0, accounts.size());
			int toIndex = ThreadLocalRandom.current().nextInt(0, accounts.size());
			while (toIndex == fromIndex) {
				toIndex = ThreadLocalRandom.current().nextInt(0, accounts.size());
			}
			Thread thread = new Thread(new AccountTransaction(fromIndex, toIndex, accounts));
			thread.start();
			int time = ThreadLocalRandom.current().nextInt(400, 850);	
			try {			
				Thread.sleep(time);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				return;
			}
		}
	}
}
