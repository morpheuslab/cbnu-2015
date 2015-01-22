package com.cbnu.chatting.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiChatClient implements ActionListener, Runnable {
	
	// 채팅 서버 주소
	private String serverIp;
	
	private boolean showingLoginPanel = true;
	
	// 로그인 패널
	private JPanel loginPanel;			// 로그인 패널
	private JLabel label1;			// 대화명 레이블
	private JButton loginButton;		// 로그인 버튼
	private JTextField nickInput;	// 대화명 입력 텍스트 필드
	
	// 로그아웃 패널
	private JPanel logoutPanel;
	private JLabel label2;
	private JButton logoutButton;
	
	// 메시지 패널
	private JPanel msgPanel;
	private JTextField msgInput;
	private JButton exitButton;
	
	// 카드 레이아웃 관련
	private Container tab;
	private CardLayout cLayout;
	
	// 채팅 메시지 출력 TextArea
	private JTextArea msgOut;
	
	// 윈도우
	private JFrame window;
	
	public MultiChatClient(String serverIp) {
		this.serverIp = serverIp;
		
		// 로그인 패널
		loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());	// 레이아웃 설정
		nickInput = new JTextField(15);
		loginButton = new JButton("로그인");
		loginButton.addActionListener(this);	// 로그인 버튼 이벤트 리스너
		label1 = new JLabel("대화명");
		// 로그인 패널에 컴포넌트 배치
		loginPanel.add(label1, BorderLayout.WEST);	// 대화명 레이블 WEST 영역에 배치
		loginPanel.add(nickInput, BorderLayout.CENTER);	// 대화명 입력 텍스트 필드 CENTER 영역에 배치
		loginPanel.add(loginButton, BorderLayout.EAST);	// 로그인 버튼 EAST 영역에 배치
		
		// 로그아웃 패널
		logoutPanel = new JPanel();
		logoutPanel.setLayout(new BorderLayout());
		label2 = new JLabel();
		logoutButton = new JButton("로그아웃");
		logoutButton.addActionListener(this);
		//로그아웃 패널 컴포넌트 배치
		logoutPanel.add(label2, BorderLayout.CENTER);
		logoutPanel.add(logoutButton, BorderLayout.EAST);
		
		// 메시지 패널
		msgPanel = new JPanel();
		msgPanel.setLayout(new BorderLayout());
		msgInput = new JTextField(30);	// 대화 입력 필드
		msgInput.addActionListener(this);
		exitButton = new JButton("종료");
		exitButton.addActionListener(this);
		// 메시지 패널 컴포넌트 배치
		msgPanel.add(msgInput, BorderLayout.CENTER);
		msgPanel.add(exitButton, BorderLayout.EAST);
		
		// 카드 레이아웃
		tab = new JPanel();
		cLayout = new CardLayout();
		tab.setLayout(cLayout);
		tab.add(loginPanel, "login");
		tab.add(logoutPanel, "logout");
		
		// 채팅 메시지 출력 부분
		msgOut = new JTextArea("", 10, 30);
		msgOut.setEditable(false);
		
		// 스크롤 뷰로 msgOut 감싸 줌
		JScrollPane jsp = new JScrollPane(msgOut,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// 윈도우 만들기
		window = new JFrame("::멀티챗::");
		window.setLayout(new BorderLayout());
		//
		window.add(tab, BorderLayout.NORTH);
		window.add(jsp, BorderLayout.CENTER);
		window.add(msgPanel, BorderLayout.SOUTH);
		
		cLayout.show(tab, "login");
		
		window.pack();
		window.setResizable(false);	// 윈도우 크기 변경 불가능하게 설정
		window.setVisible(true);
	}
	
	@Override
	public void run() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if (eventSource == exitButton) {
			System.exit(0);
		}
		else if (eventSource == loginButton) {
			// 서버에 접속
			
			// 로그아웃 패널 보이기
			cLayout.show(tab, "logout");
		}
		else if (eventSource == logoutButton) {
			// 서버에 로그아웃 알림
			
			// 로그인 패널 보이기
			cLayout.show(tab, "login");
		}
		else if (eventSource == msgInput) {
			// 메시지 처리
			
			// 입력 창 비우기
			msgInput.setText("");
		}
	}

	public static void main(String[] args) {
		MultiChatClient client = new MultiChatClient("127.0.0.1");
	}

}


























