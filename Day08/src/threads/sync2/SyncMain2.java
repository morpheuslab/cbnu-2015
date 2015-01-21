package threads.sync2;

class Bank {
	private int money = 10000;	// 예금 잔액
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void saveMoney(int save) {
		int m = this.getMoney();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
		this.setMoney(m + save);
	}
	public void minusMoney(int minus) {
		int m = this.getMoney();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {}
		this.setMoney(m - minus);
	}
}

class Park extends Thread {
	public void run() {
		synchronized (SyncMain2.myBank) {
			SyncMain2.myBank.saveMoney(3000);
			System.out.println("saveMoney(3000) 결과 잔액: "
					+ SyncMain2.myBank.getMoney());
		}
	}
}

class ParkWife extends Thread {
	public void run() {
		synchronized (SyncMain2.myBank) {
			SyncMain2.myBank.minusMoney(1000);
			System.out.println("minusMoney(1000) 결과 잔액: "
					+ SyncMain2.myBank.getMoney());
		}
	}
}

public class SyncMain2 {
	public static Bank myBank = new Bank();
	public static void main(String[] args) {
		System.out.println("원금: " + myBank.getMoney());
		Park p = new Park();
		ParkWife w = new ParkWife();
		p.start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {}
		w.start();
	}
}















