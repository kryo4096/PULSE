package pulse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public class Pulse extends JFrame {
    public static final int PORT = 11235;
    private static final String VERSION = "Pulse 0.1.0a";
    private Receiver receiver;
    private Transmitter transmitter;
    private JChatLog chatLog;
    private InetAddress ip;


    public Pulse() {
        receiver = new Receiver(PORT);
        receiver.start();
        switch (JOptionPane.showOptionDialog(this, "Wait for or initiate connection.", VERSION, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new NullIcon(), new Object[]{"Wait", "Connect"}, "Wait")) {
            case 0:
                waitForConnection();
                break;
            case 1:
                initializeTransmitter();
        }

    }

    private void waitForConnection() {


        while (receiver.connections().size() < 1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chatLog = new JChatLog();
        transmitter = new Transmitter(PORT, chatLog);
        receiver.changeLogger(chatLog);

        initializeChat(receiver.connections().get(0));
    }


    public void initializeTransmitter() {
        chatLog = new JChatLog();
        transmitter = new Transmitter(PORT, chatLog);
        try {
            receiver.changeLogger(chatLog);
            ip = Inet4Address.getByName(JOptionPane.showInputDialog(this, "Enter IP-Adress of the receiver:"));
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(this, e, "Hostname not recognized.", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        if (!transmitter.checkOnline(ip)) {
            JOptionPane.showMessageDialog(this, ip, "Server not Running!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        initializeChat(ip);

    }

    public void initializeChat(InetAddress ip) {
        JScrollPane scrollPane = new JScrollPane(chatLog);


        setSize(400, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextField field = new JTextField();
        add(scrollPane, BorderLayout.CENTER);
        add(field, BorderLayout.SOUTH);

        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        final KeyStroke ENTER = KeyStroke.getKeyStroke("ENTER");
        ActionListener action = (e) -> {
            transmitter.sendMessage(ip, field.getText());
            field.setText("");
        };
        field.registerKeyboardAction(action, ENTER, condition);


        setVisible(true);
    }

    public static void main(String... args) {

        AtomicReference<Pulse> pulse = new AtomicReference<>(new Pulse());

    }


}
