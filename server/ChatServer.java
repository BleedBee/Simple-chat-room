package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatServer implements Runnable{
	
	private static String txt="";
	private static String name="";
	private static Socket client=null;
	
	public static void setTxt(String t) {
		txt=t;
	}
	
	public static String getTxt() {
		return txt;
	}
	
	public static void setClient(Socket client) {
		ChatServer.client=client;
	}
	
	public static void setName(String t) {
		name=t;
	}
	
	public static String getName() {
		return txt;
	}
	
	public static Socket getClient() {
		return ChatServer.client;
	}
	
	@Override
	public void run() {
		Socket client=ChatServer.getClient();
		String userName="";
		try {
			DataInputStream dis=new DataInputStream(client.getInputStream());
			DataOutputStream dos=new DataOutputStream(client.getOutputStream());
			userName=dis.readUTF();
			System.out.println(userName+"连接到客户端");
			new Thread(new Read(dis)).start();
			while(true) {
				if(txt.equals("")) {
					Thread.sleep(1);
				}else {
					dos.writeUTF(name);
					dos.writeUTF(txt);
					dos.flush();
					Thread.sleep(5000);
					txt="";
					name="";
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.out.println("连接到客户端失败");
		}
		
	}
}
