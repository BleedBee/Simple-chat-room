package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ClientInterface {
	
	private String userName;
	private DataOutputStream dos;
	private DataInputStream dis;
	private JFrame frame;
	private JTextArea showArea,inputArea;
	private JScrollPane show,input;
	private JPanel bottom;
	private JButton send;
	
	private void addAction() {
		ActionListener sendListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputText=inputArea.getText();
				inputArea.setText("");
				try {
					dos.writeUTF(userName);
					dos.writeUTF(inputText);
					dos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		//��Ӽ���
		inputArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER&&e.isControlDown()) {
					send.doClick();
				}
			}
		});
		send.addActionListener(sendListener);
	}
	
	public ClientInterface(String userName,DataOutputStream dos,DataInputStream dis) {
		//�����û���
		this.userName=userName;
		this.dis=dis;
		this.dos=dos;
		//JFrame����
		frame=new JFrame();
		frame.setVisible(true);
		frame.setTitle("���������");
		frame.setSize(600, 450);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�����ı���
		showArea=new JTextArea();
		inputArea=new JTextArea();
		show=new JScrollPane(showArea);
		input=new JScrollPane(inputArea);
		Font textFont=new Font(null, showArea.getFont().getStyle(), 25);
		showArea.setFont(textFont);
		inputArea.setFont(textFont);
		showArea.setLineWrap(true);
		inputArea.setLineWrap(true);
		showArea.setWrapStyleWord(true);
		inputArea.setWrapStyleWord(true);
		show.setPreferredSize(new Dimension(580,250));
		input.setPreferredSize(new Dimension(580,100));
		//����JPanel
		bottom=new JPanel();
		//������ť
		send=new JButton("����");
		send.setPreferredSize(new Dimension(100,45));
		//��װ
		bottom.add(send);
		frame.add(show,BorderLayout.NORTH);
		frame.add(input,BorderLayout.CENTER);
		frame.add(bottom,BorderLayout.SOUTH);
		//����¼�����
		addAction();
		//���ÿɼ�
		frame.setVisible(true);
		inputArea.requestFocusInWindow();
	}
	
	public void showTxt(String userName,String userTxt) {
		showArea.append(userName+":");
		showArea.append(userTxt+"\r\n\n");
	}
}
