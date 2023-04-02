package telran.chat.client.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import telran.UI.model.ChatClientUI;
import telran.chat.model.Message;

public class Receiver implements Runnable {
	
	private ChatClientUI chatClientUI;
	private ObjectOutputStream oos;
	ObjectInputStream ois;

	public Receiver(ChatClientUI chatClientUI, ObjectOutputStream oos, ObjectInputStream ois) {
		this.chatClientUI = chatClientUI;
		this.oos = oos;
		this.ois = ois;
	}

	@Override
	public void run() {
		while (true) {

			try {
				String response = ois.readObject().toString();
				System.out.println(response);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
