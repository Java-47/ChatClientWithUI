package telran.chat.client.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;

import telran.UI.model.ChatClientUI;
import telran.chat.model.Message;

public class Sender implements Runnable {

	private ChatClientUI chatClientUI;
	private ObjectOutputStream oos;
	ObjectInputStream ois;

	public Sender(ChatClientUI chatClientUI, ObjectOutputStream oos, ObjectInputStream ois) {
		this.chatClientUI = chatClientUI;
		this.oos = oos;
		this.ois = ois;
	}

	@Override
	public void run() {
		chatClientUI.getSendButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String input = ChatClientUI.getInputField().getText();
					ChatClientUI.getInputField().setText("");
					Message messageObj = new Message(ChatClientUI.getNickName(), input);
					messageObj.setTime(LocalTime.now());
					oos.writeObject(messageObj);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}});
		
			ChatClientUI.getInputField().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String input = ChatClientUI.getInputField().getText();
					ChatClientUI.getInputField().setText("");
					Message messageObj = new Message(ChatClientUI.getNickName(), input);
					messageObj.setTime(LocalTime.now());
					chatClientUI.getOos().writeObject(messageObj);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

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