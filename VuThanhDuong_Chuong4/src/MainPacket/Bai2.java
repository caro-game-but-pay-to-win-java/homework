package MainPacket;

import MyThreadPacket.MyThread2;

public class Bai2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1 = new Thread(new MyThread2(true));
		Thread thread2 = new Thread(new MyThread2(false));
		
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread2.setPriority(Thread.MIN_PRIORITY);
		
		thread1.start();
		try {
			thread1.join();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		thread2.start();
	}
}
