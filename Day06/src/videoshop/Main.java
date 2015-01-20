package videoshop;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	static Map<String, Video> VIDEO_INVEN
		= new HashMap<String, Video>();
	
	static void manageVideo(Scanner sc) {
		String menu = null;
		while (true) {
			// 서브메뉴 출력
			System.out.println("===== 비디오관리 =====");
			System.out.println("  1. 등록");
			System.out.println("  2. 조회");
			System.out.println("  3. 삭제");
			System.out.println("  0. 상위메뉴");
			System.out.println("---------------------");
			System.out.print("메뉴: ");
			menu = sc.nextLine();
			if (menu.equals("1")) {
				System.out.println("===== 비디오관리 > 등록 =====");
				System.out.print("제목: ");
				String title = sc.nextLine();
				
				System.out.print("상영시간(분): ");
				int runningTime = 0;
				String tmp = sc.nextLine();
				try {
					runningTime = Integer.parseInt(tmp);
				} catch (NumberFormatException e1) {
				}
				
				System.out.print("감독: ");
				String director = sc.nextLine();
				
				System.out.print("장르: ");
				String genre = sc.nextLine();
				
				System.out.println("매체타입:");
				System.out.println("  1. VHS    2. DVD    3. BlueRay");
				System.out.print("  선택: ");
				int mediaType = 0;
				tmp = sc.nextLine();
				try {
					mediaType = Integer.parseInt(tmp);
				} catch (NumberFormatException e) {
				}
				
				// Video 객체 생성 및 속성 설정
				Video v = new Video();
				v.setTitle(title);
				v.setRunningTime(runningTime);
				v.setDirector(director);
				v.setGenre(genre);
				v.setMediaType(mediaType);
				
				// VIDEO_INVEN에 저장
				VIDEO_INVEN.put(title, v);
				
				System.out.println("등록되었습니다.");
			}
			else if (menu.equals("2")) {
				// 전체 목록 출력
				System.out.println("===== 비디오관리 > 조회 =====");
				
				Collection<Video> list = VIDEO_INVEN.values();
				Iterator<Video> iter = list.iterator();
				while (iter.hasNext()) {
					Video v = iter.next();
					System.out.println("제목: " + v.getTitle());
					System.out.println("감독: " + v.getDirector());
					System.out.println("타입: "
							+ Video.MediaType.getName(v.getMediaType()));
					System.out.println("------------");
				}
			}
			else if (menu.equals("3")) {
				
			}
			else if (menu.equals("0")) {
				break;
			}
			else {
				System.out.println("잘못 입력. 다시...");
			}
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("=== Video Shop Manager ===");
			System.out.println();
			System.out.println("    1. 대여");
			System.out.println("    2. 반납");
			System.out.println("    3. 회원관리");
			System.out.println("    4. 비디오관리");
			System.out.println("    0. 프로그램 종료");
			System.out.println();
			System.out.println("--------------------------");
			System.out.print("메뉴: ");
			
			String menu = sc.nextLine();
			
			if (menu.equals("1")) {
				// 대여 프로세스 시작
			}
			else if (menu.equals("2")) {
				// 반납 프로세스 시작
			}
			else if (menu.equals("3")) {
				// 회원관리 프로세스 시작
			}
			else if (menu.equals("4")) {
				// 비디오관리 프로세스 시작
				manageVideo(sc);
			}
			else if (menu.equals("0")) {
				// 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				break;	// while문 빠져나가기
			}
			else {
				System.out.println("잘못 선택하셨습니다. 다시...");
			}
		}
		
		sc.close();
	}
	
}























