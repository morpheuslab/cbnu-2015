package network.echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(3000);
		} catch (IOException e) {
			System.err.println("서버 소켓 생성 오류");
			System.exit(0);
		}
		
		Socket sock = ss.accept();	// 클라이언트와 연결된 소켓 객체
		
		System.out.println("클라이언트와 연결 수립");
		
		InputStream in = sock.getInputStream();
		OutputStream out = sock.getOutputStream();
		
		InputStreamReader isr = new InputStreamReader(in);
		OutputStreamWriter osw = new OutputStreamWriter(out);
		
		BufferedReader reader = new BufferedReader(isr);
		BufferedWriter writer = new BufferedWriter(osw);
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			writer.write(line + "\n");
			writer.flush();
		}
		
		System.out.println("클라이언트 접속 종료");
		
		reader.close();
		writer.close();
		
		sock.close();
		
		ss.close();
	}
	
}


















