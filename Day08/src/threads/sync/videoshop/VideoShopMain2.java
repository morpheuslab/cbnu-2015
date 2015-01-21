package threads.sync.videoshop;

import java.util.*;

class VideoShop {
	private ArrayList<String> buffer = new ArrayList<String>();

	public VideoShop() {
		buffer.add("은하철도999-0");
		buffer.add("은하철도999-1");
//		buffer.add("은하철도999-2");
//		buffer.add("은하철도999-3");
	}

	public synchronized String lendVideo() throws InterruptedException {
		Thread t = Thread.currentThread();
		while (buffer.size() == 0) {
			System.out.println(t.getName() + ": 대기상태 진입");
			this.wait();
			System.out.println(t.getName() + ": 대기상태 해제");
		}
		String v = (String) this.buffer.remove(buffer.size() - 1);
		return v;
	}

	public synchronized void returnVideo(String video) {
		this.buffer.add(video);
		this.notifyAll();
	}
} // end of VideoShop class

class Person extends Thread {
	public void run() {
		try {
			String v = VideoShopMain2.vShop.lendVideo();
			System.out.println(this.getName() + ":" + v + " 대여");
			System.out.println(this.getName() + ":" + v + " 보는중");
			Thread.sleep(1000);
			System.out.println(this.getName() + ":" + v + " 반납");
			VideoShopMain2.vShop.returnVideo(v);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
} // end of Person class

public class VideoShopMain2 {
	public static VideoShop vShop = new VideoShop();

	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		Person p4 = new Person();
		Person p5 = new Person();
		Person p6 = new Person();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		p6.start();
		
		System.out.println("프로그램 종료");
	}
}
