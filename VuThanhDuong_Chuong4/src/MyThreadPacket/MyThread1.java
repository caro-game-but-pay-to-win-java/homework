package MyThreadPacket;

public class MyThread1 implements Runnable {
	public void run() {
		for (int i = 1; i <= 10; ++i) {
			System.out.println("Id: " + Thread.currentThread().getId() + " \t Name: " + Thread.currentThread().getName()
					+ "\tPriority: " + Thread.currentThread().getPriority() + "\t[v]: " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
