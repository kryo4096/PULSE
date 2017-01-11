package pulse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public class Pulse implements IChatLogger {
    public static final int PORT = 11235;
    private Receiver receiver;
    private Transmitter transmitter;
    public Pulse(){
        receiver = new Receiver(PORT,this);
        transmitter = new Transmitter(PORT,this);
        receiver.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            InetAddress ip = Inet4Address.getByName(br.readLine());
            while(true){
                transmitter.sendMessage(ip,br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
