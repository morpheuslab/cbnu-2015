package threads;

import java.awt.Frame;

class RunFrame extends Frame implements Runnable {
	public RunFrame() {
		new Thread(this).start();
	}
	@Override
	public void run() {
		int i = 0;
		System.out.println("스레드 시작!");
		while (i < 20) {
			System.out.print(i + "\t");
			this.setTitle("스레드 동작중" + i++);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		System.out.println("스레드 종료!");
	}
}

public class RunFrameMain {
	public static void main(String[] args) {
		RunFrame r = new RunFrame();
		r.setSize(300, 100);
		r.show();
//		Thread t = new Thread(r);
//		t.start();
	}
}
