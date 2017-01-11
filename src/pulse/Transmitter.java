package pulse;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.net.Socket;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public class Transmitter {

    private int port;
    private IChatLogger logger;

    public Transmitter(int port, IChatLogger logger) {
        this.port = port;
        this.logger = logger;
    }

    public void sendMessage(InetAddress ip, String message){

        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(ip,port));

            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(message + "-E-N-D-");
            logger.log(socket.getLocalAddress().toString(),message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
