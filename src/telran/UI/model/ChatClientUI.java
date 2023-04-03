package telran.UI.model;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class ChatClientUI extends JFrame {

	private static final long serialVersionUID = 1162127549588214876L;
	JTextArea chatArea;
	JTextField inputField;
	String nickName;
	JButton sendButton;

	public ChatClientUI(String nickName) throws IOException {
		super("Java 47 Chat");
		this.nickName = nickName;

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
		// прокрутка чата вниз при получении сообщений
		DefaultCaret caret = (DefaultCaret) chatArea.getCaret(); //
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public JButton getSendButton() {
		// TODO Auto-generated method stub
		return sendButton;
	}

	public JTextField getInputField() {

		return inputField;
	}

	public JTextArea getChatArea() {
		// TODO Auto-generated method stub
		return chatArea;
	}

}
