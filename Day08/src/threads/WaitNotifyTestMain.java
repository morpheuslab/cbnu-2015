package threads;

import java.io.IOException;
import java.util.Scanner;

class TestThread extends Thread {
	// 스레드의 종료를 제어하는 플래그
	private boolean flag = false;

	@Override
	public void run() {
		System.out.println(this.getName() + "시작");
		while (!flag) {
			try {
				// 작업
				Thread.sleep(5000);
				System.out.println(this.getName() + "동작중");
			} catch (InterruptedException e) {
			}
		}
		System.out.println(this.getName() + "종료");
	}
}

public class WaitNotifyTestMain {
	public static void main(String[] args) throws Exception {
		TestThread t1 = new TestThread();
		TestThread t2 = new TestThread();
		TestThread t3 = new TestThread();
		
		t1.start();
		t2.start();
		t3.start();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("사용법 [스레드WAIT: W{번호}, NOTIFY: N{번호}]");
		while (true) {
			System.out.println("명령:");
			String cmd = sc.nextLine();
			if (cmd.startsWith("W")) {
				switch (cmd.charAt(1)) {
				case '1':
					try {
						t1.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case '2':
					t2.wait();
					break;
				case '3':
					t3.wait();
					break;
				}
			}
			else if (cmd.startsWith("N")) {
				switch (cmd.charAt(1)) {
				case '1':
					t1.notify();
					break;
				case '2':
					t2.notify();
					break;
				case '3':
					t3.notify();
					break;
				}
			}
		}
	}
}
