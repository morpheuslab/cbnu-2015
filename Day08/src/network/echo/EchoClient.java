package network.echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	
	public static void main(String[] args) throws IOException {
		
		Socket sock = new Socket("127.0.0.1", 3000);
		
		OutputStream out = sock.getOutputStream();
		InputStream in = sock.getInputStream();
		
		OutputStreamWriter osw = new OutputStreamWriter(out);
		InputStreamReader isr = new InputStreamReader(in);
		
		BufferedWriter writer = new BufferedWriter(osw);
		BufferedReader reader = new BufferedReader(isr);
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("전송할 문자열 (종료는 \"EXIT\"): ");
			String message = sc.nextLine();
			
			if (message.equals("EXIT")) {
				break;
			}
			
			writer.write(message + "\r");
			writer.flush();
			
			// 서버가 되돌려 주는 메시지 읽기
			String serverMessage = reader.readLine();
			// serverMessage가 null이라면 연결 종료된 경우
			if (serverMessage == null) {
				break;
			}
			System.out.println("서버: " + serverMessage);
		}
		
		writer.close();
		reader.close();
		
		sock.close();
		
		sc.close();
		
	}
	
}








