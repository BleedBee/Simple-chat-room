package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {

	public static void main(String[] args) {
		
		ServerSocket server;
		ChatServer serverEr=new ChatServer();
		System.out.println("------�����------");
		System.out.println("���������");
		
		try {
			server = new ServerSocket(8888);
			System.out.println("�ȴ��ͻ�������");
			while(true) {
				ChatServer.setClient(server.accept());
				new Thread(serverEr).start();
				Thread.sleep(1000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
