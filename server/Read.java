package server;

import java.io.DataInputStream;
import java.io.IOException;

public class Read  implements Runnable{

	private DataInputStream dis;
	
	public Read(DataInputStream dis) {
		this.dis=dis;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				ChatServer.setName(dis.readUTF());
				ChatServer.setTxt(dis.readUTF());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
