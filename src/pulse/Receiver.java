package pulse;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public class Receiver extends Thread {

    private int port;
    private IChatLogger logger;
    private ArrayList<InetAddress> connections;

    public Receiver(int port, IChatLogger logger) {
        this.port = port;
        this.logger = logger;
        this.setDaemon(true);
        connections = new ArrayList<>();


    }

    public Receiver(int port) {
        this(port, NullLogger.instance());
    }

    public void changeLogger(IChatLogger logger) {
        this.logger = logger;
    }

    public ArrayList<InetAddress> connections() {

        return connections;
    }

    @Override
    public void run() {
        while (this.isAlive()) {
            Socket transmitter;
            try (ServerSocket socket = new ServerSocket(port)) {

                transmitter = socket.accept();
                connections.add(transmitter.getInetAddress());
                InputStream is = transmitter.getInputStream();
                Scanner s = new Scanner(is);
                s.useDelimiter("-E-N-D-");
                logger.log(transmitter.getInetAddress().toString(),s.next());
                s.close();
                is.close();
                //PrintStream ps = new PrintStream(transmitter.getOutputStream());
                // ps.println("RECEIVED");


            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e) {

            }
        }
    }
}
