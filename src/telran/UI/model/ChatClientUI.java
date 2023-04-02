package telran.UI.model;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;

import javax.swing.*;

import telran.chat.client.task.Receiver;
import telran.chat.client.task.Sender;
import telran.chat.model.Message;

public class ChatClientUI extends JFrame {

	private static final long serialVersionUID = 1162127549588214876L;
	private JTextArea chatArea;
	private static JTextField inputField;

	private static String nickName;

	ObjectOutputStream oos;
	ObjectInputStream ois;

	JButton sendButton;

	public ChatClientUI(ObjectOutputStream oos, ObjectInputStream ois, String nickName) throws IOException {
		super("Java 47 Chat");
		this.oos = oos;
		this.ois = ois;
		this.setNickName(nickName);

		// Set up the UI components
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		JScrollPane chatScrollPane = new JScrollPane(chatArea);

		inputField = new JTextField(30);

		sendButton = new JButton("Send");

		// Set up the layout of the UI
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(inputField, BorderLayout.CENTER);
		inputPanel.add(sendButton, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(chatScrollPane, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
		// Set up the frame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);


		Sender sender = new Sender(this, oos, ois);
		Thread senderThread = new Thread(sender);
		senderThread.start();
		
//		Receiver receiver = new Receiver(this, oos, ois);
//		Thread receiverThread = new Thread(receiver);
//		receiverThread.start();

	}

	public static String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public JButton getSendButton() {
		// TODO Auto-generated method stub
		return sendButton;
	}

	public static JTextField getInputField() {

		return inputField;
	}

	public ObjectOutputStream getOos() {
		// TODO Auto-generated method stub
		return oos;
	}

	public JTextArea getChatArea() {
		// TODO Auto-generated method stub
		return chatArea;
	}

}