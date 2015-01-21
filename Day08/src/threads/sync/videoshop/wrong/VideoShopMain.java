/**
잘못된 동기화의 예
VideoShopMain.java
 **/

package threads.sync.videoshop.wrong;

import java.util.*;

class VideoShop {
	private ArrayList<String> buffer = new ArrayList<String>();

	public VideoShop() {
		buffer.add("은하철도999-0");
		buffer.add("은하철도999-1");
		buffer.add("은하철도999-2");
		buffer.add("은하철도999-3");
	}

	public String lendVideo() {
		String v = (String) this.buffer.remove(buffer.size() - 1);
		return v;
	}

	public void returnVideo(String video) {
		this.buffer.add(video);
	}
} // end of VideoShop class

class Person extends Thread {
	public void run() {
		synchronized (VideoShopMain.vShop) {
			// 5초 동안 VideoShopMain.vShop은 락(Lock)에 걸리게 된다.
			try {
				String v = VideoShopMain.vShop.lendVideo();
				System.out.println(this.getName() + ":" + v + " 대여");
				System.out.println(this.getName() + ":" + v + " 보는중");
				Thread.sleep(5000);
				System.out.println(this.getName() + ":" + v + " 반납");
				VideoShopMain.vShop.returnVideo(v);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
} // end of Person class

public class VideoShopMain {
	public static VideoShop vShop = new VideoShop();

	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		Person p4 = new Person();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		System.out.println("프로그램 종료");
	}
}
