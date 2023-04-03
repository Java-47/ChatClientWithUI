package telran.chat.client.task;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalTime;

import telran.UI.model.ChatClientUI;
import telran.chat.model.Message;

public class Sender implements Runnable {

	ChatClientUI chatClientUI;
	ObjectOutputStream oos;

	public Sender(ChatClientUI chatClientUI, ObjectOutputStream oos) {
		this.chatClientUI = chatClientUI;
		this.oos = oos;

	}

	@Override
	public void run() {
		ActionListener sendAction = e -> {
			try {
				String input = chatClientUI.getInputField().getText();
				chatClientUI.getInputField().setText("");
				Message messageObj = new Message(chatClientUI.getNickName(), input);
				messageObj.setTime(LocalTime.now());
				oos.writeObject(messageObj);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		};

		chatClientUI.getSendButton().addActionListener(sendAction);
		chatClientUI.getInputField().addActionListener(sendAction);

	}
}