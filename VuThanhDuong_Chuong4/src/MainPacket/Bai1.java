package MainPacket;

import MyThreadPacket.MyThread1;

public class Bai1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1 = new Thread(new MyThread1());
		Thread thread2 = new Thread(new MyThread1());
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread2.setPriority(Thread.MIN_PRIORITY);
		thread1.start();
		thread2.start();
	}
}
