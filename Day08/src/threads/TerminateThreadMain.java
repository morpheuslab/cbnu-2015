package threads;

import java.io.IOException;

class TerminateThread extends Thread {
	// 스레드의 종료를 제어하는 플래그
	private boolean flag = false;

	public void run() {
		int count = 0;
		System.out.println(this.getName() + "시작");
		while (!flag) {
			try {
				// 작업
				this.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		System.out.println(this.getName() + "종료");
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}

public class TerminateThreadMain {
	public static void main(String[] args) throws IOException {
		System.out.println("작업시작");
		TerminateThread a = new TerminateThread();
		TerminateThread b = new TerminateThread();
		TerminateThread c = new TerminateThread();
		a.start();
		b.start();
		c.start();
		int i;
		System.out.print("종료할 스레드를 입력하시오! A, B, C, M?\n");
		while (true) {
			i = System.in.read();
			if (i == 'A') {
				a.setFlag(true);
			} else if (i == 'B') {
				b.setFlag(true);
			} else if (i == 'C') {
				c.setFlag(true);
			} else if (i == 'M') {
				a.setFlag(true);
				b.setFlag(true);
				c.setFlag(true);
				System.out.println("main종료");
				break;
			}
		}
	}
}
