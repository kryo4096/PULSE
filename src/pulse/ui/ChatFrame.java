package pulse.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;

import javax.swing.Action;
import javax.swing.Box;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import static pulse.Reference.*;

import pulse.net.*;

public class ChatFrame extends JFrame {

	private JPanel contentPane;
	private JTextField ipField;
	private JChatLog chatLog;
	private JTextField inputField;
	private JButton btnConnect;

	private Receiver receiver;
	private Transmitter transmitter;
	private NetListener listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatFrame frame = new ChatFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatFrame() {
		initUI();
		initLogic();
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel split_right = new JPanel();
		splitPane.setRightComponent(split_right);
		split_right.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		split_right.add(scrollPane, BorderLayout.CENTER);

		chatLog = new JChatLog();
		scrollPane.setViewportView(chatLog);

		inputField = new JTextField();
		split_right.add(inputField, BorderLayout.SOUTH);

		JPanel split_left = new JPanel();
		splitPane.setLeftComponent(split_left);
		GridBagLayout gbl_split_left = new GridBagLayout();
		gbl_split_left.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_split_left.rowHeights = new int[] { 0, 0, 0 };
		gbl_split_left.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_split_left.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		split_left.setLayout(gbl_split_left);

		JLabel lblConnect = new JLabel("Connect:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		split_left.add(lblConnect, gbc_lblNewLabel);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 0;
		split_left.add(verticalStrut, gbc_verticalStrut);

		ipField = new JTextField();
		GridBagConstraints gbc_ipField = new GridBagConstraints();
		gbc_ipField.insets = new Insets(0, 0, 0, 5);
		gbc_ipField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ipField.gridx = 0;
		gbc_ipField.gridy = 1;
		split_left.add(ipField, gbc_ipField);
		ipField.setColumns(10);

		btnConnect = new JButton("");
		btnConnect.setIcon(
				new ImageIcon(ChatFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnect.gridx = 1;
		gbc_btnConnect.gridy = 1;
		split_left.add(btnConnect, gbc_btnConnect);
	}

	private void initLogic() {
		receiver = new Receiver(PORT, chatLog);
		transmitter = new Transmitter(PORT, chatLog);
		listener = new NetListener(receiver, transmitter, chatLog);

		receiver.start();
		listener.start();

		btnConnect.addActionListener((e) -> {
			try {
				transmitter.connect(InetAddress.getByName(ipField.getText()));
				listener.interrupt();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoServerRunningException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		inputField.registerKeyboardAction((e) -> {
			transmitter.sendMessage(inputField.getText());
			inputField.setText("");
		}, KeyStroke.getKeyStroke("ENTER"), JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

}
