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
import javax.swing.JTextField;

public class MultiChatClient implements ActionListener, Runnable {
	
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
	
	public MultiChatClient(String serverIp) {
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
		
		// 윈도우 만들기
		JFrame window = new JFrame("::멀티챗::");
		window.setLayout(new BorderLayout());
		//
		window.add(tab, BorderLayout.NORTH);
		window.add(msgPanel, BorderLayout.SOUTH);
		
		cLayout.show(tab, "login");
		
		window.pack();
		window.setVisible(true);
	}
	
	@Override
	public void run() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton
				|| e.getSource() == logoutButton) {
			if (showingLoginPanel) {
				cLayout.show(tab, "logout");
				showingLoginPanel = false;
			} else {
				cLayout.show(tab, "login");
				showingLoginPanel = true;
			}
		}
	}

	public static void main(String[] args) {
		MultiChatClient client = new MultiChatClient("");
	}

}
