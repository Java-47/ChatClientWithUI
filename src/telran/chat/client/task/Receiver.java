package telran.chat.client.task;

import java.io.IOException;
import java.io.ObjectInputStream;

import telran.UI.model.ChatClientUI;
import telran.chat.model.Message;

public class Receiver implements Runnable {

	ChatClientUI chatClientUI;
	ObjectInputStream ois;

	public Receiver(ChatClientUI chatClientUI, ObjectInputStream ois) {
		this.chatClientUI = chatClientUI;
		this.ois = ois;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Message messageObj = (Message) ois.readObject();
				chatClientUI.getChatArea().append(messageObj.toString() + "\n");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

}
