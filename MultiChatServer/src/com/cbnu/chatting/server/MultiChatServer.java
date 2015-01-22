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

	private static final int SERVER_PORT = 3000;
	
	// 서버 소켓
	private ServerSocket serverSock;
	// 접속된 채팅 클라이언트 목록
	private ArrayList<ChatThread> clientList
		= new ArrayList<ChatThread>();
	
	// 채팅 서버 시작
	public void start() throws IOException {
		// 서버 소켓 생성
		serverSock = new ServerSocket(SERVER_PORT);
		while (true) {
			// 반복해서 클라이언트 연결 대기
			Socket sock = serverSock.accept();
			
			// 연결 수립되면 채팅 스레드 생성하고
			ChatThread ct = new ChatThread(sock);
			
			// 클라이언트 목록에 추가한 뒤
			synchronized (clientList) {
				clientList.add(ct);
			}
			
			// 채팅 스레드 시작
			ct.start();
		}
	}
	
	// 모든 클라이언트에게 메시지 전송
	public void sendMsgAll(String msg) {
		synchronized (clientList) {
			// 클라이언트를 순회하면서
			Iterator<ChatThread> iter = clientList.iterator();
			while (iter.hasNext()) {
				// 각 클라이언트의 sendMsg(msg) 호출
				iter.next().sendMsg(msg);	// iter.next()가 ChatThread 객체
			}
		}
	}
	
	public static void main(String[] args) {
		MultiChatServer server = new MultiChatServer();
		try {
			server.start();
		} catch (IOException e) {
			System.err.println("### 서버를 시작할 수 없습니다.");
			e.printStackTrace();
		}
	}

	/*** inner-class ChatThread ***/
	class ChatThread extends Thread {
		
		private boolean status = false;
		
		// 이 스레드가 담당할 클라이언트와 연결된 소켓
		private Socket sock;
		
		private BufferedReader in;
		private PrintWriter out;
		
		private String nick;
		
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
				
				System.out.println("### 채팅 스레드 시작: " + getName()
						+ " (" + sock.getInetAddress().getHostAddress() + ")");
			
				while (status) {
					// 메시지 처리
					// 1. in으로부터 메시지를 읽고
					msg = in.readLine();
					// 2. 메시지 유형에 따라 처리
					rmsg = msg.split("/");
					// 2-1. "login" 메시지의 경우
					if (rmsg[1].equals("login")) {
						nick = rmsg[0];
						// 모든 클라이언트에 "xxx님이 로그인했습니다."
						sendMsgAll("system/alert/" + nick + "님이 로그인했습니다.");
					}
					// 2-2. "logout" 메시지의 경우
					else if (rmsg[1].equals("logout")) {
						/*
						// 클라이언트 목록에서 이 스레드(this) 제거
						synchronized (clientList) {
							clientList.remove(this);
						}
						// 모든 클라이언트에 "xxx님이 로그아웃했습니다."
						sendMsgAll("system/alert/" + rmsg[0] + "님이 로그아웃했습니다.");
						*/
						break;
					}
					// 2-3. 일반 메시지의 경우
					else if (rmsg[1].equals("chat")) {
						// 모든 클라이언트에 rmsg[2]를 전송
						sendMsgAll(rmsg[2]);
					}
					// 2-4. 알 수 없는 메시지 유형
					else {
						// 화면에 수신 메시지 포함한 오류 메시지 출력
						System.err.println("알 수 없는 메시지("
								+ sock.getInetAddress().getHostAddress() + "): "
								+ msg);
					}
				}
			} catch (IOException e) {
				System.out.println("### 클라이언트 접속 종료: "
						+ getName() + " ("
						+ sock.getInetAddress().getHostAddress()
						+ ")");
//				e.printStackTrace();
			} finally {
				// 클라이언트 목록에서 이 스레드(this) 제거
				synchronized (clientList) {
					clientList.remove(this);
				}
				// 모든 클라이언트에 "xxx님이 로그아웃했습니다."
				sendMsgAll("system/alert/" + nick + "님이 로그아웃했습니다.");
				
				status = false;
			}
		}
		
		public void sendMsg(String msg) {
			out.println(msg);	// 이 스레드(this)와 연결된
								// 클라이언트에게 메시지 전송
		}
	}
	
	
}
























