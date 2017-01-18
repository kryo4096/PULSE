package pulse.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import pulse.Reference;
import pulse.interfaces.IChatLogger;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public class Receiver extends Thread {

    private int port;
    private IChatLogger logger;
    private InetAddress lastConnection;
    private boolean untouched;

    public InetAddress getLastConnection() {
		return lastConnection;
	}

	public boolean isUntouched() {
		return untouched;
	}

	public Receiver(int port, IChatLogger logger) {
        this.port = port;
        this.logger = logger;
        this.setDaemon(true);
        lastConnection = null;
        untouched = true;


    }

  

    public void changeLogger(IChatLogger logger) {
        this.logger = logger;
    }
    public void touch() {
    	untouched = false;
    }
    public InetAddress lastConnection() {

        return lastConnection;
    }

    @Override
    public void run() {
        while (this.isAlive()) {
            Socket transmitter;
            try (ServerSocket socket = new ServerSocket(port)) {

                transmitter = socket.accept();
                lastConnection = transmitter.getInetAddress();
                untouched = false;
                InputStream is = transmitter.getInputStream();
                Scanner s = new Scanner(is);
                s.useDelimiter(Reference.MSG_DELIM);
                
                String receivedMessage = s.next();
                if(!receivedMessage.equals(System.lineSeparator())){
                	logger.log(transmitter.getInetAddress().toString(),receivedMessage);
                }
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
