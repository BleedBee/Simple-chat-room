package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	
	public static void main(String[] agrs) {
		Scanner in=new Scanner(System.in);
		
		System.out.print("�����������IP��");
		String serverIP;
		serverIP=in.next();
		System.out.println("------�ͻ���------");
		System.out.println("�ͻ�������");
		System.out.print("�������������ǳƣ�");
		String userName=in.next();
		try {
			Socket client=new Socket(serverIP,8888);
			DataOutputStream dos=new DataOutputStream(client.getOutputStream());
			DataInputStream dis=new DataInputStream(client.getInputStream());
			dos.writeUTF(userName);
			dos.flush();
			ClientInterface clientInterface=new ClientInterface(userName,dos,dis);
			String userName_server;
			String userTxt_server;
			while(true) {
				userName_server=dis.readUTF();
				userTxt_server=dis.readUTF();
				clientInterface.showTxt(userName_server, userTxt_server);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
