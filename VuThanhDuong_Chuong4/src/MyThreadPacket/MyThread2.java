package MyThreadPacket;

public class MyThread2 implements Runnable {
	private Boolean isEven;
	public MyThread2(Boolean isEven) {
		this.isEven = isEven;
	}
	public void run() {
		int num = 1;
		if (isEven) num = 2;
		for (int i = num; i <= 10; i += 2) {
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
