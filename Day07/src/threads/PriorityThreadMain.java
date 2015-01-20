package threads;

class PriorityThread extends Thread {
	@Override
	public void run() {
		int i = 0;
		System.out.println(this.getName() +
				" [우선권: " + this.getPriority() + "] 시작\t");
		while (i < 10000) {
			i++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(this.getName() +
				" [우선권: " + this.getPriority() + "] 종료\t");
	}
}

public class PriorityThreadMain {
	public static void main(String[] args) {
		System.out.println("MAIN 메서드 시작");
		for (int i = 1; i <= 10; i++) {
			PriorityThread t = new PriorityThread();
			t.setPriority(i);
			t.start();
		}
		System.out.println("MAIN 메서드 종료");
	}
}
















