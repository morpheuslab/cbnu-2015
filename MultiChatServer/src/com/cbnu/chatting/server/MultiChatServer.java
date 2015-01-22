package com.cbnu.chatting.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class MultiChatServer {

	// 서버 소켓
	private ServerSocket serverSock;
	// 접속된 채팅 클라이언트 목록
	private ArrayList<ChatThread> clientList
		= new ArrayList<ChatThread>();
	
	// 채팅 서버 시작
	public void start() {
		// 서버 소켓 생성
		
		while (true) {
			// 반복해서 클라이언트 연결 대기
			
			// 연결 수립되면 채팅 스레드 생성하고
			// 클라이언트 목록에 추가한 뒤
			// 채팅 스레드 시작
		}
	}
	
	// 모든 클라이언트에게 메시지 전송
	public void sendMsgAll(String msg) {
		// 클라이언트를 순회하면서
		Iterator<ChatThread> iter = clientList.iterator();
		while (iter.hasNext()) {
			// 각 클라이언트의 sendMsg(msg) 호출
			iter.next().sendMsg(msg);	// iter.next()가 ChatThread 객체
		}
	}
	
	public static void main(String[] args) {
		MultiChatServer server = new MultiChatServer();
		server.start();
	}

	/*** inner-class ChatThread ***/
	class ChatThread extends Thread {
		
		private boolean status = false;
		
		// 이 스레드가 담당할 클라이언트와 연결된 소켓
		private Socket sock;
		
		private BufferedReader in;
		private PrintWriter out;
		
		public ChatThread(Socket sock) {
			this.sock = sock;
		}
		
		@Override
		public void run() {
			try {
				// 입출력 스트림 생성
				in = new BufferedReader(
						new InputStreamReader(sock.getInputStream()));
				out = new PrintWriter(sock.getOutputStream(), true);
			
				status = true;
				
				String msg = null;
				String[] rmsg = null;
				
				System.out.println("### 채팅 스레드 시작: " + getName());
			
				while (status) {
					// 메시지 처리
					// 1. in으로부터 메시지를 읽고
					msg = in.readLine();
					// 2. 메시지 유형에 따라 처리
					rmsg = msg.split("/");
					// 2-1. "login" 메시지의 경우
					if (rmsg[1].equals("login")) {
						// 모든 클라이언트에 "xxx님이 로그인했습니다."
						
					}
					// 2-2. "logout" 메시지의 경우
					else if (rmsg[1].equals("logout")) {
						// 모든 클라이언트에 "xxx님이 로그아웃했습니다."
						
						// 클라이언트 목록에서 이 스레드(this) 제거
						
					}
					// 2-3. 일반 메시지의 경우
					else if (rmsg[1].equals("chat")) {
						// 모든 클라이언트에 rmsg[2]를 전송
						
					}
					// 2-4. 알 수 없는 메시지 유형
					else {
						// 화면에 수신 메시지 포함한 오류 메시지 출력
						
					}
				}
			} catch (IOException e) {
				// 클라이언트 목록에서 이 스레드(this)를 제거
				
				e.printStackTrace();
			}
		}
		
		public void sendMsg(String msg) {
			out.println(msg);	// 이 스레드(this)와 연결된
								// 클라이언트에게 메시지 전송
		}
	}
	
	
}
























