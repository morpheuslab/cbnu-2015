package threads;

class Top implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.print(i + "\t");
			if (i % 5 == 4) {
				System.out.println();
			}
		}
	}
	
}

public class ThreadMain {
	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		
		Top t = new Top();
		Thread thd1 = new Thread(t);
		Thread thd2 = new Thread(t);
		
		thd1.start();
		thd2.start();
		
//		for (char c = 'a'; c <= 'z'; c++) {
//			System.out.print(c + "\t");
//		}
		
		System.out.println("프로그램 종료");
	}
}















