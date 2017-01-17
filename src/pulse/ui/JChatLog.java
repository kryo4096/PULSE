package pulse.ui;

import pulse.Reference;
import pulse.net.IChatLogger;

/**
 * Created by kryo4096 on 11.01.2017.
 */
public class JChatLog extends JTextLog implements IChatLogger {
    @Override
    public synchronized void log(String nickname, String message) {
        String entry = "[ " + nickname + " ] " + message;
        println(entry);
    }
    
    @Override
    public synchronized void syslog(String message) {
        String entry = "[ " + Reference.getNameString() + " ] " + message;
        println(entry);
    }
}
