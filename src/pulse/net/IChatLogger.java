package pulse.net;

/**
 * Created by kryo4096 on 10.01.2017.
 */
public interface IChatLogger {

    void log(String nickname, String message);
    void syslog(String message);

}
