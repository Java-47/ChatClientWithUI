package telran.chat.client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import telran.UI.model.ChatClientUI;
import telran.chat.client.task.Receiver;
import telran.chat.client.task.Sender;

public class ChatClientAppl {
	private static final String SERVER_HOST = "127.0.0.1";
	private static final int PORT = 9000;

	private static String userName;

	public ChatClientAppl() {
		//Окно ввода ник нейма
		ChatClientAppl.userName = JOptionPane.showInputDialog(null, "Enter you nick name", "Enter",
				JOptionPane.PLAIN_MESSAGE);
		if (ChatClientAppl.userName == null || ChatClientAppl.userName.isEmpty()) {
			System.exit(0); // если пользователь не ввел имя или отменил ввод, выходим из приложения
		}
	}


	public static void main(String[] args) {
		new ChatClientAppl();
		try (Socket socket = new Socket(SERVER_HOST, PORT);){
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ChatClientUI clientUI = new ChatClientUI(userName);
			//Sender
			Sender sender = new Sender(clientUI, oos);
			Thread senderThread = new Thread(sender);
			senderThread.start();
			//Receiver
			Receiver receiver = new Receiver(clientUI, ois);
			Thread receiverThread = new Thread(receiver);
			receiverThread.start();
			senderThread.join();
			receiverThread.join();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
