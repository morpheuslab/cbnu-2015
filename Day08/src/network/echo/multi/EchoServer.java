package network.echo.multi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

class EchoWorker extends Thread {
	private Socket sock;
	public EchoWorker(Socket sock) {
		this.sock = sock;
	}
	public void run() {
		String ipAddr = sock.getInetAddress().getHostAddress();
		System.out.println(getName() + ": 스레드 시작. from: " + ipAddr);
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		// 에코서버가 원래 하던 일
		try {
			InputStream in = sock.getInputStream();
			OutputStream out = sock.getOutputStream();
			
			InputStreamReader isr = new InputStreamReader(in);
			OutputStreamWriter osw = new OutputStreamWriter(out);
			
			reader = new BufferedReader(isr);
			writer = new BufferedWriter(osw);
		} catch (IOException e) {
			System.err.println("I/O 예외 발생. 스레드를 종료합니다.");
			try {
				sock.close();
			} catch (IOException e1) {}
			return;
		}
		
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				try {
					writer.write(line + "\n");
					writer.flush();
				} catch (Exception e) {
					System.err.println("write() 또는 flush() I/O 예외 발생");
				}
			}
		} catch (IOException e) {
			System.err.println("readLine() I/O 예외 발생");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {}
			}
			if (sock != null) {
				try {
					sock.close();
				} catch (IOException e) {}
			}
		}
		
		System.out.println(getName() + ": 클라이언트 접속 종료");
	}
}

public class EchoServer {
	
	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(3000);
		} catch (IOException e) {
			System.err.println("서버 소켓 생성 오류");
			System.exit(0);
		}
		
		while (true) {
			Socket sock = null;
			try {
				sock = ss.accept();
			} catch (Exception e) {
				break;
			}
			System.out.println("클라이언트와 연결 수립");
			// sock 객체의 에코를 담당할 새 thread 생성하고 시작
			EchoWorker worker = new EchoWorker(sock);
			worker.start();
		}
		
		try {
			ss.close();
		} catch (IOException e) {}
		
	}
	
}


















