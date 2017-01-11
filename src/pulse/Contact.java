package pulse;

import java.net.InetAddress;

/**
 * Created by kryo4096 on 11.01.2017.
 * Not yet in use
 */
public class Contact {

    public final InetAddress ip;
    public final String name;

    public Contact(InetAddress ip, String name) {
        this.ip = ip;
        this.name = name;
    }

}
