package pulse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public class Receiver extends Thread {

    private int port;
    private IChatLogger logger;

    public Receiver(int port, IChatLogger logger) {
        this.port = port;
        this.logger = logger;
    }

    @Override
    public void run() {
        while (this.isAlive()) {
            Socket transmitter;
            try (ServerSocket socket = new ServerSocket()) {
                transmitter = socket.accept();
                InputStream is = transmitter.getInputStream();
                Scanner s = new Scanner(is);
                s.useDelimiter("-E-N-D-");
                logger.log(transmitter.getInetAddress().toString(),s.next());
                s.close();
                is.close();
                PrintStream ps = new PrintStream(transmitter.getOutputStream());
                ps.println("RECEIVED");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
