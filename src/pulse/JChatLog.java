package pulse;

/**
 * Created by kryo4096 on 11.01.2017.
 */
public class JChatLog extends JTextLog implements IChatLogger {
    @Override
    public synchronized void log(String nickname, String message) {
        String entry = "[ " + nickname + " ] " + message;
        println(entry);
    }
}
