package pulse.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import pulse.Reference;
import pulse.interfaces.IChatLogger;

import static pulse.Reference.PORT;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public class Transmitter {

    private int port;
    private IChatLogger logger;
    private InetAddress connection;
    

    public Transmitter(int port, IChatLogger logger) {
        this.port = port;
        this.logger = logger;
        try {
			this.connection = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    public ServerState connect(InetAddress ip){
    	ServerState state;
    	state = checkState(ip);
    	if(state.canConnect()){
    		connection = ip;
    	}
    	return state;
    }

    public void sendMessage(String message){

        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(connection, PORT));

            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(message + Reference.MSG_DELIM);
            logger.log(socket.getLocalAddress().toString(),message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ServerState checkState(InetAddress ip) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, PORT));
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(Reference.MSG_DELIM);
            
            return ServerState.ONLINE;
            

        } catch (IOException e) {
            return ServerState.OFFLINE;
        }
    }
}
